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
     *
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
     *
     * @return 検査結果一覧 {@link com.example.honban_robot2023.APIModules.ResultAPI.ResultsDataModel}
     */
    @GET(value = "result")
    Call<List<ResultsDataModel>> getResults();

    @GET(value = "result")
    Call<List<ResultsDataModel>> getResultsWithSearch(@Query("startTime") String startTime, @Query("endTime") String endTime);

    /**
     * 1日ごとのロボットの稼働率一覧を取得する
     *
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
    Call<List<TimeStampModel>> getTimeStampData();

    @GET(value = "times/timestump")
    Call<List<TimeStampModel>> getTimeStampDataWithSearch(@Query("startTime") String startTime, @Query("endTime") String endTime);

    /**
     * 単位時間(1日、1週間、1か月)ごとで、検査数などの統計データを表示する
     */
    @GET(value = "statistics")
    Call<List<StatisticsAPIModel>> getStatisticsData();

    @GET(value = "statistics")
    Call<List<StatisticsAPIModel>> getStatisticsDataWithSearch(@Query("startTime") String startTime, @Query("endTime") String endTime);

    @GET(value = "result")
    Call<List<ResultsDataModel>> getResultWithSearch(
            @Query("ng_colum") String ngColum, @Query("result") String result,
            @Query("startTime") String startTime, @Query("endTime") String endTime,
            @Query("sortColum") String sortColum, @Query("orderBy") String orderBy);

    @GET(value = "statistics")
    Call<List<StatisticsAPIModel>> getStatisticsWithSearch(
            @Query("dateTimeKind") String dateTimeKind,
            @Query("startTime") String startTime, @Query("endTime") String endTime,
            @Query("sortColum") String sortColum, @Query("orderBy") String orderBy);

    @GET(value = "times")
    Call<List<TimeIntervalAPIModel>> getTimeIntervalWithSearch(
            @Query("startTime") String startTime, @Query("endTime") String endTime,
            @Query("sortColum") String sortColum, @Query("orderBy") String orderBy);

    @GET(value = "times/timestump")
    Call<List<TimeStampModel>> getTimeStampWithSearch(
            @Query("startTime") String startTime, @Query("endTime") String endTime,
            @Query("sortColum") String sortColum, @Query("orderBy") String orderBy);

    @GET(value = "stationUtilization")
    Call<List<UtilizationModel>> getUtilizationDataWithSearch(
            @Query("startTime") String startTime, @Query("endTime") String endTime,
            @Query("sortColum") String sortColum, @Query("orderBy") String orderBy);

    //TODO システムの現在の状態を取ってくるAPIの実装
    @GET(value = "hoge")
    Call<StationStateAPIModel> getStationState();
}