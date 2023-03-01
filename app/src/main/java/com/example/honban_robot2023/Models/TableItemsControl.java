package com.example.honban_robot2023.Models;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.honban_robot2023.R;

import java.util.List;

/**
 * {@link com.example.honban_robot2023.TableBaseActivity}を継承している、
 * テーブルアクティビティで、取得したAPIデータをテーブルにデータを表示させる処理を
 * 担当するコントローラ。
 *  テーブルの1行を作成する {@link #addRowFromModule}を実装したのち、
 *  {@link #tableColumInit}を呼び出すことでテーブルが表示される。
 *
 * @param <T> テーブル1行の表示データを格納したデータクラス。
 */
public abstract class TableItemsControl<T> {

    /**
     * テーブルに表示したいデータのモデルクラス。
     */
    protected List<T> itemModelList;
    /**
     * テーブルが表示されているアクティビティのコンテキスト
     */
    protected Context activityContext;
    /**
     * 内容を表示させる対象のテーブル。
     */
    protected TableLayout tableLayout;
    /**
     * androidシステムから様々なリソースを取得するための
     * リソースオブジェクト
     */
    protected Resources resources;
    /**
     * テーブルの中身の項目の見た目を管理する、レイアウトデータ
     */
    protected TextLayout DEFAULT_COLUM_LAYOUT;
    /**
     * テーブルの表題、項目名の見た目を管理する、レイアウトデータ
     */
    protected TextLayout DEFAULT_TITLE_LAYOUT;

    /**
     * デフォルトのコンストラクタ。テーブルの中身、表題のレイアウトは
     * デフォルトが採用される。
     *
     * @param activityContext
     * @param tableLayout     中身を書き換えるテーブルレイアウト
     */
    public TableItemsControl(Context activityContext, TableLayout tableLayout) {
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

    /**
     * テーブルの表題、項目名を表示させる。
     *
     * @param titles 項目名の文字列の配列。
     *               中身の項目の数と一致させる
     */
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

    /**
     * テーブルの中身を表示させる。
     *
     * @param itemModelList 表示させる中身のデータ。
     *                      1行を表示させるAPIモデルクラスのリスト
     */
    public void tableColumInit(List<T> itemModelList) {
        this.itemModelList = itemModelList;
        for (T item : itemModelList) {
            tableLayout.addView(addRowFromModule(item));
        }
    }

    /**
     * 項目名をそのままに、テーブルの中身のみを書き換える。
     * 絞り込みや並び替えの処理の時に使用する。
     *
     * @param itemModelList 表示させる中身のデータ。
     *                      1行を表示させるAPIモデルクラスのリスト
     */
    public void refresh(List<T> itemModelList) {
        TableRow titleTableRow = (TableRow) tableLayout.getChildAt(0);
        tableLayout.removeAllViews();
        tableLayout.addView(titleTableRow);
        tableColumInit(itemModelList);
    }

    /**
     * テーブルの1行のビューを表示させる処理。この関数を実装することで、
     * {@link #tableColumInit}や {@link #refresh}を呼ぶと自動で
     * テーブルの表示ができる。
     *
     * @param colum テーブルの1行データを作成できるデータクラス
     * @return テーブルの1行データとなる {@link TableRow}
     */
    public abstract TableRow addRowFromModule(T colum);

    /**
     * テキストの見た目を適用させるレイアウトデータをまとめたクラス。
     */
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

    /**
     * テーブルの1項目となる1つの {@link TextView} に、レイアウトデータを適用させる。
     *
     * @param targetTextView レイアウトを適用させる {@link TextView}
     * @param textLayout     適用させるレイアウトデータ
     */
    protected void setColumText(@NonNull TextView targetTextView, TextLayout textLayout) {

        targetTextView.setTextSize(textLayout.textSize);
        targetTextView.setTextColor(textLayout.textColor);
        targetTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        targetTextView.setPadding(textLayout.padding, textLayout.padding, textLayout.padding, textLayout.padding);
    }

    /**
     * テーブルの1項目となる1つの {@link TextView} に、デフォルトのレイアウトデータを適用させる。
     *
     * @param targetTextView レイアウトを適用させる {@link TextView}
     */
    protected void setColumText(@NonNull TextView targetTextView) {
        setColumText(targetTextView, DEFAULT_COLUM_LAYOUT);
    }

    /**
     * テーブルの表題、項目名の1項目の {@link TextView} に、デフォルトのレイアウトデータを適用させる。
     *
     * @param targetTextView レイアウトを適用させる {@link TextView}
     * @param textLayout     適用させるレイアウトデータ
     */
    protected void setTitleText(@NonNull TextView targetTextView, TextLayout textLayout) {
        targetTextView.setTextSize(textLayout.textSize);
        targetTextView.setTextColor(textLayout.textColor);
        targetTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        targetTextView.setPadding(textLayout.padding, textLayout.padding, textLayout.padding, textLayout.padding);
        targetTextView.setBackground(resources.getDrawable(R.drawable.table_title_background, activityContext.getTheme()));
    }

    /**
     * テーブルの表題、項目名の1項目の {@link TextView} に、デフォルトのレイアウトデータを適用させる。
     *
     * @param targetTextView レイアウトを適用させる {@link TextView}
     */
    protected void setTitleText(@NonNull TextView targetTextView) {
        setTitleText(targetTextView, DEFAULT_TITLE_LAYOUT);
    }

}