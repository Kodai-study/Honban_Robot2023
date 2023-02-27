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
    String lastSelectColum = null;
    String lastSelectResult = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tableController = new ResultTableController(this, this.resultTable);
        tableController.setTableTitle(getResources().getStringArray(R.array.tableTitle_Result));
        setResultTable(this.retrofitApi.getResults());
        searchButton.setOnClickListener(view -> {
            updateTable(lastSelectColum, lastSelectResult);
        });
    }

    public void updateTable(String selectColum, String selectResult) {
        lastSelectColum = selectColum;
        lastSelectResult = selectResult;
        refreshTable(retrofitApi.getResultWithSearch(selectColum, selectResult, getFirstDate(),
                getLastDate(), null, null));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
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