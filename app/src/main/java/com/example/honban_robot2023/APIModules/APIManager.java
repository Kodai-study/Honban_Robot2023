package com.example.honban_robot2023.APIModules;

import com.example.honban_robot2023.APIModules.ResultAPI.ResultsDataModel;
import com.example.honban_robot2023.Test.TestFetchAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIManager {

    @GET(value = "posts")
    Call<List<SampleAPIModel>> getModels();

    @GET(value = "posts")
    Call<List<SampleAPIModel>> getModelsWithUserId(@Query("userId") int userId);

    @GET(value = "sample1")
    Call<TestFetchAPI.Sample_OneParameterModel> getSampleParam();

    @GET(value = "result")
    Call<List<ResultsDataModel>> getResults();

    @GET(value = "stationUtilization")
    Call<List<UtilizationModel>> getUtilizationData();
}