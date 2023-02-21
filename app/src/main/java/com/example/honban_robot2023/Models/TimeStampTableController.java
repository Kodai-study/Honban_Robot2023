package com.example.honban_robot2023.Models;

import android.content.Context;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.honban_robot2023.APIModules.TimeIntervalAPIModel;
import com.example.honban_robot2023.APIModules.TimeStampModel;

import java.util.Date;

public class TimeStampTableController extends TableResultControl<TimeStampModel> {

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
        textViewCells[1].setText(ConfigParameters.DATETIME_FORMATTER.format(colum.getSupply()));
        textViewCells[2].setText(changeTimeToString(colum.getVisual_in()));
        textViewCells[3].setText(changeTimeToString(colum.getFunctional_in()));
        textViewCells[4].setText(changeTimeToString(colum.getAssembly_in()));
        textViewCells[5].setText(changeTimeToString(colum.getAssembly()));

        return tableRow;
    }

    private String changeTimeToString(Date date) {
        if (date == null)
            return ConfigParameters.TABLEDATA_NOTHING;
        else
            return ConfigParameters.TIMEONLY_FORMATTER.format(date);
    }
}