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

import java.util.List;

public class FavouriteItemStores extends AppCompatActivity {
    LinearLayoutCompat cards;
    String faveFullName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_item_stores);

        Intent intent = getIntent();
        faveFullName = intent.getStringExtra("NameofItem");

        TextView title = findViewById(R.id.textViewFaveInStores);
        title.setText("All Stores That Carry " + faveFullName);

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
        clearFavCards();

        List<Product> allProdsOfFaveItem = Product.getProductsByFullName(faveFullName);

        int total = allProdsOfFaveItem.size();
        for (int i = 0; i < total; i++) {
            CardView newCard = new CardView(FavouriteItemStores.this);
            getLayoutInflater().inflate(R.layout.card_base_favitem_stores, newCard);

            TextView storenametext = newCard.findViewById(R.id.storenametext_fav);
            TextView itemName = newCard.findViewById(R.id.Item1);
            TextView weight = newCard.findViewById(R.id.Weight1);
            TextView value = newCard.findViewById(R.id.Value1);
            TextView price = newCard.findViewById(R.id.Price1);
            ImageView v = newCard.findViewById(R.id.itemImage1);

            Product p = allProdsOfFaveItem.get(i);

            String imageName = p.getFileNameWithoutExtension();
            int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
            v.setImageResource(resID);

            storenametext.setText(p.getStore());
            value.setText(p.getFormattedValue());
            price.setText(p.getFormattedPrice());
            itemName.setText(p.getFullName());
            weight.setText(p.getFormattedWeight());

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


}