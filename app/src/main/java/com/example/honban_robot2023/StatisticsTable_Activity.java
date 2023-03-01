package com.example.honban_robot2023;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.honban_robot2023.Fragment.StatisticsDisplaySetting_Fragment;
import com.example.honban_robot2023.Models.CommonParameters;
import com.example.honban_robot2023.Models.StatisticsTableController;
import com.example.honban_robot2023.Test.Test_dummyAPIData;

/**
 * 検査結果の一覧表を表示するアクティビティ
 */
public class StatisticsTable_Activity extends TableBaseActivity {

    String lastDateTimeKind = null;
    String lastSortColum = null;
    String lastOrderBy = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        settingDialog = new StatisticsDisplaySetting_Fragment(this);
        tableController = new StatisticsTableController(this, this.resultTable);
        tableController.setTableTitle(getResources().getStringArray(R.array.tableTitle_Statistics));
        settingDialog = new StatisticsDisplaySetting_Fragment(this);

        /* デバッグモードでは、APIからではなく一定のサンプルデータを使ってテーブルの表示 */
        if (CommonParameters.IS_API_DEBUG_MODE) {
            tableController.tableColumInit(Test_dummyAPIData.getResultDummy());
        } else
            setTableBody(this.retrofitApi.getStatisticsData());
        /* 日付入力画面横のボタンで、日付による絞り込みを行う。 ダイアログの設定項目を引き継ぐ */
        searchButton.setOnClickListener(view -> {
            updateTable(lastDateTimeKind, lastSortColum, lastOrderBy);
        });
    }

    /**
     * 絞り込み、並び替え条件を指定してテーブルを作成しなおし、再描写を行う。
     *
     * @param dateTimeKind 表示する日付の単位が、日、週、月のいずれかを指定
     * @param sortColum    並び替えの項目を指定。・日付:DATE ・良品率:PASSRATE
     *                     ・不良品率:DEFECTRATE
     * @param orderBy      並び替えが昇順、降順の指定
     *                     ・昇順:ASC  ・降順:DESC
     */
    public void updateTable(String dateTimeKind, String sortColum, String orderBy) {
        lastDateTimeKind = dateTimeKind;
        lastSortColum = sortColum;
        lastOrderBy = orderBy;
        refreshTable(retrofitApi.getStatisticsWithSearch(dateTimeKind, getFirstDate(),
                getLastDate(), sortColum, orderBy));
    }

    /* メニューバーのアイコンを押すことで設定ダイアログの表示 */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        settingDialog.show(getSupportFragmentManager(), "dialog");
        return super.onOptionsItemSelected(item);
    }

}