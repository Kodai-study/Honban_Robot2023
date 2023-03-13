package com.example.honban_robot2023.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.ToggleButton;

import com.example.honban_robot2023.R;

public class PieGraph_Fragment extends DialogFragment {

    RadioButton settingShowVisualInspectionData;
    RadioButton settingShowFunctionInspectionData;
    RadioButton settingShowDataEachStation;

    public PieGraph_Fragment() {
        // Required empty public constructor
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final Activity activity = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final View dialogView = LayoutInflater.from(activity).inflate(R.layout.fragment_pie_graph, null);
        builder.setView(dialogView);
        settingShowDataEachStation = dialogView.findViewById(R.id.radioButton_pieChartColum_Station);
        settingShowVisualInspectionData = dialogView.findViewById(R.id.radioButton_pieChartColum_VisualInspection);
        settingShowFunctionInspectionData = dialogView.findViewById(R.id.radioButton_pieChartColum_FunctionInspection);

        settingShowDataEachStation.setChecked(true);
        settingShowFunctionInspectionData.setChecked(false);
        settingShowVisualInspectionData.setChecked(false);
        builder.setPositiveButton("適用", (dialog, which) -> dismiss());
        builder.setNegativeButton("キャンセル", (dialog, which) -> dismiss());
        return builder.create();
    }
}