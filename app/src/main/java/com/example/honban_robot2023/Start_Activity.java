package com.example.honban_robot2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Start_Activity extends AppCompatActivity {

    Button nextToMonitoringScreen;
    Button nextToStatisticsScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_activty);
        nextToMonitoringScreen = findViewById(R.id.button);
        nextToStatisticsScreen = findViewById(R.id.button2);

        nextToMonitoringScreen.setOnClickListener(view -> {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });

        nextToStatisticsScreen.setOnClickListener(view -> {
            Intent intent = new Intent(this,AnalysisSelection_Activity.class);
            startActivity(intent);
        });


    }
}