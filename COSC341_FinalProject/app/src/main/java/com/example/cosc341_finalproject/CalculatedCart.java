package com.example.cosc341_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CalculatedCart extends AppCompatActivity {
    private int starter = 66; //ASCII code for `B`
    LinearLayoutCompat cards;
    Button buttonSwap;
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculated_cart);

        buttonSwap = findViewById(R.id.SwapButton1);

        cards = findViewById(R.id.cards);

        buttonSwap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findBlockAndDoSomething("Foodie White Rice");
               // doSwap(view.getTag());
            }

        });
        total = Global.items.size();
        for (int i = 0; i < total; i++) {
            CardView newCard = new CardView(CalculatedCart.this);
            getLayoutInflater().inflate(R.layout.card_base, newCard);

            TextView itemName = newCard.findViewById(R.id.Item1);
            TextView weight = newCard.findViewById(R.id.Weight1);
            TextView value = newCard.findViewById(R.id.Value1);
            TextView price = newCard.findViewById(R.id.Price1);
            ImageView v = newCard.findViewById(R.id.itemImage1);

            Product p = Global.products.get(i);

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



    private void doSwap(Object tag)
    {
            System.out.println(tag);
                Intent intent = new Intent(this, ReplaceItem.class);
                startActivity(intent);

        }
    private void findBlockAndDoSomething(String name)
    {
        Log.d("MyTAG", "CLICK");

        for (int i = 1; i < cards.getChildCount()-1; i++)
        {
            CardView selected = (CardView) cards.getChildAt(i);
            System.out.println(selected.getTag());
            System.out.println(selected.getTag().toString());
            System.out.println(selected.getTag().toString().equals(name));
            if (selected.getTag() != null && selected.getTag().toString().equals(name))
            {
                // do something. E.g change block name
                TextView textViewClassesBlock1 = selected.findViewById(R.id.Item1);
                textViewClassesBlock1.setText("Block XXX");
                return;
            }
        }
    }
}








