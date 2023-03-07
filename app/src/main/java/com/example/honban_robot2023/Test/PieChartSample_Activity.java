package com.example.honban_robot2023.Test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.honban_robot2023.APIModules.APIManager;
import com.example.honban_robot2023.APIModules.InspectionTotalAPIModel;
import com.example.honban_robot2023.APIModules.StatisticsAPIModel;
import com.example.honban_robot2023.Fragment.PieGraph_Fragment;
import com.example.honban_robot2023.Models.RetrofitFactory;
import com.example.honban_robot2023.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 検査結果の、不合格要因に関する円グラフのデータを表示するアクティビティ。
 * 検査ステーション(外観検査、機能検査)毎の不合格品を出した割合と、
 * ステーションの検査で、検査項目ごとに不合格を出した割合を表示する。
 */
public class PieChartSample_Activity extends AppCompatActivity {

    /**
     * 円グラフを表示するオブジェクト
     */
    PieChart pieChart;

    APIManager retrofitApi;

    private DialogFragment settingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart_sample);
        setTestChartData();
        retrofitApi = RetrofitFactory.getApiClient("https://192.168.96.69:7015/api/").create(APIManager.class);

        setInspection();
        settingDialog = new PieGraph_Fragment();
        //setNgCourse();
    }

    /**
     * 円グラフに、デフォルトのデータを表示する。
     */
    private void setTestChartData() {
        String[] dimensions = new String[]{"A", "B", "C", "D"};
        float[] values = new float[]{1f, 2f, 3f, 4f};


        setPieChart(dimensions, values);

        pieChart.setEntryLabelTextSize(30f);  //ラベルテキストの変更
        pieChart.setCenterText("全体の不良品率"); //中央にテキストを表示する
        pieChart.setCenterTextSize(35f);  //centertextの文字の大きさ

        //pieChart.getDescription().setTextSize(26f);
        //pieChart.getDescription().setText("円グラフ");
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setTextSize(26f);
        pieChart.setTouchEnabled(false);//回転を止める、タップ無効


    }

    /**
     * データを渡し、円グラフを表示する。
     *
     * @param columNames データの項目名。第2引数の配列と対応する
     * @param values     項目の値。この値で円グラフの表示の割合が取られる
     */
    private void setPieChart(String[] columNames, float[] values) {
        List<PieEntry> entryList = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            entryList.add(
                    new PieEntry(values[i], columNames[i])
            );
        }

        PieDataSet pieDataSet = new PieDataSet(entryList, "candle");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(30f);
        pieChart = findViewById(R.id.pieChartExample);
        pieChart.setData(pieData);
        pieChart.setEnabled(true);
        pieChart.invalidate();
        /**https://qiita.com/c60evaporator/items/14e63d22d860b73e6f22
         * MPAndroidchartまとめのURL*/
    }


    /**
     * 検査ステーション毎の、不合格品を出した割合を表示する。
     * 100%を ・外観検査ステーション ・機能検査ステーションで分ける
     */
    private void setInspection() {

        Call<InspectionTotalAPIModel> inspectionData = retrofitApi.getTotalInspectionData();
        inspectionData.enqueue(new Callback<InspectionTotalAPIModel>() {
            @Override
            public void onResponse(Call<InspectionTotalAPIModel> call, Response<InspectionTotalAPIModel> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                InspectionTotalAPIModel totalData = response.body();

                setPieChart(new String[]{"外観検査不良品", "機能検査不良品"},
                        new float[]{totalData.getCount_VisualInspectionNG(), totalData.getCount_FunctionalInspectionNG()});
            }

            @Override
            public void onFailure(Call<InspectionTotalAPIModel> call, Throwable t) {
                Toast.makeText(PieChartSample_Activity.this,
                        t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    /**
     * 外観検査ステーションで、検査項目ごとに不合格品を出した割合を
     * 表示する。
     */
    private void showNGCourse_Visual() {

        Call<List<StatisticsAPIModel>> statisticsData = retrofitApi.getStatisticsData();
        statisticsData.enqueue(new Callback<List<StatisticsAPIModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<StatisticsAPIModel>> call, @NonNull Response<List<StatisticsAPIModel>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                float ngCounts[] = new float[8];
                for (StatisticsAPIModel e : response.body()) {
                    ngCounts[0] += e.getNgCount_IC1();
                    ngCounts[1] += e.getNgCount_IC2();
                    ngCounts[2] += e.getNgCount_R5();
                    ngCounts[3] += e.getNgCount_R10();
                    ngCounts[4] += e.getNgCount_R11();
                    ngCounts[5] += e.getNgCount_R12();
                    ngCounts[6] += e.getNgCount_R18();
                    ngCounts[7] += e.getNgCount_DIPSW();
                }
                setPieChart(new String[]{"IC1", "IC2", "R5", "R10", "R11", "R12", "R18", "DIPSW"}, ngCounts);
            }

            @Override
            public void onFailure(@NonNull Call<List<StatisticsAPIModel>> call, @NonNull Throwable t) {
                Toast.makeText(PieChartSample_Activity.this,
                        t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_table_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* メニューバーのアイコンを押すことで設定ダイアログの表示 */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        settingDialog.show(getSupportFragmentManager(), "dialog");
        return super.onOptionsItemSelected(item);
    }
}