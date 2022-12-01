package com.example.cosc341_finalproject;

import android.content.res.Resources;

import androidx.appcompat.widget.LinearLayoutCompat;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Global { //this is essentially like our database lol
    public static List<String> newcart_items = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();
    public static boolean firstRun = false;
    public static List<storeCart> carts = new ArrayList<>();
    public static List<FaveProduct> faves = new ArrayList<>();
}
