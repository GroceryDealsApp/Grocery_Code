package com.example.cosc341_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewCart extends AppCompatActivity {
    String baseItem = "";
    LinearLayoutCompat cards;
    //boolean first = true;
    String item = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cart);
        EditText NewCartSearch = (EditText) findViewById(R.id.NewCartSearch);
        List<Product> pp = Product.getProductsByBaseItem(baseItem);
        int total = pp.size();
        cards = findViewById(R.id.cards);


            NewCartSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    boolean handled = false;
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        baseItem = v.getText().toString();
                        updateCards();
                        handled = true;
                    }
                    return handled;
                }
            });
            //cards = findViewById(R.id.cards);
            //baseItem = "rice";
            // updateCards();
        }


        public void clearCards () {
            int count = cards.getChildCount();
            View v = null;
            for (int i = count - 1; i >= 0; i--) {
                v = cards.getChildAt(i);
                ((ViewGroup) v.getParent()).removeView(v);
            }
        }

        public void updateCards () {
            List<Product> prods = Product.getProductsByBaseItem(baseItem);
            clearCards();

            int total = prods.size();
            cards = findViewById(R.id.cards);
            for (int i = 0; i < total; i++) {
                CardView newCard = new CardView(NewCart.this);
                getLayoutInflater().inflate(R.layout.card_base2, newCard);

                TextView itemName = newCard.findViewById(R.id.Item1);
                TextView weight = newCard.findViewById(R.id.Weight1);
                TextView value = newCard.findViewById(R.id.Value1);
                TextView price = newCard.findViewById(R.id.Price1);
                ImageView v = newCard.findViewById(R.id.itemImage1);

                Product p = prods.get(i);

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
        }

        public void allItems () {
            List<Product> prods = Global.products;
            clearCards();
            int total = prods.size();
            for (int i = 0; i < total; i++) {
                CardView newCard = new CardView(NewCart.this);
                getLayoutInflater().inflate(R.layout.card_base2, newCard);

                TextView itemName = newCard.findViewById(R.id.Item1);
                TextView weight = newCard.findViewById(R.id.Weight1);
                TextView value = newCard.findViewById(R.id.Value1);
                TextView price = newCard.findViewById(R.id.Price1);
                ImageView v = newCard.findViewById(R.id.itemImage1);

                Product p = prods.get(i);

                String imageName = p.getFileNameWithoutExtension();
                int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
                v.setImageResource(resID);

                itemName.setText(p.getFullName());
                weight.setText(p.getFormattedWeight());
                value.setText(p.getFormattedValue());
                price.setText(p.getFormattedPrice());
                try {
                    newCard.setTag(i);
                    cards.addView(newCard);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        }

        public void onSearchClick (View view){
            List<Product> pp = Product.getProductsByBaseItem(baseItem);



            int total = pp.size();
            cards = findViewById(R.id.cards);
            for (int i = 0; i < total; i++) {
                CardView newCard = new CardView(NewCart.this);
                final int finalI = i;

                getLayoutInflater().inflate(R.layout.card_base2, newCard);

                TextView itemName = newCard.findViewById(R.id.Item1);
                TextView weight = newCard.findViewById(R.id.Weight1);
                TextView value = newCard.findViewById(R.id.Value1);
                TextView price = newCard.findViewById(R.id.Price1);
                ImageView v = newCard.findViewById(R.id.itemImage1);

                Product p = pp.get(i);

                String imageName = p.getFileNameWithoutExtension();
                int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
                v.setImageResource(resID);

                itemName.setText(p.getFullName());
                item = p.getFullName();
                weight.setText(p.getFormattedWeight());
                value.setText(p.getFormattedValue());
                price.setText(p.getFormattedPrice());


                newCard.setTag(i);
                cards.addView(newCard);
                newCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    Global.items.add(item);
                    }
                });

            }
        }

        public void onBackNewClick (View view){
            finish();
        }
       // public void onClick (View view){
       //     System.out.println(view.getTag());
        //    cards.getChildAt((Integer) view.getTag());


        }



