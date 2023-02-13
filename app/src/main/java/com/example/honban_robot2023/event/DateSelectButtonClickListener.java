package com.example.honban_robot2023.event;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateSelectButtonClickListener implements View.OnClickListener{

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    TextView timeInputTextView;

    Context activityContext;

    final private Calendar CURRENT_TIME = Calendar.getInstance();

    public DateSelectButtonClickListener(Context activityContext, TextView timeInputTextView) {
        this.activityContext = activityContext;
        this.timeInputTextView = timeInputTextView;
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
                        Toast.makeText(activityContext, "今日より前を選択してください", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    timeInputTextView.setText(dateFormat.format(selectDate.getTime()));
                },
                CURRENT_TIME.get(Calendar.YEAR),  CURRENT_TIME.get(Calendar.MONTH),  CURRENT_TIME.get(Calendar.DATE));
        // 表示
        datePicker.show();
    }
}