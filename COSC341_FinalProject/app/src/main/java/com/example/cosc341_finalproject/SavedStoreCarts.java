package com.example.cosc341_finalproject;

import java.util.List;

public class SavedStoreCarts {
    private String Name;
    private List<String> items;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


    public SavedStoreCarts(String name, List<String> items) {
        Name = name;
        this.items = items;
    }

    public SavedStoreCarts(List<String> items) {
        this.items = items;
        this.Name = "";
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
