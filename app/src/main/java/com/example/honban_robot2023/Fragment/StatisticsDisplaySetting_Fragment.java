package com.example.honban_robot2023.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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

    public StatisticsDisplaySetting_Fragment(StatisticsTable_Activity baseActivity) {
        this.baseActivity = baseActivity;
    }

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

            String sortColum;
            switch (sortColumSelect.getCheckedRadioButtonId()) {
                case R.id.orderBy_date:
                    sortColum = "DATE";
                    break;
                case R.id.good_per:
                    sortColum = "PASSRATE";
                    break;
                case R.id.defective_per:
                    sortColum = "DEFECTRATE";
                    break;
                case R.id.scan:
                    sortColum = "Scan";
                    break;
                default:
                    sortColum = null;
                    break;
            }
            if (selectSortMethod.isChecked())
                baseActivity.updateTable(null, sortColum, "ASC");
            else
                baseActivity.updateTable(null, sortColum, "DESC");

            dismiss();
        });
        builder.setNegativeButton("キャンセル", (dialog, which) -> dismiss());
        return builder.create();
    }
}