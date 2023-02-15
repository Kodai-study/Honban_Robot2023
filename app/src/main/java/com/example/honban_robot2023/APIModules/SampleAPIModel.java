package com.example.honban_robot2023.APIModules;

import com.google.gson.annotations.SerializedName;

public class SampleAPIModel {


    private final int userId;
    private final int id;
    private final String title;
    @SerializedName(value = "body")
    private final String subTitle;

    public SampleAPIModel(int userId, int id, String title, String subTitle) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }


}
