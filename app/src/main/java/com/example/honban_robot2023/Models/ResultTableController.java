package com.example.honban_robot2023.Models;

import android.content.Context;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.honban_robot2023.APIModules.ResultAPI.FunctionInspectionResults;
import com.example.honban_robot2023.APIModules.ResultAPI.ResultsDataModel;
import com.example.honban_robot2023.APIModules.ResultAPI.VisualInspectionResults;
import com.example.honban_robot2023.R;

/**
 * ワークごとの検査結果一覧を表示させる {@link com.example.honban_robot2023.ResultTable_Activity}
 * のテーブルの書き換えを行うコントローラ
 */
public class ResultTableController extends TableItemsControl<ResultsDataModel> {

    /**
     * 結果がOKだった時に、テーブルの項目に表示させる文字列
     */
    private String ok_String;
    /**
     * 結果がNGだった時に、テーブルの項目に表示させる文字列
     */
    private String ng_String;
    /**
     * 結果がNULL(検査していない)だった時に、テーブルの項目に表示させる文字列
     */
    private String null_String;

    /**
     * OK表示の文字列に適用させる、文字のカラー
     */
    private int ok_Color;

    /**
     * NG表示の文字列に適用させる、文字のカラー
     */
    private int ng_Color;

    /**
     * @see TableItemsControl
     */
    public ResultTableController(Context activityContext, TableLayout tableLayout) {
        super(activityContext, tableLayout);
        ok_Color = resources.getColor(R.color.OK, activityContext.getTheme());
        ng_Color = resources.getColor(R.color.NG, activityContext.getTheme());
        null_String = resources.getString(R.string.NULL);
        ok_String = resources.getString(R.string.OK);
        ng_String = resources.getString(R.string.NG);
    }


    @Override
    public TableRow addRowFromModule(ResultsDataModel colum) {
        TableRow tableRow = new TableRow(activityContext);
        TextView[] textViewCells = new TextView[ResultsDataModel.COLUM_NUMBER];
        for (int i = 0; i < ResultsDataModel.COLUM_NUMBER; i++) {
            textViewCells[i] = new TextView(activityContext);
        }

        VisualInspectionResults workResults = colum.getResult_visualInspection();
        FunctionInspectionResults functionResults = colum.getResult_functionalInspection();
        if (colum.getStartTime() == null)
            textViewCells[0].setText(CommonParameters.TABLEDATA_NOTHING);
        else
            textViewCells[0].setText(CommonParameters.DATETIME_FORMATTER.format(colum.getStartTime()));

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

        textViewCells[19].setText(resultCodeToString(functionResults.getVoltage_result()));

        if (functionResults.getVoltage_value() < 0)

            textViewCells[20].setText(null_String);
        else
            textViewCells[20].setText(String.format("%.2fV", functionResults.getVoltage_value()));

        textViewCells[21].setText(resultCodeToString(functionResults.getFrequency_result()));

        if (functionResults.getFrequency_value() < 0)
            textViewCells[22].setText(null_String);
        else
            textViewCells[22].setText(String.format("%dHz", functionResults.getFrequency_value()));

        if (colum.getCycleTime() == null)
            textViewCells[23].setText(CommonParameters.TABLEDATA_NOTHING);
        else
            textViewCells[23].setText(CommonParameters.TIMEONLY_FORMATTER.format(colum.getCycleTime()));


        for (TextView textView : textViewCells) {
            tableRow.addView(textView);
            setColumText(textView);
        }
        return tableRow;
    }


    @Override
    protected void setColumText(@NonNull TextView targetTextView) {
        super.setColumText(targetTextView);
        if (targetTextView.getText().toString().equals(ok_String)) {
            targetTextView.setTextColor(ok_Color);
        } else if (targetTextView.getText().toString().equals(ng_String)) {
            targetTextView.setTextColor(ng_Color);
        }
    }

    /**
     * APIから取得したデータで、OK,NGのデータに、androidリソースで
     * 指定した文字列に置き換える
     *
     * @param character 書き換え対象の文字データ。
     * @return 実際にテーブルに表示させる、androidリソースの文字列
     */
    protected String resultCodeToString(Character character) {
        Character okCharacter = '〇';
        Character ngCharacter = '×';
        if (character.equals(okCharacter)) {
            return ok_String;
        } else if (character.equals(ngCharacter)) {
            return ng_String;
        } else {
            return CommonParameters.TABLEDATA_NOTHING;
        }
    }

}