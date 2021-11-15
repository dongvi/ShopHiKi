package com.example.shophiki.home.products;

import android.widget.ImageView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("id")
    @Expose
        int id;
    @SerializedName("name")
    @Expose
        String name;
    @SerializedName("pic")
    @Expose
        String[] pic;
    @SerializedName("price")
    @Expose
        Double price;
    @SerializedName("originalAmount")
    @Expose
        int originalAmount;
    @SerializedName("currentAmount")
    @Expose
        int currentAmount;
    @SerializedName("sale")
    @Expose
        double sale;
    @SerializedName("detailInfo")
    @Expose
        String detailInfo;
    @SerializedName("voteStar")
    @Expose
        double voteStar;
    @SerializedName("brand")
    @Expose
        String brand;
    @SerializedName("origin")
    @Expose
        String origin;
    @SerializedName("year")
    @Expose
        String year;
    @SerializedName("warranty")
    @Expose
        String warranty;
    @SerializedName("type")
    @Expose
        String type;
    @SerializedName("isHiKi")
    @Expose
        boolean isHiKi;

    private int amount;

    public Product(int id, String name, String[] pic, double price, int originalAmount,
                   int currentAmount, double sale, String detailInfo, double voteStar,
                   String brand, String origin, String year, String warranty, String type, boolean isHiKi) {

        this.id = id;
        this.name = name;
        this.pic = pic;
        this.price = price;
        this.originalAmount = originalAmount;
        this.currentAmount = currentAmount;
        this.sale = sale;
        this.detailInfo = detailInfo;
        this.voteStar = voteStar;
        this.brand = brand;
        this.origin = origin;
        this.year = year;
        this.warranty = warranty;
        this.type = type;
        this.isHiKi = isHiKi;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getPic() {
        return pic;
    }

    public void setPic(String[] pic) {
        this.pic = pic;
    }

    public Double getPrice() {
        return price;
    }

    public double getSale() {
        return sale;
    }

    public double getVoteStar() {
        return voteStar;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    public void setVoteStar(double voteStar) {
        this.voteStar = voteStar;
    }


    public int getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(int originalAmount) {
        this.originalAmount = originalAmount;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(int currentAmount) {
        this.currentAmount = currentAmount;
    }

    public String getDetailInfo() {
        return detailInfo;
    }

    public void setDetailInfo(String detailInfo) {
        this.detailInfo = detailInfo;
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isHiKi() {
        return isHiKi;
    }

    public void setHiKi(boolean hiKi) {
        isHiKi = hiKi;
    }
}
