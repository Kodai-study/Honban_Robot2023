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

/**
 * 日ごとのロボットの稼働状況を表示させる {@link com.example.honban_robot2023.UtilizationTable_Activity}
 * の表示項目の並び替え、絞り込みを行うダイアログ
 */
public class UtilizationDisplaySetting_Fragment extends DialogFragment {

    /**
     * 並び替え項目に何が選択されているかを表す値。
     * 0:日付 1:稼働時間の長さ
     */
    private int sortColumIndex = 0;

    /**
     * 並び替えの降順、昇順を選択する{@link #selectSortMethod}
     * の値を保持しておく変数
     */
    private boolean isSortMethodSelect = false;

    /**
     * 並び替え、絞り込みを行うテーブルがあるアクティビティ
     */
    private UtilizationTable_Activity base_Activity;

    /**
     * 稼働時間の長さで並び替えを行うときに選択される
     * ラジオボタン
     */
    RadioButton orderByOperatingTime;

    /**
     * 日付で並び替えを行うときに選択される
     * ラジオボタン
     */
    RadioButton orderByDate;

    /**
     * 並び替えが true:昇順 false:降順 のどちらで
     * 行うかを設定するトグルスイッチ
     */
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

        orderByDate.setOnCheckedChangeListener((id, checked) -> {
            if (checked) this.sortColumIndex = 0;
        });
        orderByOperatingTime.setOnCheckedChangeListener((id, checked) -> {
            if (checked) this.sortColumIndex = 1;
        });

        selectSortMethod.setOnCheckedChangeListener((buttonView, isChecked) -> {
            this.isSortMethodSelect = isChecked;
        });

        selectSortMethod.setChecked(isSortMethodSelect);

        if (sortColumIndex == 0)
            orderByDate.setChecked(true);
        else
            orderByOperatingTime.setChecked(true);

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