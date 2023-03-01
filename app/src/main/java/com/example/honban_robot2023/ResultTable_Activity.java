package com.example.honban_robot2023;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.honban_robot2023.Fragment.ResultTableSetting_Fragment;
import com.example.honban_robot2023.Models.ResultTableController;

/**
 * 検査結果の一覧表を表示するアクティビティ
 */
public class ResultTable_Activity extends TableBaseActivity {

    String lastSelectColum = null;
    String lastSelectResult = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingDialog = new ResultTableSetting_Fragment(this);
        tableController = new ResultTableController(this, this.resultTable);
        tableController.setTableTitle(getResources().getStringArray(R.array.tableTitle_Result));
        setTableBody(this.retrofitApi.getResults());  //最初に、クエリなしの結果を表示する
        searchButton.setOnClickListener(view -> {       //検索ボタンを押すと、検査日付で絞り込みができる。
            updateTable(lastSelectColum, lastSelectResult); // ダイアログの設定項目を引き継ぐ
        });
    }

    /**
     * 絞り込み、並び替え条件を指定してテーブルを作成しなおし、再描写を行う。
     *
     * @param selectColum  NGになった検査項目を指定して絞り込み
     * @param selectResult ワークの良品、不良品で絞り込み
     */
    public void updateTable(String selectColum, String selectResult) {
        lastSelectColum = selectColum;
        lastSelectResult = selectResult;
        refreshTable(retrofitApi.getResultWithSearch(selectColum, selectResult, getFirstDate(),
                getLastDate(), null, null));
    }

    /* メニューバーのアイコンを押すことで設定ダイアログの表示 */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        settingDialog.show(getSupportFragmentManager(), "dialog");
        return super.onOptionsItemSelected(item);
    }

}