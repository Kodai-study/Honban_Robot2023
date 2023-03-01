package com.example.honban_robot2023.event;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 * {@link com.example.honban_robot2023.TableBaseActivity}のテーブル表示で、検査日付で表示の
 * 絞り込みを行うときの、日付を入力させるカレンダーダイアログを表示するボタン
 */
public class DateSelectButtonClickListener implements View.OnClickListener{

    /**
     * カレンダーダイアログで取得した日付データを、テキストボックス入力させるフォーマッター
     */
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
    /**
     * 対象となる、日付を入力させるテキストボックス。
     */
    TextView timeInputTextView;
    /**
     * (対象のテキストボックスが、選択期限の下限値だった時はNULL)
     * 上限値を入力させるとき、下限値が入力されているテキストボックス。
     * このテキストボックスに入力されている日付より後の日付のみが有効になる。
     */
    TextView beforeTimeInputTextView = null;

    /**
     * 絞り込み先のテーブルが表示されているアクティビティ
     */
    Context activityContext;
    final private Calendar CURRENT_TIME = Calendar.getInstance();

    /**
     * 絞り込み期間の下限値を入力させるときのクリック処理。
     * @param activityContext 元のアクティビティデータ
     * @param timeInputTextView ダイアログを選択したときに書き込まれるテキストボックス
     */
    public DateSelectButtonClickListener(Context activityContext, TextView timeInputTextView) {
        this.activityContext = activityContext;
        this.timeInputTextView = timeInputTextView;
    }

    /**
     *
     * 絞り込み期間の上限値を入力させるときのクリック処理。
     * @param activityContext 元のアクティビティデータ
     * @param timeInputTextView ダイアログを選択したときに書き込まれるテキストボックス
     * @param beforeTimeInputTextView 下限値が入力されているテキストボックス。この日付より後のみ入力できる
     */
    public DateSelectButtonClickListener(Context activityContext, TextView timeInputTextView,TextView beforeTimeInputTextView) {
        this.activityContext = activityContext;
        this.timeInputTextView = timeInputTextView;
        this.beforeTimeInputTextView = beforeTimeInputTextView;
    }



    @Override
    public void onClick(View v) {
        DatePickerDialog datePicker = new DatePickerDialog(
                activityContext,
                (view1, year, monthOfYear, dayOfMonth) -> {
                    Calendar selectDate = Calendar.getInstance();
                    selectDate.clear();
                    selectDate.set(year, monthOfYear, dayOfMonth);
                    if(selectDate.after(CURRENT_TIME)){
                        Toast.makeText(activityContext, "今日より前の日付を選択してください", Toast.LENGTH_SHORT).show();
                        return;
                    } else if(beforeTimeInputTextView != null){
                        Calendar beforeDate = null;
                        try {
                            Date beforeDateText;
                            beforeDateText = dateFormat.parse(beforeTimeInputTextView.getText().toString());
                            beforeDate = Calendar.getInstance();
                            beforeDate.setTime(Objects.requireNonNull(beforeDateText));
                        } catch (ParseException e) {
                            Toast.makeText(activityContext, "下限に設定した日付が間違っている可能性があります", Toast.LENGTH_SHORT).show();
                        }
                        if(beforeDate != null && selectDate.before(beforeDate)){
                            Toast.makeText(activityContext, "最小日付より後の日付を設定してください", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    timeInputTextView.setText(dateFormat.format(selectDate.getTime()));
                },
                CURRENT_TIME.get(Calendar.YEAR),  CURRENT_TIME.get(Calendar.MONTH),  CURRENT_TIME.get(Calendar.DATE));
        datePicker.show();
    }
}