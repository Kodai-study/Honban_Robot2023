package com.example.honban_robot2023.Test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.honban_robot2023.APIModules.APIManager;
import com.example.honban_robot2023.APIModules.StatisticsAPIModel;
import com.example.honban_robot2023.Models.RetrofitFactory;
import com.example.honban_robot2023.R;
import com.example.honban_robot2023.TableBaseActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PieChartSample_Activity extends AppCompatActivity {

    PieChart pieChart;

    APIManager retrofitApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart_sample);
        setTestChartData();
        retrofitApi = RetrofitFactory.getApiClient("https://192.168.96.69:7015/api/").create(APIManager.class);

        //setInspection();
        setNgCourse();
    }

    private void setTestChartData() {
        String[] dimensions = new String[]{"A", "B", "C", "D"};
        float[] values = new float[]{1f, 2f, 3f, 4f};

        //①Entryにデータ格納
        List<PieEntry> entryList = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            entryList.add(
                    new PieEntry(values[i], dimensions[i])
            );
        }

        PieDataSet pieDataSet = new PieDataSet(entryList, "candle");
        //③DataSetのフォーマット指定
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        //④PieDataにPieDataSet格納

        PieData pieData = new PieData(pieDataSet);
        //⑤PieChartにPieData格納
        pieChart = findViewById(R.id.pieChartExample);
        pieChart.setData(pieData);
        //⑥Chartのフォーマット指定
        pieChart.setEnabled(false);
        //⑦PieChart更新
        pieChart.invalidate();
    }

    private void setPieChart(String[] columNames, float[] values) {

        //①Entryにデータ格納
        List<PieEntry> entryList = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            entryList.add(
                    new PieEntry(values[i], columNames[i])
            );
        }

        PieDataSet pieDataSet = new PieDataSet(entryList, "candle");
        //③DataSetのフォーマット指定
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        //④PieDataにPieDataSet格納

        PieData pieData = new PieData(pieDataSet);
        //⑤PieChartにPieData格納
        pieChart = findViewById(R.id.pieChartExample);
        pieChart.setData(pieData);
        //⑥Chartのフォーマット指定
        pieChart.setEnabled(false);
        //⑦PieChart更新
        pieChart.invalidate();
    }

    private void setInspection() {

        Call<List<StatisticsAPIModel>> statisticsData = retrofitApi.getStatisticsData();
        statisticsData.enqueue(new Callback<List<StatisticsAPIModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<StatisticsAPIModel>> call, @NonNull Response<List<StatisticsAPIModel>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                int sum_visual = 0;
                int sum_function = 0;
                for (StatisticsAPIModel e : response.body()) {
                    sum_visual += e.ngCount_VisualInspection();
                    sum_function += e.ngCount_FunctionalInspection();
                }
                setPieChart(new String[]{"外観検査不良品", "機能検査不良品"}, new float[]{sum_visual, sum_function});
            }

            @Override
            public void onFailure(@NonNull Call<List<StatisticsAPIModel>> call, @NonNull Throwable t) {
                Toast.makeText(PieChartSample_Activity.this,
                        t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setNgCourse() {

        Call<List<StatisticsAPIModel>> statisticsData = retrofitApi.getStatisticsData();
        statisticsData.enqueue(new Callback<List<StatisticsAPIModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<StatisticsAPIModel>> call, @NonNull Response<List<StatisticsAPIModel>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                float ngCounts[] = new float[8];
                for (StatisticsAPIModel e : response.body()) {
                    ngCounts[0] += e.getNgCount_IC1();
                    ngCounts[1] += e.getNgCount_IC2();
                    ngCounts[2] += e.getNgCount_R5();
                    ngCounts[3] += e.getNgCount_R10();
                    ngCounts[4] += e.getNgCount_R11();
                    ngCounts[5] += e.getNgCount_R12();
                    ngCounts[6] += e.getNgCount_R18();
                    ngCounts[7] += e.getNgCount_DIPSW();
                }
                setPieChart(new String[]{"IC1", "IC2","R5","R10","R11","R12","R18","DIPSW"},ngCounts);
            }

            @Override
            public void onFailure(@NonNull Call<List<StatisticsAPIModel>> call, @NonNull Throwable t) {
                Toast.makeText(PieChartSample_Activity.this,
                        t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
