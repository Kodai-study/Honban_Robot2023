package com.example.honban_robot2023;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterButton;

import com.example.honban_robot2023.APIModules.APIManager;
import com.example.honban_robot2023.Models.RetrofitFactory;
import com.example.honban_robot2023.Models.TableItemsControl;
import com.example.honban_robot2023.event.DateSelectButtonClickListener;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 検査結果の一覧表を表示するアクティビティ
 */
public abstract class TableBaseActivity extends AppCompatActivity {

    /**
     * 一覧表を表示するテーブルレイアウト
     */
    protected TableLayout resultTable;

    /**
     * 設定項目を、アプリ終了後も引き継ぐためのデータアクセサ
     */
    protected SharedPreferences saveSearchSettings;

    /**
     * 検査日時の範囲の下限値を設定するカレンダーダイアログを表示するボタン
     * {@link #firstDateInput}
     */
    protected ImageFilterButton firstDateSelect;
    /**
     * 検査日時の範囲の上限値を設定するカレンダーダイアログを表示するボタン
     * {@link #lastDateInput}
     */
    protected ImageFilterButton lastDateSelect;
    /**
     * 表示する結果を検査日時で範囲指定する時の下限値を入力する
     * テキストボックス。ダイアログによって自動で入力される
     */
    protected EditText firstDateInput = null;
    /**
     * 表示する結果を検査日時で範囲指定する時の上限値を入力する
     * テキストボックス。ダイアログによって自動で入力される
     */
    protected EditText lastDateInput = null;

    /**
     * APIによってデータを取得するAPIを集めたクラス
     */
    protected APIManager retrofitApi;

    /**
     *  テーブルの項目を切り替えるコントローラ。テーブルの種類によって、
     * {@link TableItemsControl}を継承したそれぞれのコントローラを割り当てる。
     */
    protected TableItemsControl tableController;

    /**
     * 日付入力部分の横にある検索ボタン。このボタンを押すと、
     * 検査日付による絞り込みが行われる
     */
    protected ImageFilterButton searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_table);
        resultTable = findViewById(R.id.table_resultDatas);
        resultTable.setBackgroundColor(Color.WHITE);
        firstDateSelect = findViewById(R.id.imageButton_selectFirstTime);
        lastDateSelect = findViewById(R.id.imageButton_selectLastTime);
        firstDateInput = findViewById(R.id.editText_firstDate);
        lastDateInput = findViewById(R.id.editText_lastDate);
        searchButton = findViewById(R.id.imageButton_sortByDateRange);

        /* 日付入力ボックスの横にあるボタンをクリックすると、日付入力のダイアログが表示される */
        firstDateSelect.setOnClickListener(new DateSelectButtonClickListener(this, firstDateInput));
        lastDateSelect.setOnClickListener(new DateSelectButtonClickListener(this, lastDateInput, firstDateInput));
        retrofitApi = RetrofitFactory.getApiClient("https://192.168.96.69:7015/api/").create(APIManager.class);
    }

    /**
     * テーブルの中身、項目以外の部分をセットする
     * @param list APIで取得したデータのリスト。
     *  {@link #retrofitApi}から取得した、Callオブジェクトのまま
     * @param <T> 取得したい型の、APIのデータをまとめたモデルクラス。
     */
    protected <T> void setTableBody(Call<List<T>> list) {
        /* APIデータの取得が成功したらテーブル項目の更新、失敗したらトーストの表示 */
        list.enqueue(new Callback<List<T>>() {
            @Override
            public void onResponse(@NonNull Call<List<T>> call, @NonNull Response<List<T>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                tableController.tableColumInit(Objects.requireNonNull(response.body()));
            }

            @Override
            public void onFailure(@NonNull Call<List<T>> call, @NonNull Throwable t) {
                Toast.makeText(TableBaseActivity.this,
                        t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * テーブルの一番上、項目名のみを残してテーブルの中身のみを切り替える。
     * @param list APIで取得したデータのリスト。
     *  {@link #retrofitApi}から取得した、Callオブジェクトのまま
     * @param <T> 取得したい型の、APIのデータをまとめたモデルクラス。
     */
    protected <T> void refreshTable(Call<List<T>> list) {
        list.enqueue(new Callback<List<T>>() {
            @Override
            public void onResponse(@NonNull Call<List<T>> call, @NonNull Response<List<T>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                tableController.refresh(Objects.requireNonNull(response.body()));
            }

            @Override
            public void onFailure(@NonNull Call<List<T>> call, @NonNull Throwable t) {
                Toast.makeText(TableBaseActivity.this,
                        t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_table_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * {@link #firstDateInput}日付入力部で入力した、検査日付絞り込みの下限値
     * を、クエリ文字列として取得する。
     * @return 日付指定で、クエリとして表示する文字列。
     *  入力部が空なら、null
     */
    protected String getFirstDate() {
        String firstDateInputText = firstDateInput.getText().toString();
        if (firstDateInputText.equals(""))
            return null;
        return firstDateInputText;
    }

    /**
     * {@link #lastDateInput}日付入力部で入力した、検査日付絞り込みの上限値
     * を、クエリ文字列として取得する
     * @return 日付指定で、クエリとして表示する文字列。
     *  入力部が空なら、null
     */
    protected String getLastDate() {
        String lastDateInputText = lastDateInput.getText().toString();
        if (lastDateInputText.equals(""))
            return null;
        return lastDateInputText;
    }

}