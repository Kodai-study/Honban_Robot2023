package com.example.honban_robot2023.Test;

import com.example.honban_robot2023.APIModules.ResultAPI.ResultsDataModel;
import com.example.honban_robot2023.APIModules.StatisticsAPIModel;
import com.example.honban_robot2023.APIModules.TimeIntervalAPIModel;
import com.example.honban_robot2023.APIModules.UtilizationModel;

import java.util.ArrayList;
import java.util.List;

public class Test_dummyAPIData {
    static public List<StatisticsAPIModel> getResultDummy() {
        ArrayList<StatisticsAPIModel> list = new ArrayList<StatisticsAPIModel>();
        list.add(new StatisticsAPIModel("2022-02-02T00:00:00", "2022-02-03T00:00:00", 9, 8, 7, 6, 5, 4, 3, 3, 2, 2, 1, 2, 3));
        list.add(new StatisticsAPIModel("2022-02-03T00:00:00", "2022-02-03T00:00:00", 9, 8, 7, 6, 5, 4, 3, 3, 2, 2, 1, 2, 3));
        list.add(new StatisticsAPIModel("2022-02-04T00:00:00", "2022-02-03T00:00:00", 9, 8, 7, 6, 5, 4, 3, 3, 2, 2, 1, 2, 3));
        list.add(new StatisticsAPIModel("2022-02-05T00:00:00", "2022-02-03T00:00:00", 9, 8, 7, 6, 5, 4, 3, 3, 2, 2, 1, 2, 3));
        list.add(new StatisticsAPIModel("2022-02-06T00:00:00", "2022-02-03T00:00:00", 9, 8, 7, 6, 5, 4, 3, 3, 2, 2, 1, 2, 3));
        return list;
    }

    static public List<TimeIntervalAPIModel> getTimeIntervalDummy() {
        ArrayList<TimeIntervalAPIModel> list = new ArrayList<>();
        list.add(new TimeIntervalAPIModel(1, "2022-02-02T00:00:00", "00:00:00", "00:00:00", "00:00:00", "00:00:00"));
        list.add(new TimeIntervalAPIModel(1, "2022-02-02T00:00:00", "00:00:00", "00:00:00", "00:00:00", "00:00:00"));
        list.add(new TimeIntervalAPIModel(1, "2022-02-02T00:00:00", "00:00:00", "00:00:00", "00:00:00", "00:00:00"));
        list.add(new TimeIntervalAPIModel(1, "2022-02-02T00:00:00", "00:00:00", "00:00:00", "00:00:00", "00:00:00"));
        list.add(new TimeIntervalAPIModel(1, "2022-02-02T00:00:00", "00:00:00", "00:00:00", "00:00:00", "00:00:00"));
        list.add(new TimeIntervalAPIModel(1, "2022-02-02T00:00:00", "00:00:00", "00:00:00", "00:00:00", "00:00:00"));
        return list;
    }

    static public List<UtilizationModel> getUtilizationDummy() {
        ArrayList<UtilizationModel> list = new ArrayList<>();
        list.add(new UtilizationModel("2022-02-02T00:00:00","00:00:00","00:00:00","00:00:00","00:00:00"));
        list.add(new UtilizationModel("2022-02-02T00:00:00","00:00:00","00:00:00","00:00:00","00:00:00"));
        list.add(new UtilizationModel("2022-02-02T00:00:00","00:00:00","00:00:00","00:00:00","00:00:00"));
        list.add(new UtilizationModel("2022-02-02T00:00:00","00:00:00","00:00:00","00:00:00","00:00:00"));
        list.add(new UtilizationModel("2022-02-02T00:00:00","00:00:00","00:00:00","00:00:00","00:00:00"));
        list.add(new UtilizationModel("2022-02-02T00:00:00","00:00:00","00:00:00","00:00:00","00:00:00"));
        return list;
    }

}
