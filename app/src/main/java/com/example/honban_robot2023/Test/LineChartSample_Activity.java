
//package your.package.name;

/*
Copyright 2018 Philipp Jahoda

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed
under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
either express or implied. See the License for the specific language governing permissions
and limitations under the License.
 */

package com.example.honban_robot2023.Test;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.honban_robot2023.Models.CommonParameters;
import com.example.honban_robot2023.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class LineChartSample_Activity extends AppCompatActivity {

    private LineChart mChart;

    Calendar currentTime = Calendar.getInstance();

    int timeRange = Calendar.DATE;

    List<Entry> data1 = new ArrayList<>();

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_sample);

        mChart = findViewById(R.id.lineChart);
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    addData_other();
                } else {
                    addData();
                }

                mChart.notifyDataSetChanged();
                mChart.invalidate();
                mChart.refreshDrawableState();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Grid背景色
        mChart.setDrawGridBackground(false);

        // no description text
        mChart.getDescription().setEnabled(false);

        // Grid縦軸を破線
        XAxis xAxis = mChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                Calendar calendar = (Calendar) currentTime.clone();
                calendar.add(timeRange, (int) value);
                return CommonParameters.DATEFORMATTER_YEAR_TWODIGITS.format(calendar.getTime());
            }
        });

        YAxis leftAxis = mChart.getAxisLeft();
        mChart.getAxisRight().setEnabled(false);
        // Y軸最大最小設定
        //leftAxis.setAxisMaximum(100);
        //leftAxis.setAxisMinimum(0f);
        // Grid横軸を破線
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(true);

        addData();

        LineDataSet lineDataSet = new LineDataSet(data1, "data");

        mChart.setData(new LineData(lineDataSet));

    }

    private void addData() {
        data1.clear();
        data1.add(new Entry(1, 0.8f));
        data1.add(new Entry(2, 0.78f));
        data1.add(new Entry(4, 0.9f));
        data1.add(new Entry(5, 0.8f));
        data1.add(new Entry(8, 0.7f));
        data1.add(new Entry(10, 0.68f));
    }

    private void addData_other() {
        data1.clear();
        data1.add(new Entry(1, 0.69f));
        data1.add(new Entry(3, 0.8f));
        data1.add(new Entry(4, 0.92f));
        data1.add(new Entry(5, 0.8f));
        data1.add(new Entry(8, 0.75f));
        data1.add(new Entry(12, 0.71f));
    }

}