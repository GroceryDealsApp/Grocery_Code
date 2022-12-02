package com.example.cosc341_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StoreComparison extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static Integer[] imageIconDatabase = {R.drawable.walk, R.drawable.bike, R.drawable.bus, R.drawable.car};
    //private String[] imageNameDatabase = {"walk", "bike", "bus", "car"};
    String storeNames[];
    String storename;
    LinearLayoutCompat cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_comparison);
        Spinner ImageSpinner = (Spinner) findViewById(R.id.ImageSpinner);
        SimpleImageArrayAdapter adapter = new SimpleImageArrayAdapter(this, imageIconDatabase);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ImageSpinner.setAdapter(adapter);
        Spinner SortSpinner = (Spinner) findViewById(R.id.SortSpinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.sort_array, android.R.layout.simple_spinner_item);
        SortSpinner.setAdapter(adapter2);
        SortSpinner.setOnItemSelectedListener( this);

        //initialize carts
        Global.carts = new ArrayList<>();
        for (Store store : Global.stores) {
            storeCart storecart = new storeCart(store.getStorename());
            Global.carts.add(storecart);
        }

        //for fixing bug
       // TextView trantext = findViewById(R.id.Transport);
       // String test = "";
        /*for (storeCart storecart : Global.carts) {
            test += storecart.getCartItems().get(0).getFullName() + "\n";
        }*/
        /*for (Store store : Global.stores) {
        test += store.getStorename() + "\n";
        }
        trantext.setText(test); */

        cards = findViewById(R.id.cards);
        updateStoreCards();

    }

    public void clearStoreCards(){
        int count = cards.getChildCount();
        View v = null;
        for(int i=count-1; i>=0; i--) {
            v = cards.getChildAt(i);
            ((ViewGroup) v.getParent()).removeView(v);
        }
    }
    public void updateStoreCards(){
        //TextView tvf = findViewById(R.id.textViewFav);
        //String test = "";
        //List<FaveProduct> faveprods = new ArrayList<>(); //should just show nothing by default
        //faveprods = Product.getProductsByBrand(brand,faveprods);
        // For Loop for iterating all favourite items
        //int numIt = Global.faves.size();
        //for (int i = 0; i < numIt; i++) {
        //    FaveProduct p = new FaveProduct();
        //}

        //tvf.setText(test);

        clearStoreCards();

        int total = Global.carts.size();
        cards = findViewById(R.id.cards);
        for (int i = 0; i < total; i++) {
            //test = test + "\n" + i;
            //tvf.setText(test);
            storeCart storecart = Global.carts.get(i);
            if (storecart.isCartAbleToBeFilled()) {
                //storecart.listItems();
                CardView newCard = new CardView(StoreComparison.this);
                getLayoutInflater().inflate(R.layout.card_base_storecomparison, newCard);

                TextView storeNameText = newCard.findViewById(R.id.StoreNameText);
                TextView distanceEtaText = newCard.findViewById(R.id.distanceEtaText);
                TextView totalCostText = newCard.findViewById(R.id.totalCostText);
                TextView totalValueText = newCard.findViewById(R.id.totalValue);
                ImageButton ButtCardDetails = newCard.findViewById(R.id.ButtonCardDetailsArrow);
                Button mapButt = newCard.findViewById(R.id.buttonMapStore);


                storeNameText.setText(storecart.getStore());
                //TODO: set distance eta text to an actual value from google maps
                totalCostText.setText(storecart.getTotalcostFormatted());
                totalValueText.setText(storecart.getTotalvalueFormatted());


                mapButt.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        //TODO: open map of route to store from campus
                    }
                });

                ButtCardDetails.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), CalculatedCart.class);
                        intent.putExtra("store", storecart.getStore());
                        startActivityForResult(intent,4);
                    }
                });
                newCard.setTag(i);
                cards.addView(newCard);
            }
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 4
        if (requestCode == 4) {
            if(resultCode == RESULT_OK) {
                updateStoreCards();
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public class SimpleImageArrayAdapter extends ArrayAdapter<Integer> {
        private Integer[] images;

        public SimpleImageArrayAdapter(Context context, Integer[] images) {
            super(context, R.layout.activity_store_comparison, images);
            this.images = images;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getImageForPosition(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getImageForPosition(position);
        }

        private View getImageForPosition(int position) {
            ImageView imageView = new ImageView(getContext());
            imageView.setBackgroundResource(images[position]);
            imageView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return imageView;
        }

    }
    public void onHelpButton(View view){

    }
    public void onBackCompClick(View view){
        finish();

    }
    public void onMap1Click(View view){

    }
    public void onMap2Click(View view){

    }
    public void onMap3Click(View view){

    }
    public void onMap4Click(View view){

    }
    public void OnDoneClick(View view){
        //TODO: should go back to homescreen with the usercart put in the recently made carts area
        Intent intent = new Intent(this, ActivityHome.class);
        startActivity(intent);

    }
    public void onReplaceClick(View view){
        Intent intent = new Intent(this, ReplaceItem.class);
        startActivity(intent);
    }


}

