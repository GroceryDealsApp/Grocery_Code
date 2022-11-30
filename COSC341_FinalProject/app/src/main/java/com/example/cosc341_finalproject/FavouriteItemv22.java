package com.example.cosc341_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
        List<Product> prods = new ArrayList<>(); //should just show nothing by default
        // For Each Loop for iterating all favourite items
        for (String favName : Global.faves) {
            //test = test + "\n" + favName;
            //find the favourite item and add the product to prods
            int totalprods = Global.products.size();
            for (int i = 0; i < totalprods; i++) {
                Product p = Global.products.get(i);
                if((p.getFullName().toLowerCase()).contains(favName.toLowerCase())){
                    prods.add(p);
                }
            }
        }

        //tvf.setText(test);
        clearFavCards();

        int total = prods.size();
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

            Product p = prods.get(i);

            String imageName = p.getFileNameWithoutExtension();
            int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
            v.setImageResource(resID);

            itemName.setText(p.getFullName());
            weight.setText(p.getFormattedWeight());
            //value.setText(p.getFormattedValue());
            //price.setText(p.getFormattedPrice());
            delButt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Global.faves.remove(Global.faves.indexOf(itemName.getText().toString()));
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
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {
            //do the things u wanted
            updateFavCards();
        }
    }

    public void onDone(View v){
        finish();
    }

    public void onAddNewFav(View view) {
        Intent intent = new Intent(this, FavouriteItem2Search.class);
        startActivity(intent);
    }
}