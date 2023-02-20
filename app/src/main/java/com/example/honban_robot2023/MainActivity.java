package com.example.honban_robot2023;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.honban_robot2023.APIModules.APIManager;
import com.example.honban_robot2023.APIModules.ResultAPI.ResultsDataModel;
import com.example.honban_robot2023.Models.ResultTableController;
import com.example.honban_robot2023.Models.RetrofitFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    Button result;
    Button Statistics;
    Button TimeInterval;
    Button Utilization;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.button_moveResultTable);
        Statistics = findViewById(R.id.button_moveStatistics);
        TimeInterval = findViewById(R.id.button_moveTimeInterval);
        Utilization = findViewById(R.id.button_moveUtilization);

        result.setOnClickListener(new clickToMove(ResultTable_Activity.class));
        Statistics.setOnClickListener(new clickToMove(StatisticsTable_Activity.class));
        TimeInterval.setOnClickListener(new clickToMove(TimeIntervalsTable_Activity.class));
        Utilization.setOnClickListener(new clickToMove(UtilizationTable_Activity.class));
        testAPIUsingQuery();
    }

    class clickToMove implements View.OnClickListener {

        Class activity;

        public clickToMove(Class activity) {
            this.activity = activity;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, activity);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.mainpage_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (R.id.menu_moveToResultTable == item.getItemId())
            startActivity(new Intent(MainActivity.this, ResultTable_Activity.class));
        else if (R.id.menu_moveStatisticsTable == item.getItemId())
            startActivity(new Intent(MainActivity.this, StatisticsTable_Activity.class));
        else if (R.id.menu_moveToTimeTable == item.getItemId())
            startActivity(new Intent(MainActivity.this, TimeIntervalsTable_Activity.class));
        else if (R.id.menu_moveToUtilizationTable == item.getItemId())
            startActivity(new Intent(MainActivity.this, UtilizationTable_Activity.class));

        return super.onOptionsItemSelected(item);
    }

    private void testAPIUsingQuery(){
        APIManager retrofitApi =  RetrofitFactory.getApiClient("https://192.168.96.69:7015/api/").create(APIManager.class);

        Call<List<ResultsDataModel>> e = retrofitApi.getResultWithSearch("R","OK","2023/02/06","2023/02/07");
        e.enqueue(new Callback<List<ResultsDataModel>>() {
            @Override
            public void onResponse(Call<List<ResultsDataModel>> call, Response<List<ResultsDataModel>> response) {
                ResultsDataModel dataModel = response.body().get(0);
                Log.d("TAG", dataModel.getAllResult().toString());
            }

            @Override
            public void onFailure(Call<List<ResultsDataModel>> call, Throwable t) {
                return;
            }
        });
    }

}