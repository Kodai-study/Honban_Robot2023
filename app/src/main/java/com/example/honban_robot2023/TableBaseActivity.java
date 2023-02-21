package com.example.honban_robot2023;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterButton;

import com.example.honban_robot2023.APIModules.APIManager;
import com.example.honban_robot2023.Models.RetrofitFactory;
import com.example.honban_robot2023.Models.TableResultControl;
import com.example.honban_robot2023.event.DateSelectButtonClickListener;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 検査結果の一覧表を表示するアクティビティ
 */
public abstract class TableBaseActivity extends AppCompatActivity {

    /**
     * 一覧表を表示するテーブルレイアウト
     */
    protected TableLayout resultTable;

    protected SharedPreferences saveSearchSettings;

    /**
     * 検査日時の範囲の下限値を設定するカレンダーダイアログを表示するボタン
     * {@link #firstDateInput}
     */
    protected ImageFilterButton firstDateSelect;
    /**
     * 検査日時の範囲の上限値を設定するカレンダーダイアログを表示するボタン
     * {@link #lastDateInput}
     */
    protected ImageFilterButton lastDateSelect;
    /**
     * 表示する結果を検査日時で範囲指定する時の下限値を入力する
     * テキストボックス。ダイアログによって自動で入力される
     */
    protected EditText firstDateInput;
    protected EditText lastDateInput;

    protected APIManager retrofitApi;

    protected TableResultControl tableController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_table);
        resultTable = findViewById(R.id.table_resultDatas);
        resultTable.setBackgroundColor(Color.WHITE);
        firstDateSelect = findViewById(R.id.imageButton_selectFirstTime);
        lastDateSelect = findViewById(R.id.imageButton_selectLastTime);
        firstDateInput = findViewById(R.id.editText_firstDate);
        lastDateInput = findViewById(R.id.editText_lastDate);
        firstDateSelect.setOnClickListener(new DateSelectButtonClickListener(this, firstDateInput));
        lastDateSelect.setOnClickListener(new DateSelectButtonClickListener(this, lastDateInput, firstDateInput));
        retrofitApi =  RetrofitFactory.getApiClient("https://192.168.96.69:7015/api/").create(APIManager.class);

    }


    protected <T> void setResultTable(Call<List<T>> list) {
        list.enqueue(new Callback<List<T>>() {
            @Override
            public void onResponse(@NonNull Call<List<T>> call, @NonNull Response<List<T>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                tableController.tableColumInit(Objects.requireNonNull(response.body()));
            }

            @Override
            public void onFailure(@NonNull Call<List<T>> call, @NonNull Throwable t) {
                Toast.makeText(TableBaseActivity.this,
                        t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_table_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}