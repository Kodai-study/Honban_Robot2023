package com.example.honban_robot2023.Models;

import android.content.Context;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.honban_robot2023.APIModules.TimeIntervalAPIModel;
import com.example.honban_robot2023.APIModules.TimeStampModel;
import com.example.honban_robot2023.InspectionTimeTable_Activity;

import java.util.Date;

/**
 * ワークごとの検査の時間に関する情報を表示させる {@link InspectionTimeTable_Activity}
 * のテーブルで、工程ごとにかかった時間の一覧で書き換えを行うコントローラ
 */
public class TimeStampTableController extends TableItemsControl<TimeStampModel> {

    public TimeStampTableController(Context activityContext, TableLayout tableLayout) {
        super(activityContext, tableLayout);
    }


    @Override
    public TableRow addRowFromModule(TimeStampModel colum) {
        TableRow tableRow = new TableRow(this.activityContext);
        TextView[] textViewCells = new TextView[TimeIntervalAPIModel.COLUM_NUMBER];

        for (int i = 0; i < TimeIntervalAPIModel.COLUM_NUMBER; i++) {
            textViewCells[i] = new TextView(activityContext);
            setColumText(textViewCells[i]);
            tableRow.addView(textViewCells[i]);
        }

        textViewCells[0].setText(String.valueOf(colum.getWorkId()));
        textViewCells[1].setText(CommonParameters.DATETIME_FORMATTER.format(colum.getSupply()));
        textViewCells[2].setText(changeTimeToString(colum.getVisual_in()));
        textViewCells[3].setText(changeTimeToString(colum.getFunctional_in()));
        textViewCells[4].setText(changeTimeToString(colum.getAssembly_in()));
        textViewCells[5].setText(changeTimeToString(colum.getAssembly()));

        return tableRow;
    }

    /**
     * APIから取得した日付データから、テーブルに表示させる文字列を作成する
     *
     * @param date 表示させる日付データ
     * @return 実際にテーブルの項目に表示させる、時刻データ (例 11:20:00)
     */
    private String changeTimeToString(Date date) {
        if (date == null)
            return CommonParameters.TABLEDATA_NOTHING;
        else
            return CommonParameters.TIMEONLY_FORMATTER.format(date);
    }

}