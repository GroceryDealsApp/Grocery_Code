package com.example.cosc341_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ActivityHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if(!Global.firstRun){
            loadData();
            Global.firstRun = true;
        }
        Intent intent = new Intent(this, ReplaceItem.class);
        startActivity(intent);
        //ImageButton btn = (ImageButton) findViewById(R.id.buttonNewCart);
        //int width = getResources().getDisplayMetrics().widthPixels/3;
        //btn.setLayoutParams(new RelativeLayout.LayoutParams(width, height));
    }

    //loads data from Items.csv into Global.products
    public void loadData(){
        AssetManager assetManager = getAssets();
        InputStream input;
        try {
            input = assetManager.open("Items.csv");

            Scanner s = new Scanner(input).useDelimiter(",");
            s.nextLine(); // skip csv headers
            while (s.hasNextLine()) {
                s.nextLine();
                String brand = s.next();
                String base = s.next();
                double price = s.nextDouble();
                int weight = s.nextInt();
                double value = s.nextDouble();
                String store = s.next();
                String fileName = s.next();
                Product p = new Product(brand,base,price,weight,value,store,fileName);
                p.addItemToGlobalVar();
            }
            input.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
