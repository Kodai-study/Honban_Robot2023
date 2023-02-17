package com.example.honban_robot2023.Models;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

import com.example.honban_robot2023.R;

import java.util.List;

public abstract class  TableResultControl<T> {

    protected List<T> itemModelList;
    protected Context activityContext;

    protected TableLayout tableLayout;

    protected Resources resources;

    protected TextLayout DEFAULT_COLUM_LAYOUT;

    protected TextLayout DEFAULT_TITLE_LAYOUT;


    public TableResultControl(Context activityContext, TableLayout tableLayout) {
        this.activityContext = activityContext;
        this.tableLayout = tableLayout;
        this.resources = activityContext.getResources();
        DEFAULT_COLUM_LAYOUT = new TextLayout(resources.getInteger(R.integer.tableTitle_textSize),
                resources.getColor(R.color.title, activityContext.getTheme()),
                resources.getInteger(R.integer.tableTitle_paddingAll));

        DEFAULT_TITLE_LAYOUT = new TextLayout(resources.getInteger(R.integer.tableColum_textSize),
                resources.getColor(R.color.title, activityContext.getTheme()),
                resources.getInteger(R.integer.tableTitle_paddingAll));
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

        int padding = 20;

        public TextLayout(int textSize, int textColor, int padding) {
            this.textSize = textSize;
            this.textColor = textColor;
            this.padding = padding;
        }

        public TextLayout() {
        }
    }

    protected void setColumText(@NonNull TextView targetTextView, TextLayout textLayout) {

        targetTextView.setTextSize(textLayout.textSize);
        targetTextView.setTextColor(textLayout.textColor);
        targetTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        targetTextView.setPadding(textLayout.padding, textLayout.padding, textLayout.padding, textLayout.padding);
    }

    protected void setColumText(@NonNull TextView targetTextView) {
        setColumText(targetTextView, DEFAULT_COLUM_LAYOUT);
    }

    protected void setTitleText(@NonNull TextView targetTextView, TextLayout textLayout) {
        targetTextView.setTextSize(textLayout.textSize);
        targetTextView.setTextColor(textLayout.textColor);
        targetTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        targetTextView.setPadding(textLayout.padding, textLayout.padding, textLayout.padding, textLayout.padding);
        targetTextView.setBackground(resources.getDrawable(R.drawable.midasi, activityContext.getTheme()));
    }

    protected void setTitleText(@NonNull TextView targetTextView) {
        setTitleText(targetTextView, DEFAULT_TITLE_LAYOUT);
    }


}