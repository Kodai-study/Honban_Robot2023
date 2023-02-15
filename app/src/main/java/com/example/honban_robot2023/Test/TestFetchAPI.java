package com.example.honban_robot2023.Test;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.honban_robot2023.APIModules.APIManager;
import com.example.honban_robot2023.APIModules.ResultsDataModel;
import com.example.honban_robot2023.APIModules.UtilizationModel;
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

    private final Context activityContext;

    public TestFetchAPI(Context activityContext) {
        this.activityContext = activityContext;
    }

    public void fetchSampleAPI() {
        Retrofit retrofit = RetrofitFactory.getApiClient("https://192.168.96.69:7015/api/");
        APIManager apiManager = retrofit.create(APIManager.class);
        Call<Sample_OneParameterModel> e = apiManager.getSampleParam();
        e.enqueue(new Callback<Sample_OneParameterModel>() {

            @Override
            public void onResponse(@NonNull Call<Sample_OneParameterModel> call, @NonNull Response<Sample_OneParameterModel> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                Toast.makeText(activityContext, "" + Objects.requireNonNull(response.body()).getValue(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<Sample_OneParameterModel> call, @NonNull Throwable t) {
                Toast.makeText(activityContext, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        Call<List<ResultsDataModel>> results = apiManager.getResults();
        results.enqueue(new Callback<List<ResultsDataModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<ResultsDataModel>> call, @NonNull Response<List<ResultsDataModel>> response) {
                try {
                    assert response.body() != null;
                    Toast.makeText(activityContext, "" + response.body().get(0).getCycleTime(), Toast.LENGTH_SHORT).show();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }  catch (NullPointerException nullPointerException){
                    Log.e("API","結果の一覧取得APIでヌルポエラーの発生");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ResultsDataModel>> call, @NonNull Throwable t) {
                Toast.makeText(activityContext, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Call<List<UtilizationModel>> model =  apiManager.getUtilizationData();
        model.enqueue(new Callback<List<UtilizationModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<UtilizationModel>> call, @NonNull Response<List<UtilizationModel>> response) {
                Toast.makeText(activityContext, "" + response.body(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<List<UtilizationModel>> call, @NonNull Throwable t) {
                Toast.makeText(activityContext, t.getMessage(), Toast.LENGTH_SHORT).show();
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