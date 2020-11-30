package com.jnu.youownme.dataprocessor;

import java.io.Serializable;

public class ShouLi implements Serializable {
    private String name;
    private String about;
    private String money;
    private String date;

    public ShouLi(String name, String about, String money, String date) {
        this.name = name;
        this.about = about;
        this.money = money;
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
