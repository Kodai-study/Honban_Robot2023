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

import com.example.honban_robot2023.R;

public class PieGraph_Fragment extends DialogFragment {

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
        builder.setPositiveButton("適用", (dialog, which) -> dismiss());
        builder.setNegativeButton("キャンセル", (dialog, which) -> dismiss());
        return builder.create();
    }
}