package com.example.honban_robot2023.Test;

import android.content.Context;
import android.widget.Toast;

import com.example.honban_robot2023.APIModules.APIManager;
import com.example.honban_robot2023.APIModules.ResultsDataModel;
import com.example.honban_robot2023.Models.RetrofitFactory;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TestFetchAPI {

    private Context activityContext;

    public TestFetchAPI(Context activityContext) {
        this.activityContext = activityContext;
    }

    public void fetchSampleAPI() {
        Retrofit retrofit = RetrofitFactory.getApiClient("https://192.168.96.69:7015/api/");
        APIManager apiManager = retrofit.create(APIManager.class);
        Call<Sample_OneParameterModel> e = apiManager.getSampleParam();
        e.enqueue(new Callback<Sample_OneParameterModel>() {

            @Override
            public void onResponse(Call<Sample_OneParameterModel> call, Response<Sample_OneParameterModel> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                Toast.makeText(activityContext, "" + Objects.requireNonNull(response.body()).getValue(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Sample_OneParameterModel> call, Throwable t) {
                Toast.makeText(activityContext, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        Call<List<ResultsDataModel>> results = apiManager.getResults();
        results.enqueue(new Callback<List<ResultsDataModel>>() {
            @Override
            public void onResponse(Call<List<ResultsDataModel>> call, Response<List<ResultsDataModel>> response) {
                try {
                    Toast.makeText(activityContext, "" + response.body().get(0).getCycleTime(), Toast.LENGTH_SHORT).show();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void onFailure(Call<List<ResultsDataModel>> call, Throwable t) {
                Toast.makeText(activityContext, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class Sample_OneParameterModel {
        @SerializedName(value = "value")
        private Character value;

        public Character getValue() {
            return value;
        }

        public Sample_OneParameterModel(Character value) {
            this.value = value;
        }
    }
}