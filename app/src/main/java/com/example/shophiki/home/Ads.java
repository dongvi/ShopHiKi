package com.example.shophiki.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ads {
    @SerializedName("pic")
    @Expose
    private String url;

    public Ads(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
