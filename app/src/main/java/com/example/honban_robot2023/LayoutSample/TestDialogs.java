package com.example.honban_robot2023.LayoutSample;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.honban_robot2023.R;

public class TestDialogs extends DialogFragment {
    private final int[] dialogLayouts = new int[] {
            R.layout.fragment_result_table_setting_,
            R.layout.fragment_pie_graph,
            R.layout.fragment_statistics,
            R.layout.fragment_utilization
    };
    private int layoutNumber = 0;

    public TestDialogs(int layoutNumber){
        this.layoutNumber = layoutNumber;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Activity activity = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final View dialogView = LayoutInflater.from(activity).inflate(dialogLayouts[layoutNumber], null);
        builder.setView(dialogView);
        builder.setNegativeButton("キャンセル", (dialog, which) -> dismiss());
        return builder.create();
    }
}
