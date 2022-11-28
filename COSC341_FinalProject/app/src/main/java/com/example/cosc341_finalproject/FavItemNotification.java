package com.example.cosc341_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class FavItemNotification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_item_notification);

        final Button done = findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){
                finish();}});

    }
}