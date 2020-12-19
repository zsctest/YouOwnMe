package com.jnu.youownme.dataprocessor;


import com.jnu.youownme.R;

import java.io.Serializable;


public class HomeDisplay implements Serializable {
//    private int imageResourceId = R.drawable.title;
    private String name;
    private String about;
    private String money;
    private String date;

    public HomeDisplay(String name, String about, String money, String date) {
//        this.imageResourceId = imageResourceId;
        this.name = name;
        this.about = about;
        this.money = money;
        this.date = date;
    }

//    public HomeDisplay(int imageResourceId, String name, String about, String money, String date) {
//        this.imageResourceId = imageResourceId;
//        this.name = name;
//        this.about = about;
//        this.money = money;
//        this.date = date;
//    }

    public HomeDisplay(ShouLi obj){
//        this.imageResourceId = obj.getImageResourceId();
        this.name = obj.getName();
        this.about = obj.getAbout();
        this.money = obj.getMoney();
        this.date = obj.getMoney();
    }

    public HomeDisplay(SuiLi obj){
//        this.imageResourceId = obj.getImageResourceId();
        this.name = obj.getName();
        this.about = obj.getAbout();
        this.money = obj.getMoney();
        this.date = obj.getMoney();
    }

//    public int getImageResourceId() {
//        return imageResourceId;
//    }

//    public void setImageResourceId(int imageResourceId) {
//        this.imageResourceId = imageResourceId;
//    }

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
}
