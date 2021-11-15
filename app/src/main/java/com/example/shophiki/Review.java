package com.example.shophiki;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Review {
    @SerializedName("id")
    @Expose
    private String id;


    @SerializedName("user")
    @Expose
    private String user;

    @SerializedName("imgUser")
    @Expose
    private String imgUser;

    @SerializedName("vote")
    @Expose
    private double vote;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("msg")
    @Expose
    private String msg;

    @SerializedName("emoji")
    @Expose
    private Emoji[] emojis;

    public Review(String id, String user, String imgUser, double vote, String date, String msg, Emoji[] emojis) {
        this.id = id;
        this.user = user;
        this.imgUser = imgUser;
        this.vote = vote;
        this.date = date;
        this.msg = msg;
        this.emojis = emojis;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getImgUser() {
        return imgUser;
    }

    public void setImgUser(String imgUser) {
        this.imgUser = imgUser;
    }

    public double getVote() {
        return vote;
    }

    public void setVote(double vote) {
        this.vote = vote;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Emoji[] getEmojis() {
        return emojis;
    }

    public void setEmojis(Emoji[] emojis) {
        this.emojis = emojis;
    }
}
