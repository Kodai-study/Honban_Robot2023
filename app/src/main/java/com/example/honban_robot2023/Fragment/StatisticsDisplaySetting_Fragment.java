package com.example.honban_robot2023.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.honban_robot2023.R;
import com.example.honban_robot2023.StatisticsTable_Activity;


public class StatisticsDisplaySetting_Fragment extends DialogFragment {

    RadioGroup sortColumSelect;
    StatisticsTable_Activity baseActivity;
    ToggleButton selectSortMethod;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final Activity activity = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final View dialogView = LayoutInflater.from(activity).inflate(R.layout.fragment_statistics, null);
        builder.setView(dialogView);
        sortColumSelect = dialogView.findViewById(R.id.radioGroup2);
        selectSortMethod = dialogView.findViewById(R.id.ASC_sta);
        builder.setPositiveButton("適用", (dialog, which) -> {

            String sortColum = null;
            switch (sortColumSelect.getCheckedRadioButtonId()) {
                case R.id.orderBy_date:
                    break;
                case R.id.good_per : break;
                case R.id.defective_per : break;
                case R.id.scan:break;
            }
            if(selectSortMethod.isSelected()){

            }
            baseActivity.updateTable(null,sortColum,"ASC");

            dismiss();
        });
        builder.setNegativeButton("キャンセル", (dialog, which) -> dismiss());
        return builder.create();
    }
}