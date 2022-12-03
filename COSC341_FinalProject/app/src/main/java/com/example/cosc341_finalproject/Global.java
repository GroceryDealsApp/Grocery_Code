package com.example.cosc341_finalproject;

import java.util.ArrayList;
import java.util.List;

public class Global { //this is essentially like our database lol
    public static List<String> newcart_items = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();
    public static boolean firstRun = false;
    public static List<storeCart> carts = new ArrayList<>();
    public static List<FaveProduct> faves = new ArrayList<>();
    public static List<Store> stores = new ArrayList<>();
    public static boolean check1 = true;
    public static boolean check2 = false;
    public static boolean check3 = true;
    public static List<SavedStoreCarts> SavedCarts = new ArrayList<SavedStoreCarts>();
}
