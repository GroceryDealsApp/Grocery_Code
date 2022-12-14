package com.example.cosc341_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NewCartSearch extends AppCompatActivity {
    String fullname = "";
    LinearLayoutCompat cards;
    //boolean first = true;
    String item = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cart_search);
        EditText NewCartSearch = (EditText) findViewById(R.id.NewCartSearch);
        List<Product> pp = Product.getProductsByFullName("");
        NewCartSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    fullname = v.getText().toString();
                    updateCards();
                    handled = true;
                }
                return handled;
            }
        });
        cards = findViewById(R.id.cards);
        updateCards();

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
        List<Product> prods = Product.getProductsByFullName("");
        System.out.println("click with " + fullname);
        if(fullname.length()>1){
            prods = Product.getProductsByFullName(fullname, prods);
        }
        clearCards();

        int total = prods.size();
        cards = findViewById(R.id.cards);
        for (int i = 0; i < total; i++) {
            CardView newCard = new CardView(NewCartSearch.this);
            getLayoutInflater().inflate(R.layout.card_add_newcart_item, newCard);

            TextView itemName = newCard.findViewById(R.id.Item1);
            TextView weight = newCard.findViewById(R.id.Weight1);
            //TextView value = newCard.findViewById(R.id.Value1);
            //TextView price = newCard.findViewById(R.id.Price1);
            ImageView v = newCard.findViewById(R.id.itemImage1);
            Button AddButtonNew = newCard.findViewById(R.id.AddButtonNew);

            Product p = prods.get(i);

            String imageName = p.getFileNameWithoutExtension();
            int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
            v.setImageResource(resID);

            itemName.setText(p.getFullName());
            weight.setText(p.getFormattedWeight());
            //value.setText(p.getFormattedValue());
            //price.setText(p.getFormattedPrice());
            AddButtonNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = itemName.getText().toString();
                    int totalprods = Global.products.size();
                   // Global.items.add(itemName.getText().toString());
                    Global.newcart_items.add(name);
                    /*
                    for(int j = 0; j <  totalprods; j++){
                        Product p = Global.products.get(j);
                        if((p.getFullName().toLowerCase()).contains(name.toLowerCase())){
                            //Product pp = new Product(p.getBrandName(), p.getBaseItemName(), p.getPrice(), p.getWeight(), p.getValue(), p.getStore(), p.getFileNameWithoutExtension());
                            Global.carts.add(name);
                            j= totalprods;
                        }
                    }
                    */

                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        finish();
                    }
            });
            newCard.setTag(i);
            cards.addView(newCard);
            }

            }










        public void onBack (View view){
        finish();
        }

    }
