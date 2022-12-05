package com.example.cosc341_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class ActivityHome extends AppCompatActivity {
    LinearLayoutCompat cards;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if(!Global.firstRun){
            loadData();
            Global.firstRun = true;
        } else {
            //make sure help screen is invisible when a recent cart is shown, since that *may* cause issues if both are visible
            TextView helpText = findViewById(R.id.textViewhelpHome);
            helpText.setVisibility(View.INVISIBLE);
            Button helpButton = findViewById(R.id.buttonOkay);
            helpButton.setVisibility(View.INVISIBLE);
        }

       //Intent intent = new Intent(this, ReplaceItem.class);
        //startActivity(intent);

        //ImageButton btn = (ImageButton) findViewById(R.id.buttonNewCart);
        //int width = getResources().getDisplayMetrics().widthPixels/3;
        //btn.setLayoutParams(new RelativeLayout.LayoutParams(width, height));
        cards = findViewById(R.id.cards);
        updateCards();


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

    public void clearCards(){
        int count = cards.getChildCount();
        View v = null;
        for(int i=count-1; i>=0; i--) {
            v = cards.getChildAt(i);
            ((ViewGroup) v.getParent()).removeView(v);
        }
    }

    public void updateCards() {
        clearCards();
        //start from last go to first recent cart
        int total = Global.RecentCarts.size();
        for (int i = total - 1; i >= 0; i--) {
            CardView newCard = new CardView(ActivityHome.this);
            getLayoutInflater().inflate(R.layout.card_recentcart, newCard);

            EditText cartNameText = newCard.findViewById(R.id.SavedCartText);
            Button startCartButt = newCard.findViewById(R.id.StartCart);

            List<String> cartitems = Global.RecentCarts.get(i).getItems();

            String cartName = Global.RecentCarts.get(i).getName();
            //if the cart's name is nothing, then have the name just be the items, otherwise let it be the name given
            StringBuffer sb= new StringBuffer(cartName);
            if (cartName == "") {
                for (String itemName : cartitems)
                    cartName += itemName + ", ";
                //remove the last comma
                sb= new StringBuffer(cartName);
                sb.deleteCharAt(sb.length()-1);
                sb.deleteCharAt(sb.length()-1);
            }
            cartNameText.setText(sb);

            startCartButt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Global.newcart_items = cartitems;
                    Intent intent = new Intent(v.getContext(), NewCart.class);
                    startActivity(intent);
                }
            });

            newCard.setTag(i);
            cards.addView(newCard);


        }

    }

}
