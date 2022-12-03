package com.example.cosc341_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SavedCarts extends AppCompatActivity {
    LinearLayoutCompat cards;
    /*TextView SavedCartText;
    Button EditName;
    Button StartCart;
    String name = "placeholder";
    int total;
    SavedStoreCarts savedcart = null;
    String cartItems;

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_carts);

        cards = findViewById(R.id.cards);
        updateCards();


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

        int total = Global.SavedCarts.size();
        for (int i = 0; i < total; i++) {
            CardView newCard = new CardView(SavedCarts.this);
            getLayoutInflater().inflate(R.layout.card_savedcart, newCard);

            EditText cartNameText = newCard.findViewById(R.id.SavedCartText);
            Button startCartButt = newCard.findViewById(R.id.StartCart);
            Button editnamebutt = newCard.findViewById(R.id.EditName);

            List<String> cartitems = Global.SavedCarts.get(i).getItems();

            String cartName = Global.SavedCarts.get(i).getName();
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

            int finalI = i;
            editnamebutt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (startCartButt.isEnabled()) { //if editing
                        startCartButt.setEnabled(false);
                        editnamebutt.setText("Done");
                        cartNameText.setEnabled(true);
                    } else { //if done editing
                        startCartButt.setEnabled(true);
                        cartNameText.setEnabled(false);
                        Global.SavedCarts.get(finalI).setName(cartNameText.getText().toString());
                        editnamebutt.setText("Edit Name");
                    }
                }
            });
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
    /*
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

     */

    public void onBack(View view) {
        finish();
    }

    }

