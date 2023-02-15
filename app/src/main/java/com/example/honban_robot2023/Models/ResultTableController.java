package com.example.honban_robot2023.Models;

import android.content.Context;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.honban_robot2023.APIModules.SampleAPIModel;

public class ResultTableController extends TableResultControl<SampleAPIModel>{


    public ResultTableController(Context activityContext, TableLayout tableLayout) {
        super(activityContext, tableLayout);
    }

    @Override
    public TableRow addRowFromModule(SampleAPIModel colum) {
        TableRow tableRow = new TableRow(activityContext);
        TextView[] textViewCells = new TextView[5];
        for (int i = 0; i < 5; i++) {
            textViewCells[i] = new TextView(activityContext);
            setColumText(textViewCells[i]);
        }

        textViewCells[0].setText(String.valueOf(colum.getUserId()));
        textViewCells[1].setText(String.valueOf(colum.getId()));
        textViewCells[2].setText(String.valueOf(colum.getTitle()));
        textViewCells[3].setText(String.valueOf(colum.getSubTitle()));

        for(TextView textView : textViewCells){
            tableRow.addView(textView);
        }
        return tableRow;
    }

    @Override
    protected void createTableRow(SampleAPIModel colum) {

    }
}
