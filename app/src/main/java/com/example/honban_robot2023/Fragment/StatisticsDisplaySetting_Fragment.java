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

import java.util.ArrayList;
import java.util.List;

/**
 * 単位時間ごとの検査数などの統計データを表示させる {@link com.example.honban_robot2023.StatisticsTable_Activity}
 * のテーブルの表示項目の絞り込み、並び替えを行う設定ダイアログ
 */
public class StatisticsDisplaySetting_Fragment extends DialogFragment {

    /**
     * 並び替え項目を選択するラジオボタンの親オブジェクト
     */
    RadioGroup sortColumSelect;

    /**
     * 並び替え、絞り込みを行うテーブルがあるアクティビティ
     */
    StatisticsTable_Activity baseActivity;

    /**
     * 並び替えが true:昇順 false:降順 のどちらで
     * 行うかを設定するトグルスイッチ
     */
    ToggleButton selectSortMethod;

    /**
     * 設定項目を選択するラジオボタン {@link #sortColumSelect}
     * のIDの値のリスト
     */
    List<Integer> radioButtonIdList = new ArrayList<>();

    /**
     * 並び替え項目のラジオボタン {@link #sortColumSelect}
     * で何が選択されているかを保持しておく変数
     */
    private int sortColumIndex = 0;

    /**
     * 並び替えの降順、昇順を選択する{@link #selectSortMethod}
     * の値を保持しておく変数
     */
    private boolean isSortMethodSelect = false;

    public StatisticsDisplaySetting_Fragment(StatisticsTable_Activity baseActivity) {
        this.baseActivity = baseActivity;

        radioButtonIdList.add(R.id.orderBy_date);
        radioButtonIdList.add(R.id.good_per);
        radioButtonIdList.add(R.id.defective_per);
        radioButtonIdList.add(R.id.scan);
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

        ((RadioButton) sortColumSelect.getChildAt(sortColumIndex)).setChecked(true);
        selectSortMethod.setChecked(isSortMethodSelect);

        selectSortMethod.setOnCheckedChangeListener((buttonView, isChecked) -> {
            this.isSortMethodSelect = isChecked;
        });

        sortColumSelect.setOnCheckedChangeListener((group, checkedId) -> {
            this.sortColumIndex = radioButtonIdList.indexOf(checkedId);
        });

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