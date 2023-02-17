package com.example.honban_robot2023;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.honban_robot2023.Fragment.ResultTableSetting_Fragment;
import com.example.honban_robot2023.Models.ConfigParameters;
import com.example.honban_robot2023.Models.TimeIntervalTableController;
import com.example.honban_robot2023.Test.Test_dummyAPIData;

/**
 * 検査結果の一覧表を表示するアクティビティ
 */
public class TimeIntervalsTable_Activity extends TableBaseActivity {

    Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tableController = new TimeIntervalTableController(this, this.resultTable);
        tableController.setTableTitle(new String[]{"fae", "fae"});
        if (ConfigParameters.IS_DEBUG_MODE)
            tableController.tableInit(Test_dummyAPIData.getTimeIntervalDummy());
        else
            setResultTable(this.retrofitApi.getTimeIntervalData());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.app_bar_switch).setVisible(true);
        return super.onPrepareOptionsMenu(menu);
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