package com.example.cosc341_finalproject;

public class Store {
    private String storename;
    //TODO: add warnings/notifications for the stores + a way to show them (like "points!" or "big heavy items")

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public Store(String storename) {
        this.storename = storename;
    }
}