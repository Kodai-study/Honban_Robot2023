package com.example.honban_robot2023;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;

import com.example.honban_robot2023.APIModules.TimeStampModel;
import com.example.honban_robot2023.Models.ConfigParameters;
import com.example.honban_robot2023.Models.TableItemsControl;
import com.example.honban_robot2023.Models.TimeIntervalTableController;
import com.example.honban_robot2023.Models.TimeStampTableController;
import com.example.honban_robot2023.Test.Test_dummyAPIData;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 検査結果の一覧表を表示するアクティビティ
 */
public class TimeIntervalsTable_Activity extends TableBaseActivity {

    SwitchCompat tableSwitchToggle;

    TableItemsControl<TimeStampModel> timeStampTableController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tableController = new TimeIntervalTableController(this, this.resultTable);
        this.timeStampTableController = new TimeStampTableController(this, this.resultTable);
        if (ConfigParameters.IS_DEBUG_MODE)
            tableController.tableColumInit(Test_dummyAPIData.getTimeIntervalDummy());
        else
            setTableBody(this.retrofitApi.getTimeIntervalData());

        searchButton.setOnClickListener( view -> {
            updateTable();
        });
    }

    public void updateTable() {
        if (tableSwitchToggle.isChecked()) {

            resultTable.removeAllViews();
            setTimeStampTable();
        }
        else
            refreshTable(retrofitApi.getTimeIntervalDataWithSearch(getFirstDate(), getLastDate()));
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        boolean superResult = super.onCreateOptionsMenu(menu);
        MenuItem switchItem = menu.findItem(R.id.app_bar_switch2);
        switchItem.setVisible(true);
        this.tableSwitchToggle = (SwitchCompat) switchItem.getActionView();
        tableSwitchToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            switchTableKind(isChecked);
        });
        tableController.setTableTitle(getResources().getStringArray(R.array.tableTitle_TimeInterval));
        return superResult;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.app_bar_switch2).setVisible(true);
        return super.onPrepareOptionsMenu(menu);
    }

    private void switchTableKind(boolean isViewTimeStamp) {
        resultTable.removeAllViews();
        if (isViewTimeStamp) {
            setTimeStampTable();
            return;
        }
        tableController.setTableTitle(getResources().getStringArray(R.array.tableTitle_TimeStamp));
        super.setTableBody(this.retrofitApi.getTimeIntervalDataWithSearch(getFirstDate(),getLastDate()));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        boolean b = item.isChecked();
        Toast.makeText(this, String.valueOf(b), Toast.LENGTH_SHORT).show();
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

    private void setTimeStampTable(){

        tableController.setTableTitle(getResources().getStringArray(R.array.tableTitle_TimeStamp));
        Call<List<TimeStampModel>> timeStamps = retrofitApi.getTimeStampDataWithSearch(getFirstDate(),getLastDate());
        timeStamps.enqueue(new Callback<List<TimeStampModel>>() {
            @Override
            public void onResponse(Call<List<TimeStampModel>> call, Response<List<TimeStampModel>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                timeStampTableController.tableColumInit(Objects.requireNonNull(response.body()));
            }

            @Override
            public void onFailure(Call<List<TimeStampModel>> call, Throwable t) {
                Toast.makeText(TimeIntervalsTable_Activity.this,
                        t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}