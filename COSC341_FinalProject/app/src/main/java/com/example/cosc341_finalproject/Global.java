package com.example.cosc341_finalproject;

import android.content.res.Resources;

import androidx.appcompat.widget.LinearLayoutCompat;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Global {
    public static List<String> items = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();
    public static boolean firstRun = false;
    public static List<String> carts = new ArrayList<>();
    public static List<FaveProduct> faves = new ArrayList<>();
}
