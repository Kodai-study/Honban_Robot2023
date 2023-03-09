package com.example.honban_robot2023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Visibility;

import android.os.Bundle;

import com.example.honban_robot2023.Models.ModeSelectRecycleViewAdapter;

public class AnalysisSelection_Activity extends AppCompatActivity {

    RecyclerView cardViewList;
    ModeSelectRecycleViewAdapter recycleViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_selection);
        recycleViewAdapter = new ModeSelectRecycleViewAdapter(getResources(),this);
        cardViewList = findViewById(R.id.recyclerView_ModeSelect);
        RecyclerView.LayoutManager rLayoutManager = new LinearLayoutManager(this);

        cardViewList.setLayoutManager(rLayoutManager);
        cardViewList.setAdapter(recycleViewAdapter);
    }
}