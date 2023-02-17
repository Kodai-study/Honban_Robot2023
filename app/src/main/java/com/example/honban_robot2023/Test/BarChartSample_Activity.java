package com.example.honban_robot2023.Test;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.honban_robot2023.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

public class BarChartSample_Activity extends AppCompatActivity {

    BarChart bar_chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart_sample);

        //chartのコンポーネントを取得
        bar_chart = findViewById(R.id.barchart);

        bar_chart.getAxisLeft().setTextSize(20);


        //グラフのデータを設定
        ArrayList<BarEntry> value1 = new ArrayList<>();
        value1.add(new BarEntry(0F, 100F));
        value1.add(new BarEntry(1F, 150F));
        value1.add(new BarEntry(2F, 200F));
        value1.add(new BarEntry(4F, 100F));
        value1.add(new BarEntry(5F, 150F));
        value1.add(new BarEntry(7F, 200F));

        ArrayList<BarEntry> value2 = new ArrayList<>();
        value2.add(new BarEntry(0F, 50F));
        value2.add(new BarEntry(1F, 100F));
        value2.add(new BarEntry(2F, 150F));
        value2.add(new BarEntry(4F, 50F));
        value2.add(new BarEntry(5F, 100F));
        value2.add(new BarEntry(7F, 150F));

        //chartに設定
        BarDataSet dataSet1 = new BarDataSet(value1, "sample1");
        dataSet1.setColor(Color.GREEN);
        BarDataSet dataSet2 = new BarDataSet(value2, "sample2");
        dataSet2.setColor(Color.BLUE);

        List<IBarDataSet> dataSets = new ArrayList<>();

        dataSets.add(dataSet1);
        dataSets.add(dataSet2);

        bar_chart.setData(new BarData(dataSets));
        bar_chart.invalidate(); // refresh

        Spinner spinner = findViewById(R.id.spinner2);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.spinner,
                getResources().getStringArray(R.array.selectRange)
        );
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);
    }
}