package com.example.honban_robot2023.Test;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.honban_robot2023.APIModules.APIManager;
import com.example.honban_robot2023.APIModules.StatisticsAPIModel;
import com.example.honban_robot2023.APIModules.TimeIntervalAPIModel;
import com.example.honban_robot2023.APIModules.TimeStampModel;
import com.example.honban_robot2023.APIModules.UtilizationModel;
import com.example.honban_robot2023.Models.RetrofitFactory;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TestFetchAPI {

    private final Context activityContext;

    public TestFetchAPI(Context activityContext) {
        this.activityContext = activityContext;
    }

    public void debugFetchAPI() {
        Retrofit retrofit = RetrofitFactory.getApiClient("https://192.168.96.69:7015/api/");
        APIManager retrofitApi = retrofit.create(APIManager.class);


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Call<List<UtilizationModel>> utilizationData = retrofitApi.getUtilizationData();
        utilizationData.enqueue(new Callback<List<UtilizationModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<UtilizationModel>> call, @NonNull Response<List<UtilizationModel>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                assert response.body() != null;
                UtilizationModel one = response.body().get(0);
                Log.d("hoge",one.toString());
            }

            @Override
            public void onFailure(@NonNull Call<List<UtilizationModel>> call, @NonNull Throwable t) {
                Toast.makeText(activityContext,
                        t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Call<List<TimeStampModel>> listCall = retrofitApi.getTimeStumpData();
        listCall.enqueue(new Callback<List<TimeStampModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<TimeStampModel>> call, @NonNull Response<List<TimeStampModel>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                assert response.body() != null;
                TimeStampModel one = response.body().get(0);
                Log.d("hoge",one.toString());
            }

            @Override
            public void onFailure(@NonNull Call<List<TimeStampModel>> call, @NonNull Throwable t) {
                Toast.makeText(activityContext,
                        t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Call<List<TimeIntervalAPIModel>> call = retrofitApi.getTimeIntervalData();
        call.enqueue(new Callback<List<TimeIntervalAPIModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<TimeIntervalAPIModel>> call, @NonNull Response<List<TimeIntervalAPIModel>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                assert response.body() != null;
                TimeIntervalAPIModel one = response.body().get(0);
                Log.d("hoge",one.toString());
            }

            @Override
            public void onFailure(@NonNull Call<List<TimeIntervalAPIModel>> call, @NonNull Throwable t) {
                Toast.makeText(activityContext,
                        t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Call<List<StatisticsAPIModel>> apiTimeIntervalData = retrofitApi.getStatisticsData();
        apiTimeIntervalData.enqueue(new Callback<List<StatisticsAPIModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<StatisticsAPIModel>> call, @NonNull Response<List<StatisticsAPIModel>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                assert response.body() != null;
                StatisticsAPIModel one = response.body().get(0);
                Log.d("hoge",one.toString());
            }

            @Override
            public void onFailure(@NonNull Call<List<StatisticsAPIModel>> call, @NonNull Throwable t) {
                Toast.makeText(activityContext,
                        t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    public static class Sample_OneParameterModel {
        @SerializedName(value = "value")
        private final Character value;

        public Character getValue() {
            return value;
        }

        public Sample_OneParameterModel(Character value) {
            this.value = value;
        }
    }
}