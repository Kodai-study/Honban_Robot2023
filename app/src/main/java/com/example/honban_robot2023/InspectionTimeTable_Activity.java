package com.example.honban_robot2023;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;

import com.example.honban_robot2023.APIModules.TimeStampModel;
import com.example.honban_robot2023.Models.CommonParameters;
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
 * ワークごとの検査工程それぞれにかかった時間と、
 * 工程の開始、終了時刻の表示を行うアクティビティ。
 * {@link #tableSwitchToggle}のスイッチを切り替えることで、
 * 工程時間:OFF  と  工程切り替え時間:ON  で表示が切り替わる
 */
public class InspectionTimeTable_Activity extends TableBaseActivity {

    /**
     * 表示項目を切り替える、メニューバーの中のトグルスイッチ。
     * スイッチ操作時に、 工程時間:OFF  と  工程切り替え時間:ON
     * が切り替わる
     */
    SwitchCompat tableSwitchToggle;

    /**
     * 工程切り替え時間で表示する時のテーブル表示のコントローラ。
     * デフォルトは工程時間の{@link TimeIntervalTableController}
     */
    TableItemsControl<TimeStampModel> timeStampTableController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tableController = new TimeIntervalTableController(this, this.resultTable);
        this.timeStampTableController = new TimeStampTableController(this, this.resultTable);
        tableController.setTableTitle(getResources().getStringArray(R.array.tableTitle_TimeInterval));

        /* デバッグモードでは、APIからではなく一定のサンプルデータを使ってテーブルの表示 */
        if (CommonParameters.IS_API_DEBUG_MODE)
            tableController.tableColumInit(Test_dummyAPIData.getTimeIntervalDummy());
        else
            setTableBody(this.retrofitApi.getTimeIntervalData());

        /* 日付入力画面横のボタンで、日付による絞り込みを行う。 ダイアログの設定項目を引き継ぐ */
        searchButton.setOnClickListener(view -> {
            updateTable();
        });
    }

    /**
     * 絞り込み、並び替え条件を指定してテーブルを作成しなおし、再描写を行う。
     * {@link #tableSwitchToggle}の値によって、表示項目が変わる
     */
    public void updateTable() {
        if (tableSwitchToggle.isChecked()) {
            resultTable.removeAllViews();
            setTimeStampTable();
        } else
            refreshTable(retrofitApi.getTimeIntervalDataWithSearch(getFirstDate(), getLastDate()));
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        boolean superResult = super.onCreateOptionsMenu(menu);  //スーパークラスの処理と戻り値を引き継ぐ
        /* メニューバーの中ののスイッチを表示させる */
        MenuItem switchItem = menu.findItem(R.id.app_bar_switch2);
        switchItem.setVisible(true);
        this.tableSwitchToggle = (SwitchCompat) switchItem.getActionView();

        /* スイッチの操作に、表示項目が切り替わる処理を割り当てる。 */
        tableSwitchToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            switchTableKind(isChecked);
        });
        return superResult;
    }

    /**
     * 表示項目を工程時間と工程切り替え時間で切り替える。
     *
     * @param isViewTimeStamp true:工程切り替え時間
     *                        false:工程時間 {@link #tableSwitchToggle}の値に等しい
     */
    private void switchTableKind(boolean isViewTimeStamp) {
        resultTable.removeAllViews();
        if (isViewTimeStamp) {
            setTimeStampTable();
            return;
        }
        tableController.setTableTitle(getResources().getStringArray(R.array.tableTitle_TimeStamp));
        super.setTableBody(this.retrofitApi.getTimeIntervalDataWithSearch(getFirstDate(), getLastDate()));
    }

    /**
     * タイムスタンプ、工程切り替わり時刻のデータをテーブルの項目に表示する。
     * デフォルトは工程時間の{@link #tableController}
     */
    private void setTimeStampTable() {

        tableController.setTableTitle(getResources().getStringArray(R.array.tableTitle_TimeStamp));
        Call<List<TimeStampModel>> timeStamps = retrofitApi.getTimeStampDataWithSearch(getFirstDate(), getLastDate());
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
                Toast.makeText(InspectionTimeTable_Activity.this,
                        t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}