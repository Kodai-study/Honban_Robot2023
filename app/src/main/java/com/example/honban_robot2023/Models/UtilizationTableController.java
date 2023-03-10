package com.example.honban_robot2023.Models;

import android.content.Context;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.honban_robot2023.APIModules.TimeIntervalAPIModel;
import com.example.honban_robot2023.APIModules.UtilizationModel;

/**
 * ロボットの稼働状態を表示させる {@link com.example.honban_robot2023.UtilizationTable_Activity} のテーブルの
 * 書き換えを行うコントローラ
 */
public class UtilizationTableController extends TableItemsControl<UtilizationModel> {

    public UtilizationTableController(Context activityContext, TableLayout tableLayout) {
        super(activityContext, tableLayout);
    }

    @Override
    public TableRow addRowFromModule(UtilizationModel colum) {
        TableRow tableRow = new TableRow(this.activityContext);
        TextView[] textViewCells = new TextView[TimeIntervalAPIModel.COLUM_NUMBER];

        for (int i = 0; i < TimeIntervalAPIModel.COLUM_NUMBER; i++) {
            textViewCells[i] = new TextView(activityContext);
            setColumText(textViewCells[i]);
            tableRow.addView(textViewCells[i]);
        }

        textViewCells[0].setText(CommonParameters.DATEONLY_FORMATTER.format(colum.getCurrentDate()));
        textViewCells[1].setText(CommonParameters.TIMEONLY_FORMATTER.format(colum.getTimeOfOperation()));
        textViewCells[2].setText(CommonParameters.TIMEONLY_FORMATTER.format(colum.getTimeOfStopSum()));
        textViewCells[3].setText(CommonParameters.TIMEONLY_FORMATTER.format(colum.getTimeOfSupplyPause()));
        textViewCells[4].setText(CommonParameters.TIMEONLY_FORMATTER.format(colum.getTimeOfPause()));

        return tableRow;
    }

}