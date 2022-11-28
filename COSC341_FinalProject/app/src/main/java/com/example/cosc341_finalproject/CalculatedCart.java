package com.example.cosc341_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class CalculatedCart extends AppCompatActivity {
    private int starter = 66; //ASCII code for `B`
    LinearLayoutCompat cards;
    Button buttonAdd;
    Button buttonDoSth;
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculated_cart);
        Global.items.add("Foodie White Rice");
        Global.items.add("Foodie Bacon");

        cards = findViewById(R.id.cards);



        total = Global.items.size();
        for(int i = 0; i < total; i++){
            CardView newCard = new CardView(CalculatedCart.this);
            getLayoutInflater().inflate(R.layout.card_base, newCard);
            TextView t = newCard.findViewById(R.id.Item1);
            t.setText(Global.items.get(i));
            newCard.setTag(i);
            cards.addView(newCard);

        }


    }


        }


