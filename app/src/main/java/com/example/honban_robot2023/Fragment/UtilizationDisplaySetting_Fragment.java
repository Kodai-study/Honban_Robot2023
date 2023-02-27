package com.example.honban_robot2023.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.ToggleButton;

import com.example.honban_robot2023.R;
import com.example.honban_robot2023.UtilizationTable_Activity;


public class UtilizationDisplaySetting_Fragment extends DialogFragment {

    private UtilizationTable_Activity base_Activity;

    RadioButton orderByOperatingTime;
    RadioButton orderByDate;

    ToggleButton selectSortMethod;

    public UtilizationDisplaySetting_Fragment(UtilizationTable_Activity base_Activity) {
        this.base_Activity = base_Activity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final Activity activity = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final View dialogView = LayoutInflater.from(activity).inflate(R.layout.fragment_utilization, null);
        builder.setView(dialogView);
        orderByOperatingTime = dialogView.findViewById(R.id.continuity);
        orderByDate = dialogView.findViewById(R.id.date_uti);
        selectSortMethod = dialogView.findViewById(R.id.ASC_uti);
        builder.setPositiveButton("適用", (dialog, which) -> {
            String sortColum = "DATE";
            if (orderByOperatingTime.isChecked())
                sortColum = "operationTime";

            if (selectSortMethod.isChecked())
                base_Activity.updateTable(sortColum, "ASC");
            else
                base_Activity.updateTable(sortColum, "DESC");
            dismiss();
        });
        builder.setNegativeButton("キャンセル", (dialog, which) -> dismiss());
        return builder.create();
    }
}