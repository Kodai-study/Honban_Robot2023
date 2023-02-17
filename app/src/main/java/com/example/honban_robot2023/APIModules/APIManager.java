package com.example.honban_robot2023.APIModules;

import com.example.honban_robot2023.APIModules.ResultAPI.ResultsDataModel;
import com.example.honban_robot2023.Test.TestFetchAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIManager {

    /**
     * Webサイトから取ってきたサンプルのAPIのデータリストを返す
     */
    @GET(value = "posts")
    Call<List<SampleAPIModel>> getModels();

    /**
     * クエリを受取り、ユーザIDを指定したデータを取得する
     * @param userId データの取得を指定するユーザIDの値。int型。
     */
    @GET(value = "posts")
    Call<List<SampleAPIModel>> getModelsWithUserId(@Query("userId") int userId);

    /**
     * 何か試したいときに使うAPI
     */
    @GET(value = "sample1")
    Call<TestFetchAPI.Sample_OneParameterModel> getSampleParam();

    /**
     * 検査結果の一覧を取得する
     * @return 検査結果一覧 {@link com.example.honban_robot2023.APIModules.ResultAPI.ResultsDataModel}
     */
    @GET(value = "result")
    Call<List<ResultsDataModel>> getResults();

    /**
     * 1日ごとのロボットの稼働率一覧を取得する
     * @return 検査結果一覧 {@link com.example.honban_robot2023.APIModules.UtilizationModel}
     */
    @GET(value = "stationUtilization")
    Call<List<UtilizationModel>> getUtilizationData();

    /**
     * ワークごとの、検査工程ごとにかかった時間の一覧を表示する
     */
    @GET(value = "times")
    Call<List<TimeIntervalAPIModel>> getTimeIntervalData();

    /**
     * ワークごとの、検査工程ごとに、工程が始まった時刻の一覧を取得する
     */
    @GET(value = "times/timestump")
    Call<List<TimeStampModel>> getTimeStumpData();

    /**
     * 単位時間(1日、1週間、1か月)ごとで、検査数などの統計データを表示する
     */
    @GET(value = "statistics")
    Call<List<StatisticsAPIModel>> getStatisticsData();
}