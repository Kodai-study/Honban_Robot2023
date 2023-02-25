package com.example.honban_robot2023;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.honban_robot2023.event.TimerTaskMonitorSystem;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    Button result;
    Button Statistics;
    Button TimeInterval;
    Button Utilization;

    Timer t = new Timer();

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
        t.scheduleAtFixedRate(new TimerTaskMonitorSystem(this), 1000, 1000);
    }

    class clickToMove implements View.OnClickListener {

        Class<? extends AppCompatActivity> activity;

        public clickToMove(Class<? extends AppCompatActivity> activity) {
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

}