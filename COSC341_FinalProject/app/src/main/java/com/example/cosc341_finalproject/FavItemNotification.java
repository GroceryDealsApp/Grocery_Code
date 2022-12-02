package com.example.cosc341_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.view.View;
import android.widget.CheckBox;

public class FavItemNotification extends AppCompatActivity {
  //  boolean checked1;
  //  boolean checked2;
 //   boolean checked3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_item_notification);
        CheckBox priceDrops = (CheckBox) findViewById(R.id.priceDrops);
        CheckBox extraPoints = (CheckBox) findViewById(R.id.extraPoints);
        CheckBox sale = (CheckBox) findViewById(R.id.sale);
        priceDrops.setChecked(Global.check1);
        extraPoints.setChecked(Global.check2);
        sale.setChecked(Global.check3);
        Button done = findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void onFirstClicked (View view) {
        Global.check1 = !Global.check1;



    }
    public void onSecondClicked(View view){
        Global.check2 = !Global.check2;


    }
    public void onThirdClicked(View view){
        Global.check3 = !Global.check3;

    }
}



