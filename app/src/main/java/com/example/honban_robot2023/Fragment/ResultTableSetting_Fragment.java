package com.example.honban_robot2023.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.honban_robot2023.R;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class ResultTableSetting_Fragment extends DialogFragment {

    RadioButton[] radioButtons = new RadioButton[8];
    Button settingResetButton;

    Dictionary<Integer, String> itemAndColumDictionary;

    List<String> checkedColumName = new ArrayList<>();

    String searchColumNames[] = new String[]{"DS", "R", "IC2", "IC1", "Volt", "Freq", "OK", "NG"};
    int checkedButtonIndex = -1;

    public ResultTableSetting_Fragment() {
        checkedColumName.add("DS");
        checkedColumName.add("R");
        checkedColumName.add("IC2");
        checkedColumName.add("IC1");
        checkedColumName.add("Volt");
        checkedColumName.add("Freq");
        checkedColumName.add("OK");
        checkedColumName.add("NG");
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final Activity activity = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final View dialogView = LayoutInflater.from(activity).inflate(R.layout.fragment_result_table_setting_, null);
        builder.setView(dialogView);
        builder.setPositiveButton("適用", (dialog, which) -> dismiss());
        builder.setNegativeButton("キャンセル", (dialog, which) -> dismiss());

        radioButtons[0] = dialogView.findViewById(R.id.DIPSW);
        radioButtons[1] = dialogView.findViewById(R.id.R);
        radioButtons[2] = dialogView.findViewById(R.id.IC2);
        radioButtons[3] = dialogView.findViewById(R.id.IC1);
        radioButtons[4] = dialogView.findViewById(R.id.Volt);
        radioButtons[5] = dialogView.findViewById(R.id.Freq);
        radioButtons[6] = dialogView.findViewById(R.id.OK);
        radioButtons[7] = dialogView.findViewById(R.id.NG);
        settingResetButton = dialogView.findViewById(R.id.reset_sea);

        for (int i = 0; i < radioButtons.length; i++) {
            int finalI = i;
            radioButtons[finalI].setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    if (checkedButtonIndex >= 0 && checkedButtonIndex != finalI)
                        radioButtons[checkedButtonIndex].setChecked(false);
                    checkedButtonIndex = finalI;
                }
            });
        }

        settingResetButton.setOnClickListener(view -> {
            for (RadioButton button : radioButtons)
                button.setChecked(false);
            checkedButtonIndex = -1;
        });

        if (checkedButtonIndex >= 0) {
            radioButtons[checkedButtonIndex].setChecked(true);
        }

        return builder.create();
    }



}