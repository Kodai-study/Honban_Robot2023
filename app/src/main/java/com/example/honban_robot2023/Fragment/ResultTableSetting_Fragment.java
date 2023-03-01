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
import com.example.honban_robot2023.ResultTable_Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * ワークごとの検査結果一覧を表示させる {@link com.example.honban_robot2023.ResultTable_Activity}
 * のテーブルの表示項目の絞り込み、並び替えを行う設定ダイアログ
 */
public class ResultTableSetting_Fragment extends DialogFragment {

    /**
     * 不合格項目による絞り込みを行うときの、検査項目を選択するラジオボタン群
     */
    RadioButton[] radioButtons = new RadioButton[8];

    /**
     * 絞り込みの時の検査項目の選択をリセットし、ラジオボタンの選択を
     * 全て取り消すリセットボタン
     */
    Button settingResetButton;

    /**
     * 不合格項目による絞り込みを行うときに、クエリでAPIに渡す文字列。
     * {@link #radioButtons} の添え字と一致させる。
     */
    List<String> checkedColumName = new ArrayList<>();

    /**
     * 選択されているラジオボタンの番号。
     * リセットされ、選択されていない時は-1
     */
    int checkedButtonIndex = -1;

    /**
     * 絞り込みを行う対象となるテーブルが存在しているアクティビティ
     */
    ResultTable_Activity baseActivity;

    public ResultTableSetting_Fragment(ResultTable_Activity baseActivity) {
        checkedColumName.add("DS");
        checkedColumName.add("R");
        checkedColumName.add("IC2");
        checkedColumName.add("IC1");
        checkedColumName.add("Volt");
        checkedColumName.add("Freq");
        checkedColumName.add("OK");
        checkedColumName.add("NG");
        this.baseActivity = baseActivity;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final Activity activity = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final View dialogView = LayoutInflater.from(activity).inflate(R.layout.fragment_result_table_setting_, null);
        builder.setView(dialogView);
        builder.setPositiveButton("適用", (dialog, which) -> {
            boolean isAnyRadioChecked = false;
            for (int i = 0; i < radioButtons.length; i++) {
                if (!radioButtons[i].isChecked())
                    continue;

                isAnyRadioChecked = true;
                if (radioButtons[i].getId() == R.id.OK || radioButtons[i].getId() == R.id.NG) {
                    baseActivity.updateTable(null, checkedColumName.get(i));
                } else {
                    baseActivity.updateTable(checkedColumName.get(i), null);
                }
            }
            if(!isAnyRadioChecked)
                baseActivity.updateTable(null,null);
            dismiss();
        });
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