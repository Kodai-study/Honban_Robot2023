package com.example.honban_robot2023.event;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.honban_robot2023.APIModules.APIManager;
import com.example.honban_robot2023.Models.RetrofitFactory;
import com.example.honban_robot2023.R;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskMonitorSystem extends TimerTask {

    APIManager apiManager;
    TextView updateStateTime_Supply;
    TextView updateStateTime_Visual;
    TextView updateStateTime_Functional;
    TextView updateStateTime_Assembly;

    TextView stopCause;
    TextView numberOfStock_good;
    TextView numberOfStock_bad;
    TextView previousResult_Voltage;
    TextView previousResult_Frequency;
    TextView previousResult_VisualInspection;

    TextView systemState_anomaly;
    TextView systemState_standby;
    TextView systemState_cooperation;
    public TimerTaskMonitorSystem(AppCompatActivity baseActivityView) {
        this.apiManager = RetrofitFactory.getApiClient("https://192.168.96.69:7015/api/").create(APIManager.class);
        updateStateTime_Supply = baseActivityView.findViewById(R.id.Supply_con);
        updateStateTime_Visual = baseActivityView.findViewById(R.id.Visal_con);
        updateStateTime_Functional = baseActivityView.findViewById(R.id.Func_con);
        updateStateTime_Assembly = baseActivityView.findViewById(R.id.Assem_con);
        stopCause = baseActivityView.findViewById(R.id.cause_bad);
        numberOfStock_good = baseActivityView.findViewById(R.id.good_num);
        numberOfStock_bad = baseActivityView.findViewById(R.id.bad_num);
        previousResult_Voltage = baseActivityView.findViewById(R.id.Volt_ST);
        previousResult_Frequency = baseActivityView.findViewById(R.id.Freq_ST);
        previousResult_VisualInspection = baseActivityView.findViewById(R.id.Visal_ST);
        systemState_anomaly = baseActivityView.findViewById(R.id.anomaly_state);
        systemState_standby = baseActivityView.findViewById(R.id.standby_state);
        systemState_cooperation = baseActivityView.findViewById(R.id.alig_state);
    }

    @Override
    public void run() {

    }
}