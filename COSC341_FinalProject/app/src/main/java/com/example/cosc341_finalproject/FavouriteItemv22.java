package com.example.cosc341_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FavouriteItemv22 extends AppCompatActivity {
    LinearLayoutCompat cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_itemv22);

        cards = findViewById(R.id.cards);
        updateFavCards();

    }

    public void clearFavCards(){
        int count = cards.getChildCount();
        View v = null;
        for(int i=count-1; i>=0; i--) {
            v = cards.getChildAt(i);
            ((ViewGroup) v.getParent()).removeView(v);
        }
    }
    public void updateFavCards(){
        //TextView tvf = findViewById(R.id.textViewFav);
        //String test = "";
        //List<FaveProduct> faveprods = new ArrayList<>(); //should just show nothing by default
        //faveprods = Product.getProductsByBrand(brand,faveprods);
        // For Loop for iterating all favourite items
        //int numIt = Global.faves.size();
        //for (int i = 0; i < numIt; i++) {
        //    FaveProduct p = new FaveProduct();
        //}

        //tvf.setText(test);
        clearFavCards();

        int total = Global.faves.size();
        cards = findViewById(R.id.cards);
        for (int i = 0; i < total; i++) {
            //test = test + "\n" + i;
            //tvf.setText(test);
            CardView newCard = new CardView(FavouriteItemv22.this);
            getLayoutInflater().inflate(R.layout.card_base_fav2, newCard);

            TextView itemName = newCard.findViewById(R.id.Item1);
            TextView weight = newCard.findViewById(R.id.Weight1);
            //TextView value = newCard.findViewById(R.id.Value1);
            //TextView price = newCard.findViewById(R.id.Price1);
            ImageView v = newCard.findViewById(R.id.itemImage1);
            Button delButt = newCard.findViewById(R.id.DeleteFavButton);
            ImageButton notifButt = newCard.findViewById(R.id.ButtonNotif);

            FaveProduct p = Global.faves.get(i);

            String imageName = p.getFileNameWithoutExtension();
            int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
            v.setImageResource(resID);

            //if else for notification
            itemName.setText(p.getFullName());
            weight.setText(p.getFormattedWeight());
            int bellID;
            if (p.getNotifEnabled() == true) {
                bellID = getResources().getIdentifier("notif_enabled", "drawable", getPackageName());
                //TODO: Add functionality for notifcations
            } else {
                bellID = getResources().getIdentifier("notif_disabled", "drawable", getPackageName());
            }
            notifButt.setImageResource(bellID);
            //value.setText(p.getFormattedValue());
            //price.setText(p.getFormattedPrice());
            delButt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Global.faves.remove(Global.faves.indexOf(p));
                    updateFavCards();
                }
            });
            notifButt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (p.getNotifEnabled() == true) {
                        p.setNotifEnabled(false);
                        Toast.makeText(getApplicationContext(), "Notifications for " + p.getFullName() + " disabled", Toast.LENGTH_SHORT).show();
                    }else {
                        p.setNotifEnabled(true);
                        Toast.makeText(getApplicationContext(), "Notifications for " + p.getFullName() + " enabled", Toast.LENGTH_SHORT).show();
                    }
                    updateFavCards();
                }
            });
            newCard.setTag(i);
            cards.addView(newCard);

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 1
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                updateFavCards();
            }
        }
    }

    public void onDone(View v){
        finish();
    }

    public void onAddNewFav(View view) {
        Intent intent = new Intent(this, FavouriteItem2Search.class);
        startActivityForResult(intent, 1);
    }
}