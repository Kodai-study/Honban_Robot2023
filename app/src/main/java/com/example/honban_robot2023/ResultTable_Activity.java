package com.example.honban_robot2023;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterButton;

import com.example.honban_robot2023.APIModules.APIManager;
import com.example.honban_robot2023.APIModules.ResultAPI.ResultsDataModel;
import com.example.honban_robot2023.APIModules.SampleAPIModel;
import com.example.honban_robot2023.Fragment.ResultTableSettingDialog;
import com.example.honban_robot2023.Models.ResultTableController;
import com.example.honban_robot2023.Models.SampleTableController;
import com.example.honban_robot2023.Models.RetrofitFactory;
import com.example.honban_robot2023.Models.TableResultControl;
import com.example.honban_robot2023.Test.TestFetchAPI;
import com.example.honban_robot2023.event.DateSelectButtonClickListener;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * 検査結果の一覧表を表示するアクティビティ
 */
public class ResultTable_Activity extends TableBaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tableController = new ResultTableController(this, this.resultTable);
        tableController.setTableTitle(new String[]{"fae","fae","fae","fae","fae","fae","fae","fae","fae","fae","fae",
                "fae","fae","fae","fae","fae","fae","fae","fae"});
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
        new ResultTableSettingDialog().show(getSupportFragmentManager(), "dialog");
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