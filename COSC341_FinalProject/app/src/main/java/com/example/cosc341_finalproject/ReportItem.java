package com.example.cosc341_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ReportItem extends AppCompatActivity {
    String itemName;
    String storeName;
    LinearLayoutCompat cards;
    Product prod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_item);
        Intent intent = getIntent();
        itemName = intent.getStringExtra("NameofItem");
        storeName = intent.getStringExtra("StoreofItem");
        List<Product> p = Product.getProductsByFullName(itemName);
        int size = p.size();
        for(int i = 0; i < size; i++){
           if(storeName.equals(p.get(i).getStore())){
                prod = p.get(i);

           }
        }


        cards = findViewById(R.id.cards);
        CardView newCard = new CardView(ReportItem.this);
        getLayoutInflater().inflate(R.layout.card_base2, newCard);
        TextView item = newCard.findViewById(R.id.Item1);
        TextView price = newCard.findViewById(R.id.Price1);
        TextView value = newCard.findViewById(R.id.Value1);
        TextView weight = newCard.findViewById(R.id.Weight1);
        ImageView v = newCard.findViewById(R.id.itemImage1);


        String imageName = p.get(0).getFileNameWithoutExtension();
        int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
        v.setImageResource(resID);
        item.setText(itemName);
        price.setText(prod.getFormattedPrice());
        value.setText(prod.getFormattedValue());
        weight.setText(prod.getFormattedWeight());
        newCard.setTag(0);
        cards.addView(newCard);



    }
    public void onCancelClick(View view){
        finish();
    }
    public void onDoneClick(View view){
        finish();

    }
}