package com.example.shophiki.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    public static final String BASE_URL = "https://gokisoft.com/api/fake/821/";
    public static Retrofit retrofit = null;

    public static APIClientLPM create(){
        return getClient(BASE_URL).create(APIClientLPM.class);
    }

    public static Retrofit getClient(String url){
        if(retrofit == null)
            retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }
}
