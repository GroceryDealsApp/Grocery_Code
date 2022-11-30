package com.example.cosc341_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);
    }
    public void onDone(View v){
        finish();
    }
    public void onFavouriteItems(View v){
        Intent intent = new Intent(this, FavouriteItem.class);
        startActivity(intent);
    }
    public void onBlacklistedItems(View v){
        Button b = findViewById(R.id.buttonBlacklistedItems);
        b.setText("Not implemented yet");
    }
    public void onNotificationSettings(View v){
        Intent intent = new Intent(this, FavItemNotification.class);
        startActivity(intent);
    }

}