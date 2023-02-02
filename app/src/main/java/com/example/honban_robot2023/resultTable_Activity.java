package com.example.honban_robot2023;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.honban_robot2023.APIModules.APIManager;
import com.example.honban_robot2023.APIModules.RowDataViews;
import com.example.honban_robot2023.APIModules.SampleAPIModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class resultTable_Activity extends AppCompatActivity {

    TableLayout resultTable;

    /**
     * テーブルの行に適応させるレイアウトデータ。
     */
    final TableLayout.LayoutParams rowLayoutParams = new TableLayout.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT);
    /**
     * テーブルの行の、1つ目の要素に適応させるレイアウトデータ
     */
    final TableRow.LayoutParams firstTextLayoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT);

    /**
     * テーブルの行の、1つ目以降の要素に適応させるレイアウトデータ
     */
    final TableRow.LayoutParams textLayoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_table);

        //rowLayoutParams.topMargin = 100;
        rowLayoutParams.setMargins(10,0,10,10);


        resultTable = findViewById(R.id.table_resultDatas);
        resultTable.getChildAt(0).setBackgroundColor(Color.GRAY);
        resultTable.setBackgroundColor(Color.WHITE);
        fetchAPISample();
    }


    /**
     *
     * @see <a href="https://jsonplaceholder.typicode.com/posts">公開されているサンプルのWebAPI</a>
     * からJSONデータを持ってきてTableLayoutに表示する。
     */
    private void fetchAPISample() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com").
                addConverterFactory(GsonConverterFactory.create())
                .build();

        APIManager retrofitApi = retrofit.create(APIManager.class);

        Call<List<SampleAPIModel>> models = retrofitApi.getModels();


        models.enqueue(new Callback<List<SampleAPIModel>>() {

            @Override
            public void onResponse(Call<List<SampleAPIModel>> call, Response<List<SampleAPIModel>> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                for (SampleAPIModel e : response.body()) {
                    TableRow t = new TableRow(resultTable_Activity.this);

                    RowDataViews tableRowViews = new RowDataViews
                            (resultTable_Activity.this,e);

                    t.addView(tableRowViews.getUserIdText());
                    t.addView(tableRowViews.getIdText());
                    t.addView(tableRowViews.getTitleText());
                    t.addView(tableRowViews.getSubTitleText());
                    t.setLayoutParams(rowLayoutParams);
                    t.setPadding(0,100,0,0);
                    t.setBackgroundColor(Color.BLACK);
                    resultTable.addView(t);
                }

            }

            @Override
            public void onFailure(Call<List<SampleAPIModel>> call, Throwable t) {
                Toast.makeText(resultTable_Activity.this, "ミスった", Toast.LENGTH_SHORT).show();
            }
        });
    }

}