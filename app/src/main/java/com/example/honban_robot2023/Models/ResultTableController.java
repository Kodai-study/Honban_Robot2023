package com.example.honban_robot2023.Models;

import android.content.Context;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.honban_robot2023.APIModules.ResultAPI.ResultsDataModel;
import com.example.honban_robot2023.APIModules.ResultAPI.VisualInspectionResults;

public class ResultTableController extends TableResultControl<ResultsDataModel> {


    private String ok_String = null;
    private String ng_String = null;


    public ResultTableController(Context activityContext, TableLayout tableLayout) {
        super(activityContext, tableLayout);
    }

    @Override
    public TableRow addRowFromModule(ResultsDataModel colum) {
        TableRow tableRow = new TableRow(activityContext);
        TextView[] textViewCells = new TextView[ResultsDataModel.COLUM_NUMBER];
        for (int i = 0; i < ResultsDataModel.COLUM_NUMBER; i++) {
            textViewCells[i] = new TextView(activityContext);
            setColumText(textViewCells[i]);
        }

        VisualInspectionResults workResults = colum.getResult();
        if (colum.getStartTime() == null)
            textViewCells[0].setText(ConfigParameters.TABLEDATA_NOTHING);
        else
            textViewCells[0].setText(ConfigParameters.DATETIME_FORMATTER.format(colum.getStartTime()));

        textViewCells[1].setText(resultCodeToString(colum.getAllResult()));

        textViewCells[2].setText(String.valueOf(colum.getWorkID()));
        textViewCells[3].setText(resultCodeToString(workResults.getIc().getAllResult()));
        textViewCells[4].setText(resultCodeToString(workResults.getIc().getiC1_dir()));
        textViewCells[5].setText(resultCodeToString(workResults.getIc().getiC2_dir()));
        textViewCells[6].setText(resultCodeToString(workResults.getIc().getiC1_have()));
        textViewCells[7].setText(resultCodeToString(workResults.getIc().getiC2_have()));
        textViewCells[8].setText(resultCodeToString(workResults.getWork().getAllResult()));
        textViewCells[9].setText(resultCodeToString(workResults.getWork().getDir()));
        textViewCells[10].setText(resultCodeToString(workResults.getWork().getIs_OK()));
        textViewCells[11].setText(resultCodeToString(workResults.getR().getAllResult()));
        textViewCells[12].setText(resultCodeToString(workResults.getR().getR05()));
        textViewCells[13].setText(resultCodeToString(workResults.getR().getR10()));
        textViewCells[14].setText(resultCodeToString(workResults.getR().getR11()));
        textViewCells[15].setText(resultCodeToString(workResults.getR().getR12()));
        textViewCells[16].setText(resultCodeToString(workResults.getR().getR18()));
        textViewCells[17].setText(resultCodeToString(workResults.getDipSw().getAllResult()));
        textViewCells[18].setText(workResults.getDipSw().getPattern());

        if (colum.getCycleTime() == null)
            textViewCells[19].setText(ConfigParameters.TABLEDATA_NOTHING);
        else
            textViewCells[19].setText(ConfigParameters.TIMEONLY_FORMATTER.format(colum.getCycleTime()));


        for (TextView textView : textViewCells) {
            tableRow.addView(textView);
        }
        return tableRow;
    }

    @Override
    protected void createTableRow(ResultsDataModel colum) {

    }


    protected String resultCodeToString(Character character) {
        if (ok_String == null) {
            //TODO アンドロイドのリソースから取ってくる
            ok_String = "OK";
            ng_String = "NG";
        }
        Character okCharactor = '〇';
        Character ngCharactor = '×';
        if (character.equals(okCharactor)) {
            return ok_String;
        } else if (character.equals(ngCharactor)) {
            return ng_String;
        } else {
            return ConfigParameters.TABLEDATA_NOTHING;
        }
    }

}