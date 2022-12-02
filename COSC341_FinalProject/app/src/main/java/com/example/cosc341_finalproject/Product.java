package com.example.cosc341_finalproject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Product {
    private String brandName = "";
    private String baseItemName = "";
    private double price = 0.0;
    private int weight = 0;
    private double value = 0.0;
    private String store = "";
    private String filename = "";
    private NumberFormat currFormat = NumberFormat.getCurrencyInstance();
    private String weightUnit = "kg";

    Product(String brand,String base,double price,int weight,double value,String store,String filename){
        this.setBrandName(brand);
        this.setBaseItemName(base);
        this.setPrice(price);
        this.setWeight(weight);
        this.setValue(value);
        this.setStore(store);
        this.setFilename(filename);
    }


    //global filter functions
    static public List<Product> getProductsByBaseItem(String base){
        List<Product> things = new ArrayList<>();
        int total = Global.products.size();
        for (int i = 0; i < total; i++) {
            Product p = Global.products.get(i);
            if((p.getBaseItemName().toLowerCase()).contains(base.toLowerCase())){
                things.add(p);
            }
        }
        return things;
    }
    static public List<Product> getProductsByBaseItemFromStore(String base, String store){
        List<Product> things = new ArrayList<>();
        int total = Global.products.size();
        for (int i = 0; i < total; i++) {
            Product p = Global.products.get(i);
            if((p.getBaseItemName().toLowerCase()).contains(base.toLowerCase()) && (p.getStore().toLowerCase()).contains(store.toLowerCase())){
                things.add(p);
            }
        }
        return things;
    }
    static public List<Product> getProductsByBaseItem(String base, List<Product> existingList){
        List<Product> things = new ArrayList<>();
        int total = existingList.size();
        for (int i = 0; i < total; i++) {
            Product p = existingList.get(i);
            if((p.getBaseItemName().toLowerCase()).contains(base.toLowerCase())){
                things.add(p);
            }
        }
        return things;
    }

    static public List<Product> getProductsByBrand(String brand){
        List<Product> things = new ArrayList<>();
        int total = Global.products.size();
        for (int i = 0; i < total; i++) {
            Product p = Global.products.get(i);
            if((p.getBrandName().toLowerCase()).contains(brand.toLowerCase())){
                things.add(p);
            }
        }
        return things;
    }

    static public List<Product> getProductsByBrand(String brand, List<Product> existingList){
        List<Product> things = new ArrayList<>();
        int total = existingList.size();
        for (int i = 0; i < total; i++) {
            Product p = existingList.get(i);
            if((p.getBrandName().toLowerCase()).contains(brand.toLowerCase())){
                things.add(p);
            }
        }
        return things;
    }

    static public List<Product> getProductsByFullName(String fullname){
        List<Product> things = new ArrayList<>();
        int total = Global.products.size();
        for (int i = 0; i < total; i++) {
            Product p = Global.products.get(i);
            if((p.getFullName().toLowerCase()).contains(fullname.toLowerCase())){
                things.add(p);
            }
        }
        return things;
    }
    static public List<Product> getProductsByFullName(String fullname, List<Product> existingList){
        List<Product> things = new ArrayList<>();
        int total = existingList.size();
        for (int i = 0; i < total; i++) {
            Product p = existingList.get(i);
            if((p.getFullName().toLowerCase()).contains(fullname.toLowerCase())){
                things.add(p);
            }
        }
        return things;
    }

    static public List<Product> getProductCloseToWeight(int weightInGrams, List<Product> existingList){
        List<Product> things = new ArrayList<>();
        int total = existingList.size();
        for (int i = 0; i < total; i++) {
            Product p = existingList.get(i);
            if(p.getWeight() < weightInGrams + 1000 && p.getWeight() > weightInGrams - 1000){
                things.add(p);
            }
        }
        return things;
    }

    //improved getter functions for convenience
    public String getFileNameWithoutExtension(){
        String n = this.getFilename();
        return n.split("\\.")[0];
    }
    public String getFormattedPrice(){
        return currFormat.format(this.getPrice());
    }
    public String getFormattedValue(){
        return currFormat.format(this.getValue()) + "/100g";
    }
    public String getFormattedWeight(){
        DecimalFormat df = new DecimalFormat("#.#");
        return String.valueOf(df.format((double)this.getWeight()/(double)1000)) + " " + weightUnit;
    }
    public String getFullName(){
        return this.getBrandName() + " " + this.getBaseItemName();
    }

    public void addItemToGlobalVar(){
        Global.products.add(this);
    }

    //setters and getters
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBaseItemName() {
        return baseItemName;
    }

    public void setBaseItemName(String baseItemName) {
        this.baseItemName = baseItemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

}
