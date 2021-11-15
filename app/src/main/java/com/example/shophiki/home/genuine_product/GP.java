package com.example.shophiki.home.genuine_product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GP {
    @SerializedName("imgFP")
    @Expose
        private String imgFeaturedProducts;
    @SerializedName("imgB")
    @Expose
        private String imgBrand;
    @SerializedName("endow")
    @Expose
        private String endow;

    public GP(String imgFeaturedProducts, String imgBrand, String endow) {
        this.imgFeaturedProducts = imgFeaturedProducts;
        this.imgBrand = imgBrand;
        this.endow = endow;
    }

    public String getImgFeaturedProducts() {
        return imgFeaturedProducts;
    }

    public void setImgFeaturedProducts(String imgFeaturedProducts) {
        this.imgFeaturedProducts = imgFeaturedProducts;
    }

    public String getImgBrand() {
        return imgBrand;
    }

    public void setImgBrand(String imgBrand) {
        this.imgBrand = imgBrand;
    }

    public String getEndow() {
        return endow;
    }

    public void setEndow(String endow) {
        this.endow = endow;
    }
}
