package com.example.honban_robot2023;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.example.honban_robot2023.Fragment.StatisticsDisplaySetting_Fragment;
import com.example.honban_robot2023.Models.ConfigParameters;
import com.example.honban_robot2023.Models.StatisticsTableController;
import com.example.honban_robot2023.Test.Test_dummyAPIData;

/**
 * 検査結果の一覧表を表示するアクティビティ
 */
public class StatisticsTable_Activity extends TableBaseActivity {

    StatisticsDisplaySetting_Fragment settingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tableController = new StatisticsTableController(this, this.resultTable);
        tableController.setTableTitle(getResources().getStringArray(R.array.tableTitle_Statistics));
        settingDialog = new StatisticsDisplaySetting_Fragment(this);

        if (ConfigParameters.IS_DEBUG_MODE) {
            tableController.tableColumInit(Test_dummyAPIData.getResultDummy());
        } else
            setResultTable(this.retrofitApi.getStatisticsData());
    }

    public void updateTable(String dateTimeKind, String sortColum, String orderBy) {

        refreshTable(retrofitApi.getStatisticsWithSearch(dateTimeKind, getFirstDate(),
                getLastDate(), sortColum, orderBy));
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