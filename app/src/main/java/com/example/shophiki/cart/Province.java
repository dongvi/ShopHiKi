package com.example.shophiki.cart;

import java.util.List;

public class Province {
    private String name;
    private District[] districts;

    public Province(String name, District[] districts) {
        this.name = name;
        this.districts = districts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public District[] getDistricts() {
        return districts;
    }

    public void setDistricts(District[] districts) {
        this.districts = districts;
    }
}
