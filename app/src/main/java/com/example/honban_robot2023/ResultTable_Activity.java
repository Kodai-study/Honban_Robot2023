package com.example.honban_robot2023;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterButton;

import com.example.honban_robot2023.APIModules.APIManager;
import com.example.honban_robot2023.APIModules.ResultAPI.ResultsDataModel;
import com.example.honban_robot2023.APIModules.SampleAPIModel;
import com.example.honban_robot2023.Models.ResultTableController;
import com.example.honban_robot2023.Models.SampleTableController;
import com.example.honban_robot2023.Models.RetrofitFactory;
import com.example.honban_robot2023.Test.TestFetchAPI;
import com.example.honban_robot2023.event.DateSelectButtonClickListener;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * 検査結果の一覧表を表示するアクティビティ
 */
public class ResultTable_Activity extends AppCompatActivity {

    /**
     * 一覧表を表示するテーブルレイアウト
     */
    TableLayout resultTable;

    SharedPreferences saveSearchSettings;

    /**
     * 検査日時の範囲の下限値を設定するカレンダーダイアログを表示するボタン
     * {@link #firstDateInput}
     */
    ImageFilterButton firstDateSelect;
    /**
     * 検査日時の範囲の上限値を設定するカレンダーダイアログを表示するボタン
     * {@link #lastDateInput}
     */
    ImageFilterButton lastDateSelect;
    /**
     * 表示する結果を検査日時で範囲指定する時の下限値を入力する
     * テキストボックス。ダイアログによって自動で入力される
     */
    EditText firstDateInput;
    EditText lastDateInput;

    /**
     * テーブルの行に適応させるレイアウトデータ。
     */
    final TableLayout.LayoutParams rowLayoutParams = new TableLayout.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT);

    boolean[] viewable = new boolean[]{true, true, true, true};

    Button[] button = new Button[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_table);

        rowLayoutParams.setMargins(10, 0, 10, 10);

        resultTable = findViewById(R.id.table_resultDatas);
        resultTable.getChildAt(0).setBackgroundColor(Color.GRAY);
        resultTable.setBackgroundColor(Color.WHITE);

        button[0] = findViewById(R.id.button);
        button[1] = findViewById(R.id.button2);
        button[2] = findViewById(R.id.button3);
        button[3] = findViewById(R.id.button4);
        for (int i = 0; i < button.length; i++) {
            button[i].setOnClickListener(new ColumButtonClickListener(i));
        }
        // fetchAPISample();
            setResultTable();

        new TestFetchAPI(this).debugFetchAPI();
        firstDateSelect = findViewById(R.id.imageButton_selectFirstTime);
        lastDateSelect = findViewById(R.id.imageButton_selectLastTime);
        firstDateInput = findViewById(R.id.editText_firstDate);
        lastDateInput = findViewById(R.id.editText_lastDate);
        firstDateSelect.setOnClickListener(new DateSelectButtonClickListener(this, firstDateInput));
        lastDateSelect.setOnClickListener(new DateSelectButtonClickListener(this, lastDateInput, firstDateInput));
    }

    class ColumButtonClickListener implements View.OnClickListener {

        private final int columPosition;

        public ColumButtonClickListener(int columPosition) {
            this.columPosition = columPosition;
        }

        @Override
        public void onClick(View view) {

            for (int i = 0; i < resultTable.getChildCount(); i++) {
                TableRow tableRow = (TableRow) (resultTable.getChildAt(i));
                if (viewable[columPosition]) {
                    tableRow.getChildAt(columPosition).setVisibility(View.GONE);
                } else {
                    tableRow.getChildAt(columPosition).setVisibility(View.VISIBLE);
                }
            }
            viewable[columPosition] = !viewable[columPosition];
        }
    }

    /**
     * @see <a href="https://jsonplaceholder.typicode.com/posts">公開されているサンプルのWebAPI</a>
     * からJSONデータを持ってきてTableLayoutに表示する。
     */
    private void fetchAPISample() {

        Retrofit retrofit = RetrofitFactory.getApiClient("https://jsonplaceholder.typicode.com/");
        APIManager retrofitApi = retrofit.create(APIManager.class);
        Call<List<SampleAPIModel>> fetchedResultList = retrofitApi.getModels();
        SampleTableController tableController = new SampleTableController(this, this.resultTable);
        fetchedResultList.enqueue(new Callback<List<SampleAPIModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<SampleAPIModel>> call, @NonNull Response<List<SampleAPIModel>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                tableController.tableInit(Objects.requireNonNull(response.body()));
            }

            @Override
            public void onFailure(@NonNull Call<List<SampleAPIModel>> call, @NonNull Throwable t) {
                Toast.makeText(ResultTable_Activity.this,
                        t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        new TestFetchAPI(this).debugFetchAPI();
    }

    private void setResultTable() {

        Retrofit retrofit = RetrofitFactory.getApiClient("https://192.168.96.69:7015/api/");
        APIManager retrofitApi = retrofit.create(APIManager.class);

        Call<List<ResultsDataModel>> fetchedResultList = retrofitApi.getResults();
        ResultTableController tableController = new ResultTableController(this, this.resultTable);
        fetchedResultList.enqueue(new Callback<List<ResultsDataModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<ResultsDataModel>> call, @NonNull Response<List<ResultsDataModel>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                tableController.tableInit(Objects.requireNonNull(response.body()));
            }

            @Override
            public void onFailure(@NonNull Call<List<ResultsDataModel>> call, @NonNull Throwable t) {
                Toast.makeText(ResultTable_Activity.this,
                        t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_table_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        LinearLayout layout = findViewById(R.id.layout_timeSearch);
        if (layout.getVisibility() == View.GONE) {
            layout.setVisibility(View.VISIBLE);
        } else {
            layout.setVisibility(View.GONE);
        }
        new ResultTableSettingDialog().show(getSupportFragmentManager(), "dialog");
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveSearchSettings = getSharedPreferences("resultTableSettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor saveSettingEditor = saveSearchSettings.edit();
        saveSettingEditor.putString("searchTimeFirst", "g");
        saveSettingEditor.apply();
    }
}