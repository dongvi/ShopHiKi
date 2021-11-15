package com.example.shophiki.cart;

import java.util.List;

public class District {
    private String name;
    private String[] towns;

    public District(String name, String[] towns) {
        this.name = name;
        this.towns = towns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getTowns() {
        return towns;
    }

    public void setTowns(String[] towns) {
        this.towns = towns;
    }
}
