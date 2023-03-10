package com.example.honban_robot2023.event;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.honban_robot2023.APIModules.APIManager;
import com.example.honban_robot2023.APIModules.StationStateAPIModel;
import com.example.honban_robot2023.Models.RetrofitFactory;
import com.example.honban_robot2023.R;

import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * システムの現在の状態を表す {@link com.example.honban_robot2023.MainActivity}
 * で、システムの状態をAPIから取得して画面の更新を行う処理
 */
public class TimerTaskMonitorSystem extends TimerTask {

    /**
     * APIからデータを取得するためのクラス
     */
    APIManager apiManager;

    TextView stationState_Supply;
    TextView stationState_Visual;
    TextView stationState_Function;
    TextView stationState_Assembly;

    TextView stopCause;
    TextView numberOfStock_good;
    TextView numberOfStock_bad;
    TextView previousResult_Voltage;
    TextView previousResult_Frequency;
    TextView previousResult_VisualInspection;

    TextView systemState_anomaly;
    TextView systemState_standby;
    TextView systemState_cooperation;
    AppCompatActivity baseActivityView;

    TextView numberOfWork_VisualStation;
    TextView numberOfWork_FunctionStation;
    TextView numberOfWork_AssemblyStation;

    /**
     * 書き換えを行う様々なビューオブジェクトを取得する。
     *
     * @param baseActivityView 書き換え対象のビューがある
     *                         アクティビティ
     */
    public TimerTaskMonitorSystem(AppCompatActivity baseActivityView) {
        this.baseActivityView = baseActivityView;
        apiManager = RetrofitFactory.getApiClient("https://192.168.96.234:7015/api/").create(APIManager.class);
        stationState_Supply = baseActivityView.findViewById(R.id.Supply_con);
        stationState_Visual = baseActivityView.findViewById(R.id.Visal_con);
        stationState_Function = baseActivityView.findViewById(R.id.Func_con);
        stationState_Assembly = baseActivityView.findViewById(R.id.Assem_con);
        stopCause = baseActivityView.findViewById(R.id.cause_bad);
        numberOfStock_good = baseActivityView.findViewById(R.id.good_num);
        numberOfStock_bad = baseActivityView.findViewById(R.id.bad_num);
        previousResult_Voltage = baseActivityView.findViewById(R.id.Volt_ST);
        previousResult_Frequency = baseActivityView.findViewById(R.id.Freq_ST);
        previousResult_VisualInspection = baseActivityView.findViewById(R.id.Visal_Test);
        systemState_anomaly = baseActivityView.findViewById(R.id.anomaly_state);
        systemState_standby = baseActivityView.findViewById(R.id.standby_state);
        systemState_cooperation = baseActivityView.findViewById(R.id.alig_state);
        numberOfWork_VisualStation = baseActivityView.findViewById(R.id.numberOfWork_visualStation);
        numberOfWork_FunctionStation = baseActivityView.findViewById(R.id.numberOfWork_functionStation);
        numberOfWork_AssemblyStation = baseActivityView.findViewById(R.id.numberOfWork_assemblyStation);
    }

    @Override
    public void run() {
        Call<StationStateAPIModel> stationStateData = apiManager.getStationState();
        stationStateData.enqueue(new Callback<StationStateAPIModel>() {
            @Override
            public void onResponse(Call<StationStateAPIModel> call, Response<StationStateAPIModel> response) {
                StationStateAPIModel s = response.body();

                if (!s.isSuccessConnect()) {
                    Toast.makeText(baseActivityView, "システム状態の取得に失敗", Toast.LENGTH_LONG).show();
                    TimerTaskMonitorSystem.super.cancel();
                    return;
                }

                numberOfStock_good.setText(String.format("良品:%d個", s.getNumberOfOKStock()));
                numberOfStock_bad.setText(String.format("不良品:%d個", s.getNumberOfNGStock()));
                stationState_Supply.setText("状態:" + s.getStationState_Supply());
                stationState_Visual.setText("状態:" + s.getStationState_Visual());
                stationState_Function.setText("状態:" + s.getStationState_Function());
                stationState_Assembly.setText("状態:" + s.getStationState_Assembly());
                numberOfWork_VisualStation.setText("ステーション内のワークの個数 : " + s.getNumberOfWork_VisualStation());
                numberOfWork_FunctionStation.setText("ステーション内のワークの個数 : " + s.getNumberOfWork_FunctionalStation());
                numberOfWork_AssemblyStation.setText("ステーション内のワークの個数 : " + s.getNumberOfWork_AssemblyStation());
                String systemState = s.getSystemState();

                if (systemState == null || systemState.equals("")) {
                    systemState_anomaly.setText("");
                    systemState_standby.setVisibility(View.GONE);
                    systemState_cooperation.setVisibility(View.GONE);
                } else if (systemState.contains("異常")) {
                    systemState_anomaly.setText(systemState);
                    systemState_standby.setVisibility(View.GONE);
                    systemState_cooperation.setVisibility(View.GONE);
                } else if (systemState.contains("連携動作中")) {
                    systemState_cooperation.setText(systemState);
                    systemState_standby.setVisibility(View.GONE);
                    systemState_anomaly.setVisibility(View.GONE);
                } else {
                    systemState_standby.setText(systemState);
                    systemState_cooperation.setVisibility(View.GONE);
                    systemState_anomaly.setVisibility(View.GONE);
                }

                if (s.isSystemPause()) {
                    stopCause.setVisibility(View.VISIBLE);
                    stopCause.setText(s.getSystemPauseCause());
                } else
                    stopCause.setVisibility(View.GONE);

                if (s.isFunctionInspectedJustBefore()) {
                    previousResult_Voltage.setText("電圧 : " + s.getResultVoltage());
                    previousResult_Frequency.setText("周波数" + s.getResultFrequency());
                } else {
                    previousResult_Voltage.setVisibility(View.GONE);
                    previousResult_Frequency.setVisibility(View.GONE);
                }

                String visualInspectionData = s.getVisualInspectionData();

                if (s.isVisualInspectedJustBefore())
                    previousResult_VisualInspection.setVisibility(View.GONE);
                else {
                    previousResult_VisualInspection.setText(visualInspectionData);
                    previousResult_VisualInspection.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<StationStateAPIModel> call, Throwable t) {
                Toast.makeText(baseActivityView, "システム状態の取得に失敗", Toast.LENGTH_LONG).show();
                TimerTaskMonitorSystem.this.cancel();
                return;
            }
        });
    }

}