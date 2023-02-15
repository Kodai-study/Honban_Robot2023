package com.example.honban_robot2023.Models;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.honban_robot2023.APIModules.SampleAPIModel;
import com.example.honban_robot2023.R;


public class RowDataViews {

    static final int COL_TEXT_SIZE = 24;
    private static boolean isSetLayoutParam = false;
    private Context activityContext;
    private TextView userIdText;
    private TextView idText;
    private TextView titleText;
    private TextView subTitleText;

    /**
     * テーブルの行の、1つ目の要素に適応させるレイアウトデータ
     */
    final static TableRow.LayoutParams FIRST_TEXT_LAYOUT_PARAMS = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT);

    /**
     * テーブルの行の、1つ目以降の要素に適応させるレイアウトデータ
     */
    final static TableRow.LayoutParams TEXT_LAYOUT_PARAMS = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT);


    public RowDataViews(Context activityContext, SampleAPIModel model) {

        if (!isSetLayoutParam)
            setLayoutParams();

        this.activityContext = activityContext;
        userIdText = new TextView(activityContext);
        userIdText.setLayoutParams(FIRST_TEXT_LAYOUT_PARAMS);
        userIdText.setText("" + model.getUserId());
        userIdText.setTextSize(COL_TEXT_SIZE);
        userIdText.setTextColor(Color.WHITE);
        userIdText.setTextColor(activityContext.getResources().getColor(R.color.black, activityContext.getTheme()));

        idText = new TextView(activityContext);
        idText.setLayoutParams(TEXT_LAYOUT_PARAMS);
        idText.setText("" + model.getId());
        idText.setTextSize(COL_TEXT_SIZE);

        titleText = new TextView(activityContext);
        titleText.setLayoutParams(TEXT_LAYOUT_PARAMS);
        titleText.setText(model.getTitle());
        titleText.setTextSize(COL_TEXT_SIZE);

        subTitleText = new TextView(activityContext);
        subTitleText.setLayoutParams(TEXT_LAYOUT_PARAMS);
        subTitleText.setText(model.getSubTitle());
        subTitleText.setTextSize(COL_TEXT_SIZE);
    }

    private void setLayoutParams() {
        TEXT_LAYOUT_PARAMS.leftMargin = 100;
        FIRST_TEXT_LAYOUT_PARAMS.leftMargin = 40;
        isSetLayoutParam = true;
    }

    public TextView getUserIdText() {
        return userIdText;
    }

    public TextView getIdText() {
        return idText;
    }

    public TextView getTitleText() {
        return titleText;
    }

    public TextView getSubTitleText() {
        return subTitleText;
    }
}