package com.example.honban_robot2023.APIModules;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIManager {

    @GET(value = "posts")
    Call<List<SampleAPIModel>> getModels();

    @GET(value = "posts")
    Call<List<SampleAPIModel>> getModelsWithUserId(@Query("userId") int userId);
}
