package com.example.shophiki.api;

import com.example.shophiki.Review;
import com.example.shophiki.home.Ads;
import com.example.shophiki.home.products.Product;
import com.example.shophiki.home.genuine_product.GP;
import com.example.shophiki.home.trends.Trend;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface APIClientLPM {
    @GET("getSale")
        Call<List<Product>> onGetProductSale();

    @GET("getADS")
        Call<List<Ads>> onGetAds();

    @GET("getGP")
        Call<List<GP>> onGetGP();

    @GET("getTrend")
        Call<List<Trend>> onGetTrend();

    @GET("getAdsGP")
        Call<List<Ads>> onGetAdsGP();

    @GET("getProduct")
    Call<List<Product>> onGetProduct();

    @GET("getReview")
        Call<List<Review>> onGetReview();
}
