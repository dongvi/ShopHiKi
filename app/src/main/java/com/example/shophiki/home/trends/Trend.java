package com.example.shophiki.home.trends;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trend {
    @SerializedName("Name")
    @Expose
        private String trendName;

    @SerializedName("imgTrend")
    @Expose
        private String imgTrend;

    public Trend(String trendName, String imgTrend) {
        this.trendName = trendName;
        this.imgTrend = imgTrend;
    }

    public String getImgTrend() {
        return imgTrend;
    }

    public void setImgTrend(String imgTrend) {
        this.imgTrend = imgTrend;
    }

    public String getTrendName() {
        return trendName;
    }

    public void setTrendName(String trendName) {
        this.trendName = trendName;
    }
}
