package com.example.honban_robot2023.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;

import com.example.honban_robot2023.R;

public class ResultTableSetting_Fragment extends DialogFragment {


    public ResultTableSetting_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_result_table_setting_, container, false);
        return fragmentView;
    }
}