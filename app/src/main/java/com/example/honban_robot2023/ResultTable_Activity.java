package com.example.honban_robot2023;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.example.honban_robot2023.Fragment.ResultTableSetting_Fragment;
import com.example.honban_robot2023.Models.ResultTableController;

/**
 * 検査結果の一覧表を表示するアクティビティ
 */
public class ResultTable_Activity extends TableBaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tableController = new ResultTableController(this, this.resultTable);
        tableController.setTableTitle(getResources().getStringArray(R.array.tableTitle_Result));
        setResultTable(this.retrofitApi.getResults());
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        LinearLayout layout = findViewById(R.id.layout_timeSearch);
        if (layout.getVisibility() == View.GONE) {
            layout.setVisibility(View.VISIBLE);
        } else {
            layout.setVisibility(View.GONE);
        }
        new ResultTableSetting_Fragment().show(getSupportFragmentManager(), "dialog");
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