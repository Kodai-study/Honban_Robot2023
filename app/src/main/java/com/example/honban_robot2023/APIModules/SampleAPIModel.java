package com.example.honban_robot2023.APIModules;

import com.google.gson.annotations.SerializedName;

public class SampleAPIModel {


    private int userId;
    private int id;
    private String title;

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

    @SerializedName(value = "body")
    private String subTitle;

}
