package com.example.cosc341_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class CalculatedCart extends AppCompatActivity {
    private int starter = 66; //ASCII code for `B`
    LinearLayoutCompat cards;
    Button DoneButton;
    private int total;
    double totalPrice = 0;
    TextView TotalCalc;
    String storeName = "placeholder";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculated_cart);

        Intent intent = getIntent();
        storeName = intent.getStringExtra("store"); //receive the store name
        TextView storeNameText =  findViewById(R.id.StoreNameCalc);
        storeNameText.setText(storeName);
        //find the correct storecart
        storeCart storecart = null;
        for (storeCart cartThatMightMatch : Global.carts) {
            if (cartThatMightMatch.getStore().equals(storeName)){
                storecart = cartThatMightMatch;
            }
        }

        List<Product> prods = storecart.getCartItems();
       // buttonSwap = findViewById(R.id.SwapButton1);
        TotalCalc = findViewById(R.id.TotalCalc);
        cards = findViewById(R.id.cards);
        int total = prods.size();
        cards = findViewById(R.id.cards);
        for (int i = 0; i < total; i++) {
            CardView newCard = new CardView(CalculatedCart.this);
            getLayoutInflater().inflate(R.layout.card_swap, newCard);

            TextView itemName = newCard.findViewById(R.id.Item1);
            TextView weight = newCard.findViewById(R.id.Weight1);
            TextView value = newCard.findViewById(R.id.Value1);
            TextView price = newCard.findViewById(R.id.Price1);

            ImageView v = newCard.findViewById(R.id.itemImage1);

            Product p = prods.get(i);
            totalPrice = totalPrice + p.getPrice();
            String imageName = p.getFileNameWithoutExtension();
            int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
            v.setImageResource(resID);

            itemName.setText(p.getFullName());
            weight.setText(p.getFormattedWeight());
            value.setText(p.getFormattedValue());
            price.setText(p.getFormattedPrice());
            newCard.setTag(i);
            cards.addView(newCard);



        }
        String pp = "Grand total: $" + Double.toString(totalPrice);
        TotalCalc.setText(pp);
         DoneButton = findViewById(R.id.DoneButton);
         DoneButton.setVisibility(View.VISIBLE);
       DoneButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               FragmentManager fragmentManager = getSupportFragmentManager();
               fragmentManager.beginTransaction()
                       .replace(R.id.fragmentContainerView2, SaveCartQuestion.class, null)
                       .setReorderingAllowed(true)
                       .addToBackStack("name")
                       .commit();


               DoneButton.setVisibility(View.GONE);

           }
       });

        }
    public void onBack(View view){
        finish();


    }








}








