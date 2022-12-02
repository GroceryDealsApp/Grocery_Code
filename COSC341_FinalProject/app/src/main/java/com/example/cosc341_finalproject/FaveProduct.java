package com.example.cosc341_finalproject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class FaveProduct {
    private String fullName = "";
    private boolean notifEnabled = true; //notifications are enabled by default
    private int weight = 0;
    private Product bestProduct;
    private int daysLeft = 0; //unused, to be used with bestValue to show how long the bestValue is valid for
    private String weightUnit = "kg";
    private String filename = "";

    FaveProduct(String fullName, boolean notifEnabled, int weight, String filename){
        this.setFullName(fullName);
        this.setNotifEnabled(notifEnabled);
        this.setWeight(weight);
        this.setFilename(filename);
        updateBestProduct();
    }


    //update the FaveProduct bestProduct with whatever has most value
    private void updateBestProduct() {
        List<Product> allProdsWithSameName = Product.getProductsByFullName(fullName);
        bestProduct = allProdsWithSameName.get(0);
        for (Product p : allProdsWithSameName) {
            if (bestProduct.getValue() > p.getValue()) {
                bestProduct = p;
            }
        }
    }

    //i think these two methods below don't actually work lol
    static public List<FaveProduct> getProductsByFullName(String fullname){
        List<FaveProduct> things = new ArrayList<>();
        int total = Global.products.size();
        for (int i = 0; i < total; i++) {
            FaveProduct p = Global.faves.get(i);
            if((p.getFullName().toLowerCase()).contains(fullname.toLowerCase())){
                things.add(p);
            }
        }
        return things;
    }
    static public List<FaveProduct> getProductsByFullName(String fullname, List<FaveProduct> existingList){
        List<FaveProduct> things = new ArrayList<>();
        int total = existingList.size();
        for (int i = 0; i < total; i++) {
            FaveProduct p = existingList.get(i);
            if((p.getFullName().toLowerCase()).contains(fullname.toLowerCase())){
                things.add(p);
            }
        }
        return things;
    }

    public String getFileNameWithoutExtension(){
        String n = this.getFilename();
        return n.split("\\.")[0];
    }


    public String getFormattedWeight(){
        return String.valueOf(this.getWeight()/1000) + " " + weightUnit;
    }

    public void addItemToGlobalVar(){
        Global.faves.add(this);
    }

    //setters and getters
    public boolean getNotifEnabled() {
        return notifEnabled;
    }
    public void setNotifEnabled(boolean notifEnabled) {
        this.notifEnabled = notifEnabled;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Product getBestProduct() {
        return bestProduct;
    }

    public void setBestProduct(Product bestProduct) {
        this.bestProduct = bestProduct;
    }
}
