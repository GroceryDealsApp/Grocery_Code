package com.example.cosc341_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SavedCarts extends AppCompatActivity {
    LinearLayoutCompat cards = findViewById(R.id.cards);
    TextView SavedCartText;
    Button EditName;
    Button StartCart;
    String name = "placeholder";
    int total;
    SavedStoreCarts savedcart = null;
    String cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_carts);
        total = Global.SavedCarts.size();
        updateCards();



    }
        public void clearCards() {
            int count = cards.getChildCount();
            View v = null;
            for (int i = count - 1; i >= 0; i--) {
                v = cards.getChildAt(i);
                ((ViewGroup) v.getParent()).removeView(v);
                clearCards();
            }
        }
        public void updateCards(){
        clearCards();

                for(SavedStoreCarts s : Global.SavedCarts){
                    if(!(name.equals(""))){
                    if(s.getItems().equals(name)) {
                        savedcart = s;
                    }


                    }
                }
            for (int i = 0; i < total; i++) {
                CardView newCard = new CardView(SavedCarts.this);
                getLayoutInflater().inflate(R.layout.card_savedcart, newCard);
                SavedCartText= newCard.findViewById(R.id.SavedCartText);
                savedcart = null;


            }

        }

    }

