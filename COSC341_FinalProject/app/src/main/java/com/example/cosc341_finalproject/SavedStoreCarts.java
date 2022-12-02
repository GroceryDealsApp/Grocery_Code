package com.example.cosc341_finalproject;

public class SavedStoreCarts {
    private String Name;
    private String items;

    public String getName() {
        return Name;
    }

    public String getItems() {
        return items;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public SavedStoreCarts(String name, String items) {
        Name = name;
        this.items = items;
    }

    public SavedStoreCarts(String items) {
        this.items = items;
        this.Name = "";
    }
}
