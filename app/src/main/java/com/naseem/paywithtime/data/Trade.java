package com.naseem.paywithtime.data;

/**
 * Created by user on 08/03/2018.
 */

public class Trade {
    private String Adress;
    private double price;
    private double Hours;
    private boolean isCompleted;
    private String imgPath;
    private String keyId;
    private String email;

    public Trade(String name, double price, double amount) {
        this.Adress = name;
        this.price = price;
        this.Hours = amount;
        isCompleted=false;
        imgPath=null;
    }

    public Trade(String name, double price, double amount, boolean isCompleted, String imgPath, String keyId) {
        this.Adress = name;
        this.price = price;
        this.Hours = amount;
        this.isCompleted = isCompleted;
        this.imgPath = imgPath;
        this.keyId = keyId;
    }

    public Trade(){}

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getHours() {
        return Hours;
    }

    public void setHours(double hours) {
        Hours = hours;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

