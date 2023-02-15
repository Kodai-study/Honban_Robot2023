package com.example.honban_robot2023.APIModules;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIController {

    public List<SampleAPIModel> main() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com").
                addConverterFactory(GsonConverterFactory.create())
                .build();

        APIManager retrofitApi = retrofit.create(APIManager.class);

        Call<List<SampleAPIModel>> models = retrofitApi.getModelsWithUserId(2);
        List<SampleAPIModel> datas = null;
        models.enqueue(new Callback<List<SampleAPIModel>>() {
            @Override
            public void onResponse(Call<List<SampleAPIModel>> call, Response<List<SampleAPIModel>> response) {
                if (!response.isSuccessful()) {

                    return;
                }

                //datas = response.body();
            }

            @Override
            public void onFailure(Call<List<SampleAPIModel>> call, Throwable t) {

            }

        });
        return null;
    }

}