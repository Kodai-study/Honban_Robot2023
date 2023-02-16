package com.example.honban_robot2023.Models;

import android.content.Context;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.honban_robot2023.APIModules.ConfigParameters;
import com.example.honban_robot2023.APIModules.ResultAPI.ResultsDataModel;
import com.example.honban_robot2023.APIModules.ResultAPI.VisualInspectionResults;

public class ResultTableController extends TableResultControl<ResultsDataModel> {


    public ResultTableController(Context activityContext, TableLayout tableLayout) {
        super(activityContext, tableLayout);
    }

    @Override
    public TableRow addRowFromModule(ResultsDataModel colum) {
        TableRow tableRow = new TableRow(activityContext);
        TextView[] textViewCells = new TextView[10];
        for (int i = 0; i < textViewCells.length; i++) {
            textViewCells[i] = new TextView(activityContext);
            setColumText(textViewCells[i]);
        }

        VisualInspectionResults workResults = colum.getResult();

        textViewCells[0].setText(colum.getStartTime() != null ?
                colum.getStartTime().toString() : ConfigParameters.TABLEDATA_NOTHING);
        textViewCells[1].setText(colum.getAllResult().toString());
        textViewCells[2].setText(String.valueOf(colum.getWorkID()));
        textViewCells[3].setText(workResults.getWork().getAllResult().toString());
        textViewCells[4].setText(workResults.getR().getAllResult().toString());
        textViewCells[5].setText(workResults.getDipSw().getAllResult().toString());


        for (TextView textView : textViewCells) {
            tableRow.addView(textView);
        }
        return tableRow;
    }

    @Override
    protected void createTableRow(ResultsDataModel colum) {

    }

}