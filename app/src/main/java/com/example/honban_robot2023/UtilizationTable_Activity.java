package com.example.honban_robot2023;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.honban_robot2023.Fragment.UtilizationDisplaySetting_Fragment;
import com.example.honban_robot2023.Models.CommonParameters;
import com.example.honban_robot2023.Models.UtilizationTableController;
import com.example.honban_robot2023.Test.Test_dummyAPIData;

/**
 * 1日毎の、ロボットの稼働状態(稼働時間や停止時間、稼働率)のテーブルを表示
 * させるアクティビティ。
 */
public class UtilizationTable_Activity extends TableBaseActivity {

    /**
     * 最後に設定された、並び替え項目
     */
    String lastSortColum = null;
    String lastOrderBy = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingDialog = new UtilizationDisplaySetting_Fragment(this);
        tableController = new UtilizationTableController(this, this.resultTable);
        tableController.setTableTitle(getResources().getStringArray(R.array.tableTitle_Utilization));
        if (CommonParameters.IS_API_DEBUG_MODE)
            tableController.tableColumInit(Test_dummyAPIData.getUtilizationDummy());
        else
            setTableBody(this.retrofitApi.getUtilizationData());

        searchButton.setOnClickListener(view -> {
            updateTable(lastSortColum, lastOrderBy);
        });
    }

    /**
     * 絞り込み、並び替え条件を指定してテーブルを作成しなおし、再描写を行う。
     *
     * @param sortColum 並び替えの項目を指定。・日付:DATE
     *                  ・稼働時間:operationTime
     * @param orderBy   並び替えが昇順、降順の指定
     *                  ・昇順:ASC  ・降順:DESC
     */
    public void updateTable(String sortColum, String orderBy) {
        lastSortColum = sortColum;
        lastOrderBy = orderBy;
        refreshTable(retrofitApi.getUtilizationDataWithSearch(getFirstDate(), getLastDate(), sortColum, orderBy));
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