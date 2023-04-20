package com.example.myapplication.models;

public class Item {
    int available;
    String name;
    int price;
    int estimateTime;


    public Item(String name, int price, int estimateTime,int available) {
        this.available = available;
        this.name = name;
        this.price = price;
        this.estimateTime = estimateTime;
    }


//    public Item(int itemId, String name, int price, int estimateTime) {
//        this.itemId = itemId;
//        this.name = name;
//        this.price = price;
//        this.estimateTime = estimateTime;
//    }

    public int getItemId() {
        return available;
    }

    public void setItemId(int available) {
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getEstimateTime() {
        return estimateTime;
    }

    public void setEstimateTime(int estimateTime) {
        this.estimateTime = estimateTime;
    }


}
