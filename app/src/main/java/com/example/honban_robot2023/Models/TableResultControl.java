package com.example.honban_robot2023.Models;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.honban_robot2023.R;

import org.w3c.dom.Text;

import java.util.List;

public abstract class  TableResultControl<T> {

    protected List<T> rowDataList;
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

    protected class TextLayout {
        int textSize = 24;
        int textColor = Color.BLACK;

        Drawable backGroundBorder;

        public TextLayout(int textSize, int textColor) {
            this.textSize = textSize;
            this.textColor = textColor;
           // this.backGroundBorder = activityContext.getDrawable(R.drawable.border_line);
        }
    }

    protected void setColumText(@NonNull TextView targetTextView) {
        targetTextView.setTextSize(DEFAULT_COLUM_LAYOUT.textSize);
        targetTextView.setTextColor(DEFAULT_COLUM_LAYOUT.textColor);
    }

    protected void setTitleText(@NonNull TextView targetTextView) {
        targetTextView.setTextSize(DEFAULT_TITLE_LAYOUT.textSize);
        targetTextView.setTextColor(DEFAULT_TITLE_LAYOUT.textColor);
    }

}