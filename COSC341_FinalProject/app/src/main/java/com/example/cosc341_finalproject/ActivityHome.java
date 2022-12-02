package com.example.cosc341_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

       //Intent intent = new Intent(this, ReplaceItem.class);
        //startActivity(intent);

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

            Scanner s = new Scanner(input);
            s.nextLine(); // skip csv headers
            int i = 0;
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] stuff = line.split(",");
                String brand = stuff[0];
                String base = stuff[1];
                double price = Double.parseDouble(stuff[2]);
                int weight = Integer.parseInt(stuff[3]);
                double value = Double.parseDouble(stuff[4]);
                String store = stuff[5];
                String fileName = stuff[6];
                Product p = new Product(brand,base,price,weight,value,store,fileName);
                p.addItemToGlobalVar();
                //add stores
                Store storeWithPossiblySameName = new Store(store);
                boolean storeHasSameName = false;
                for (Store storesAlreadyExisting : Global.stores) {
                    if (storesAlreadyExisting.getStorename().equals(store)) {
                        storeHasSameName = true;
                    }
                }
                if (storeHasSameName == false)
                    Global.stores.add(storeWithPossiblySameName);
            }
            input.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void pressNewCart(View view) {
        Intent intent = new Intent(this, NewCart.class);
        startActivity(intent);
    }
    public void pressSavedCarts(View view) {
        Intent intent = new Intent(this, SavedCarts.class);
        startActivity(intent);
    }
    public void pressUserSettings(View view) {
        Intent intent = new Intent(this, UserSettings.class);
        startActivity(intent);
    }
    public void onPressOkayHelp(View view) {
        TextView helpText = findViewById(R.id.textViewhelpHome);
        helpText.setVisibility(View.INVISIBLE);
        Button helpButton = findViewById(R.id.buttonOkay);
        helpButton.setVisibility(View.INVISIBLE);
    }
    public void onPressNeedHelp(View view) {
        TextView helpText = findViewById(R.id.textViewhelpHome);
        helpText.setVisibility(View.VISIBLE);
        Button helpButton = findViewById(R.id.buttonOkay);
        helpButton.setVisibility(View.VISIBLE);
    }
}
