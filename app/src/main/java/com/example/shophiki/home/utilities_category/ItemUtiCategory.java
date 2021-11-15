package com.example.shophiki.home.utilities_category;

import android.widget.ImageView;

public class ItemUtiCategory {
    private int imgItem;
    private String nameItem;

    public ItemUtiCategory(int imgItem, String nameItem) {
        this.imgItem = imgItem;
        this.nameItem = nameItem;
    }

    public int getImgItem() {
        return imgItem;
    }

    public void setImgItem(int imgItem) {
        this.imgItem = imgItem;
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }
}
