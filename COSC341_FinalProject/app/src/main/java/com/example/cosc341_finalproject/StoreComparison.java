package com.example.cosc341_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class StoreComparison extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static Integer[] imageIconDatabase = {R.drawable.walk, R.drawable.bike, R.drawable.bus, R.drawable.car};
    private String[] imageNameDatabase = {"walk", "bike", "bus", "car"};



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
    public void onNext1Click(View view){

    }
    public void onNext2Click(View view){

    }
    public void onNext3Click(View view){

    }
    public void onNext4Click(View view){

    }


}

