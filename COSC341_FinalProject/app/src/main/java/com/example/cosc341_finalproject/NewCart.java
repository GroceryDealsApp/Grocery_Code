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

import java.util.List;

public class NewCart extends AppCompatActivity {
    //String baseItem = "";
    //TextView itemName;
    LinearLayoutCompat cards;
    //boolean first = true;
    // String item = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cart);
        //  EditText NewCartSearch = (EditText) findViewById(R.id.NewCartSearch);
        //  List<Product> pp = Product.getProductsByBaseItem(baseItem);
        //int total = pp.size();

        cards = findViewById(R.id.cards);
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

    public void updateCards() {
        List<Product> prods = Product.getProductsByBaseItem("asdhioashdoa");
        //TextView ncText = findViewById(R.id.textViewNewCart);
        //String test = "";
        for (String item : Global.newcart_items) {
            //test = test + "\n" + item;
            //find the favourite item and add the product to prods
            int totalprods = Global.products.size();
            for (int i = 0; i < totalprods; i++) {
                Product p = Global.products.get(i);
                if ((p.getFullName().toLowerCase()).contains(item.toLowerCase())) {
                    prods.add(p);
                }
            }
        }
        //ncText.setText(test);
        clearCards();
        int total = Global.newcart_items.size();
        cards = findViewById(R.id.cards);
        for (int i = 0; i < total; i++) {
            CardView newCard = new CardView(NewCart.this);
            getLayoutInflater().inflate(R.layout.card_newcart__item, newCard);

            TextView itemName = newCard.findViewById(R.id.Item1);
            TextView weight = newCard.findViewById(R.id.Weight1);
            //TextView value = newCard.findViewById(R.id.Value1);
            //TextView price = newCard.findViewById(R.id.Price1);
            ImageView v = newCard.findViewById(R.id.itemImage1);
            Button delNewItemButt = newCard.findViewById(R.id.deletenewItemButton);

            Product p = prods.get(i);

            String imageName = p.getFileNameWithoutExtension();
            int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
            v.setImageResource(resID);
            delNewItemButt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Global.newcart_items.remove(Global.newcart_items.indexOf(p.getFullName()));
                    updateCards();
                }
            });
            itemName.setText(p.getFullName());
            weight.setText(p.getFormattedWeight());
            //value.setText(p.getFormattedValue());
            //price.setText(p.getFormattedPrice());
            //   addButt.setOnClickListener(new View.OnClickListener() {
            //    @Override
            //  public void onClick(View view) {
            //   Global.items.add(itemName.getText().toString());
            //     finish();
            //  }
            //  });

            newCard.setTag(i);
            cards.addView(newCard);

        }
    }

      /*  public void allItems () {
            List<Product> prods = Global.products;
            clearCards();
            int total = prods.size();
            for (int i = 0; i < total; i++) {
                CardView newCard = new CardView(NewCart.this);
                getLayoutInflater().inflate(R.layout.card_comparison, newCard);

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

        }*/

       /* public void onSearchClick (View view){
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

                weight.setText(p.getFormattedWeight());
                value.setText(p.getFormattedValue());
                price.setText(p.getFormattedPrice());


                newCard.setTag(i);
                cards.addView(newCard);

            }
        }*/

        public void onBackNewClick (View view){
            finish();
        }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 1
        if (requestCode == 2) {
            if(resultCode == RESULT_OK) {
                updateCards();
            }
        }

    }
        public void onAddNewItem(View view) {
            Intent intent = new Intent(this, NewCartSearch.class);
            startActivityForResult(intent, 2);
        }
        public void onDone(View v){
            Intent intent = new Intent(this, StoreComparison.class);
            startActivity(intent);
        }



    }




