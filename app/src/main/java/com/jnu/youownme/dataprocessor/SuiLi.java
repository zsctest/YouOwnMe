package com.jnu.youownme.dataprocessor;

import com.jnu.youownme.R;

import java.io.Serializable;

public class SuiLi implements Serializable {
    private static int imageResourceId = R.drawable.suili;
    private String name;
    private String about;
    private String money;
    private String date;


    public SuiLi(String name, String about, String money, String date) {
        this.name = name;
        this.about = about;
        this.money = money;
        this.date = date;
    }

    public static int getImageResourceId() {
        return imageResourceId;
    }


    public String getName() {
        return name;
    }

    public String getAbout() {
        return about;
    }

    public String getMoney() {
        return money;
    }

    public String getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
