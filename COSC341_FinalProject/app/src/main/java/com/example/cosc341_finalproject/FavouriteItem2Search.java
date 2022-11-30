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

public class FavouriteItem2Search extends AppCompatActivity {


    LinearLayoutCompat cards;
    String fullname = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_item2search);

        EditText itemEditText = (EditText) findViewById(R.id.favouriteItemSearch);
        itemEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
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

    public void clearCards(){
        int count = cards.getChildCount();
        View v = null;
        for(int i=count-1; i>=0; i--) {
            v = cards.getChildAt(i);
            ((ViewGroup) v.getParent()).removeView(v);
        }
    }

    public void updateCards(){
        List<Product> prods = Product.getProductsByFullName(""); //should just show whatever by default
        if(fullname.length() > 1){
            prods = Product.getProductsByFullName(fullname,prods);
        }

        clearCards();

        int total = prods.size();
        cards = findViewById(R.id.cards);
        for (int i = 0; i < total; i++) {
            CardView newCard = new CardView(FavouriteItem2Search.this);
            getLayoutInflater().inflate(R.layout.card_base_fav, newCard);

            TextView itemName = newCard.findViewById(R.id.Item1);
            TextView weight = newCard.findViewById(R.id.Weight1);
            //TextView value = newCard.findViewById(R.id.Value1);
            //TextView price = newCard.findViewById(R.id.Price1);
            ImageView v = newCard.findViewById(R.id.itemImage1);
            Button addButt = newCard.findViewById(R.id.AddFavButton);

            Product p = prods.get(i);

            String imageName = p.getFileNameWithoutExtension();
            int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
            v.setImageResource(resID);

            itemName.setText(p.getFullName());
            weight.setText(p.getFormattedWeight());
            //value.setText(p.getFormattedValue());
            //price.setText(p.getFormattedPrice());
            addButt.setOnClickListener(new View.OnClickListener() {  //ON ADD FAVE BUTTON PRESS
                public void onClick(View v) {
                    String fname = itemName.getText().toString();
                    //find the favourite item and add the product to faveItems
                    int totalprods = Global.products.size();
                    for (int j = 0; j < totalprods; j++) {
                        Product p = Global.products.get(j);
                        if((p.getFullName().toLowerCase()).contains(fname.toLowerCase())){
                            //TODO: Eventually check so you can't add the same product to favourites twice
                            FaveProduct fp = new FaveProduct(fname, true, p.getWeight(), p.getFilename());
                            Global.faves.add(fp);
                            j = totalprods; //stop the loop to not add any item twice (in the case that the item is at multiple stores)
                        }
                    }

                    Intent intent = new Intent();
                    //intent.putExtra("editTextValue", "value_here"); for sending data back, though i just wanna cal the method to update the list of items
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });

            newCard.setTag(i);
            cards.addView(newCard);

        }
    }

/*
    public void onAddFav(View view) {
        String itemName = view.getParent().//.findViewById(R.id.Item1).toString();
        //view.getParent().

    }
*/
    public void onDone(View v){
        finish();
    }


}