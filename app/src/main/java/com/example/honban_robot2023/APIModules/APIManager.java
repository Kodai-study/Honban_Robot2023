package com.example.honban_robot2023.APIModules;

import com.example.honban_robot2023.APIModules.ResultAPI.ResultsDataModel;
import com.example.honban_robot2023.Test.TestFetchAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *  @see <a href="https://192.168.96.69:7015/api/">自作API</a> から
 *  情報を取得する処理をまとめたインタフェース
 */
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
     * サンプルのAPIで情報を取得する
     */
    @GET(value = "sample1")
    Call<TestFetchAPI.Sample_OneParameterModel> getSampleParam();

    /**
     * 検査結果の一覧を取得する
     *
     * @return 検査結果一覧 {@link com.example.honban_robot2023.APIModules.ResultAPI.ResultsDataModel} のリスト
     */
    @GET(value = "result")
    Call<List<ResultsDataModel>> getResults();

    @GET(value = "result")
    Call<List<ResultsDataModel>> getResultsWithSearch(
            @Query("startTime") String startTime, @Query("endTime") String endTime);

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

    @GET(value = "times")
    Call<List<TimeIntervalAPIModel>> getTimeIntervalDataWithSearch(
            @Query("startTime") String startTime, @Query("endTime") String endTime);


    /**
     * ワークごとの、検査工程ごとに、工程が始まった時刻の一覧を取得する
     */
    @GET(value = "times/timestump")
    Call<List<TimeStampModel>> getTimeStampData();

    @GET(value = "times/timestump")
    Call<List<TimeStampModel>> getTimeStampDataWithSearch(
            @Query("startTime") String startTime, @Query("endTime") String endTime);

    /**
     * 単位時間(1日、1週間、1か月)ごとで、検査数などの統計データを表示する
     */
    @GET(value = "statistics")
    Call<List<StatisticsAPIModel>> getStatisticsData();


    @GET(value = "statistics")
    Call<List<StatisticsAPIModel>> getStatisticsDataWithSearch(
            @Query("startTime") String startTime, @Query("endTime") String endTime);


    /**
     * NG項目と検査時刻による絞り込み、並び替えのデータをクエリで指定して結果データを取得する。
     *
     * @param ngColum   その項目がNGになったワークだけを絞り込む、絞り込み項目
     *                  クエリ名:"ng_colum"
     * @param result    良品、不良品の項目のみを絞り込むクエリ項目
     *                  クエリ名:"result"
     * @param startTime 検査時刻の範囲指定による絞り込みで、下限値を指定するクエリ項目
     *                  クエリ名:"startTime"
     * @param endTime   検査時刻の範囲指定による絞り込みで、上限値を指定するクエリ項目
     *                  クエリ名:"endTime"
     * @param sortColum データ項目の中で、並び替えの項目を指定するクエリ項目
     *                  クエリ名:"sortColum"
     * @param orderBy   並び替え項目を指定したとき(ない時はデフォルト)に並び替えが
     *                  降順、昇順かを指定するクエリ項目   クエリ名:"orderBy"
     * @return
     */
    @GET(value = "result")
    Call<List<ResultsDataModel>> getResultWithSearch(
            @Query("ng_colum") String ngColum, @Query("result") String result,
            @Query("startTime") String startTime, @Query("endTime") String endTime,
            @Query("sortColum") String sortColum, @Query("orderBy") String orderBy);

    /**
     * 単位時間や検査時刻の範囲をクエリで指定してデータを取得する。
     *
     * @param dateTimeKind 統計データを取得する単位時間が 1日、1週間、1か月のいずれかで取得するかを指定する
     *                     クエリ項目    クエリ名:"dateTimeKind"
     * @param startTime    検査時刻の範囲指定による絞り込みで、下限値を指定するクエリ項目
     *                     クエリ名:"startTime"
     * @param endTime      検査時刻の範囲指定による絞り込みで、上限値を指定するクエリ項目
     *                     クエリ名:"endTime"
     * @param sortColum    データ項目の中で、並び替えの項目を指定するクエリ項目
     *                     クエリ名:"sortColum"
     * @param orderBy      並び替え項目を指定したとき(ない時はデフォルト)に並び替えが
     *                     降順、昇順かを指定するクエリ項目   クエリ名:"orderBy"
     * @return
     */
    @GET(value = "statistics")
    Call<List<StatisticsAPIModel>> getStatisticsWithSearch(
            @Query("dateTimeKind") String dateTimeKind,
            @Query("startTime") String startTime, @Query("endTime") String endTime,
            @Query("sortColum") String sortColum, @Query("orderBy") String orderBy);

    /**
     * 検査時刻の範囲指定と、並び替えをクエリで指定して工程にかかった時間のデータを取得する
     *
     * @param startTime 検査時刻の範囲指定による絞り込みで、下限値を指定するクエリ項目
     *                  クエリ名:"startTime"
     * @param endTime   検査時刻の範囲指定による絞り込みで、上限値を指定するクエリ項目
     *                  クエリ名:"endTime"
     * @param sortColum データ項目の中で、並び替えの項目を指定するクエリ項目
     *                  クエリ名:"sortColum"
     * @param orderBy   並び替え項目を指定したとき(ない時はデフォルト)に並び替えが
     *                  降順、昇順かを指定するクエリ項目   クエリ名:"orderBy"
     * @return
     */
    @GET(value = "times")
    Call<List<TimeIntervalAPIModel>> getTimeIntervalWithSearch(
            @Query("startTime") String startTime, @Query("endTime") String endTime,
            @Query("sortColum") String sortColum, @Query("orderBy") String orderBy);

    /**
     * 検査時刻の範囲指定と、並び替えをクエリで指定してタイムスタンプのデータを取得する
     *
     * @param startTime 検査時刻の範囲指定による絞り込みで、下限値を指定するクエリ項目
     *                  クエリ名:"startTime"
     * @param endTime   検査時刻の範囲指定による絞り込みで、上限値を指定するクエリ項目
     *                  クエリ名:"endTime"
     * @param sortColum データ項目の中で、並び替えの項目を指定するクエリ項目
     *                  クエリ名:"sortColum"
     * @param orderBy   並び替え項目を指定したとき(ない時はデフォルト)に並び替えが
     *                  降順、昇順かを指定するクエリ項目   クエリ名:"orderBy"
     * @return
     */
    @GET(value = "times/timestump")
    Call<List<TimeStampModel>> getTimeStampWithSearch(
            @Query("startTime") String startTime, @Query("endTime") String endTime,
            @Query("sortColum") String sortColum, @Query("orderBy") String orderBy);

    /**
     * 検査時刻の範囲指定と、並び替えをクエリで指定して、ロボットの稼働状況のデータを取得する
     *
     * @param startTime 検査時刻の範囲指定による絞り込みで、下限値を指定するクエリ項目
     *                  クエリ名:"startTime"
     * @param endTime   検査時刻の範囲指定による絞り込みで、上限値を指定するクエリ項目
     *                  クエリ名:"endTime"
     * @param sortColum データ項目の中で、並び替えの項目を指定するクエリ項目
     *                  クエリ名:"sortColum"
     * @param orderBy   並び替え項目を指定したとき(ない時はデフォルト)に並び替えが
     *                  降順、昇順かを指定するクエリ項目   クエリ名:"orderBy"
     * @return
     */
    @GET(value = "stationUtilization")
    Call<List<UtilizationModel>> getUtilizationDataWithSearch(
            @Query("startTime") String startTime, @Query("endTime") String endTime,
            @Query("sortColum") String sortColum, @Query("orderBy") String orderBy);

    /**
     * PLC監視プログラムから取得した、ステーションの現在の状態を取得する
     *
     * @return ステーションの現在の状態を表すデータクラス {@link StationStateAPIModel}
     */
    @GET(value = "stationStatus")
    Call<StationStateAPIModel> getStationState();

    /**
     * 現在までの検査情報の合計を取得する
     *
     * @return 検査情報の合計データを表すデータクラス {@link InspectionTotalAPIModel}
     */
    @GET(value = "totalInspectionData")
    Call<InspectionTotalAPIModel> getTotalInspectionData();

}