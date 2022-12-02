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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ReplaceItem extends AppCompatActivity {

    LinearLayoutCompat cards;
    String baseItem = "";
    String brand = "";
    int weight = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replace_item);


        EditText brandEditText = (EditText) findViewById(R.id.brandNameSearch);
        brandEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    brand = v.getText().toString();
                    updateCards();
                    handled = true;
                }
                return handled;
            }
        });

        EditText weightEditText = (EditText) findViewById(R.id.weightSearch);
        weightEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    try {
                        weight = Integer.parseInt(v.getText().toString());
                    } catch (Exception e){
                        weight = 0;
                    }
                    updateCards();
                    handled = true;
                }
                return handled;
            }
        });

        cards = findViewById(R.id.cards);
        baseItem = "rice";
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

    public void updateCards(){
        List<Product> prods = Product.getProductsByBaseItem(baseItem);
        if(brand.length() > 1){
            prods = Product.getProductsByBrand(brand,prods);
        }
        if(weight > 0){
            prods = Product.getProductCloseToWeight(weight*1000,prods);
        }


        clearCards();

        int total = prods.size();
        cards = findViewById(R.id.cards);
        for (int i = 0; i < total; i++) {
            CardView newCard = new CardView(ReplaceItem.this);
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

    public void onDone(View v){
        finish();
    }
}
