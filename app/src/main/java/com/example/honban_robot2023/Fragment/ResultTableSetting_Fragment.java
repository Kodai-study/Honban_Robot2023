package com.example.honban_robot2023.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.honban_robot2023.R;
import com.example.honban_robot2023.ResultTableSettingDialog;


public class ResultTableSetting_Fragment extends Fragment {

    private RadioGroup radioGroup;

    public ResultTableSetting_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_result_table_setting_, container, false);
        fragmentView.findViewById(R.id.radioGroup);

        return fragmentView;
    }
}