package com.example.honban_robot2023.Models;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public abstract class  TableResultControl<T> {

    protected List<T> itemModelList;
    protected Context activityContext;

    protected TableLayout tableLayout;

    protected final TextLayout DEFAULT_COLUM_LAYOUT = new TextLayout(24, Color.BLACK);

    protected final TextLayout DEFAULT_TITLE_LAYOUT = new TextLayout(30, Color.RED);

    public TableResultControl(Context activityContext, TableLayout tableLayout) {
        this.activityContext = activityContext;
        this.tableLayout = tableLayout;
    }

    public void setTableTitle(String[] titles) {
        TableRow titleRow = new TableRow(activityContext);
        for (String e : titles) {
            TextView titleCel = new TextView(activityContext);
            setTitleText(titleCel);
            titleCel.setText(e);
            titleRow.addView(titleCel);
        }
        tableLayout.addView(titleRow);
    }

    public void tableInit(List<T> itemModelList) {
        this.itemModelList = itemModelList;
        for (T item : itemModelList) {
            tableLayout.addView(addRowFromModule(item));
        }
    }

    public void refresh(List<T> items) {
        TableRow titleTableRow = (TableRow) tableLayout.getChildAt(0);
        tableLayout.removeAllViews();
        tableLayout.addView(titleTableRow);
        tableInit(items);
    }

    public abstract TableRow addRowFromModule(T colum);

    protected abstract void createTableRow(T colum);

    protected static class TextLayout {
        int textSize = 24;
        int textColor = Color.BLACK;

        Drawable backGroundBorder;

        public TextLayout(int textSize, int textColor) {
            this.textSize = textSize;
            this.textColor = textColor;
        }
        public TextLayout(){}
    }

    protected void setColumText(@NonNull TextView targetTextView,TextLayout textLayout) {
        targetTextView.setTextSize(textLayout.textSize);
        targetTextView.setTextColor(textLayout.textColor);
    }

    protected void setColumText(@NonNull TextView targetTextView) {
        setColumText(targetTextView,DEFAULT_COLUM_LAYOUT);
    }

    protected void setTitleText(@NonNull TextView targetTextView,TextLayout textLayout) {
        targetTextView.setTextSize(textLayout.textSize);
        targetTextView.setTextColor(textLayout.textColor);
    }

    protected void setTitleText(@NonNull TextView targetTextView) {
        setTitleText(targetTextView,DEFAULT_TITLE_LAYOUT);
    }


}