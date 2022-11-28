package com.example.cosc341_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class StoreComparison extends AppCompatActivity {
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
}

