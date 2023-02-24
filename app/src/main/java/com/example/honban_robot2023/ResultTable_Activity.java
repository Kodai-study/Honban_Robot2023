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

import java.util.Locale;

/**
 * 検査結果の一覧表を表示するアクティビティ
 */
public class ResultTable_Activity extends TableBaseActivity {

    ResultTableSetting_Fragment settingDialog = new ResultTableSetting_Fragment(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tableController = new ResultTableController(this, this.resultTable);
        tableController.setTableTitle(getResources().getStringArray(R.array.tableTitle_Result));
        setResultTable(this.retrofitApi.getResults());
    }

    public void updateTable(String selectColum,String selectResult) {
/*
        if (selectColum == null || selectColum.equals("")) {
            refreshTable(retrofitApi.getResultWithSearch(null, null,
                    getFirstDate(), getLastDate(), null, null));
        } else if (selectColum.equalsIgnoreCase("OK") || selectColum.equalsIgnoreCase("NG"))
            refreshTable(retrofitApi.getResultWithSearch(null, selectColum,
                    getFirstDate(), getLastDate(), null, null));
                    */

        refreshTable(retrofitApi.getResultWithSearch(selectColum,selectResult,getFirstDate(),
                getLastDate(),null,null));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        LinearLayout layout = findViewById(R.id.layout_timeSearch);
        if (layout.getVisibility() == View.GONE) {
            layout.setVisibility(View.VISIBLE);
        } else {
            layout.setVisibility(View.GONE);
        }
        settingDialog.show(getSupportFragmentManager(), "dialog");
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