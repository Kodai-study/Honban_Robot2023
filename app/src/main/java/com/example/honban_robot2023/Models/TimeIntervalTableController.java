package com.example.honban_robot2023.Models;

import android.content.Context;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.honban_robot2023.APIModules.TimeIntervalAPIModel;
import com.example.honban_robot2023.APIModules.TimeIntervalAPIModel;
import com.example.honban_robot2023.APIModules.TimeIntervalAPIModel;

import java.util.Date;

public class TimeIntervalTableController extends TableResultControl<TimeIntervalAPIModel> {

    public TimeIntervalTableController(Context activityContext, TableLayout tableLayout) {
        super(activityContext, tableLayout);
    }

    

    @Override
    public TableRow addRowFromModule(TimeIntervalAPIModel colum) {
        TableRow tableRow = new TableRow(this.activityContext);
        TextView[] textViewCells = new TextView[TimeIntervalAPIModel.COLUM_NUMBER];

        for (int i = 0; i < TimeIntervalAPIModel.COLUM_NUMBER; i++) {
            textViewCells[i] = new TextView(activityContext);
            setColumText(textViewCells[i]);
            tableRow.addView(textViewCells[i]);
        }

        textViewCells[0].setText(String.valueOf(colum.getCycleID()));
        textViewCells[1].setText(ConfigParameters.DATETIME_FORMATTER.format(colum.getStartTime()));
        textViewCells[2].setText(changeTimeToString(colum.getTime_supply()));
        textViewCells[3].setText(changeTimeToString(colum.getTime_visualStation()));
        textViewCells[4].setText(changeTimeToString(colum.getTime_functionalStation()));
        textViewCells[5].setText(changeTimeToString(colum.getTime_assemblyStation()));

        return tableRow;
    }

    private String changeTimeToString(Date date){
        if(date == null)
            return ConfigParameters.TABLEDATA_NOTHING;
        else
            return ConfigParameters.TIMEONLY_FORMATTER.format(date);
    }



}