package com.example.cosc341_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class FavouriteItem extends AppCompatActivity {
    ArrayList<String[]> lines = new ArrayList<String[]>();
    String searchTerm = "";
    String results = "";
    int counter=0;
    ArrayList<String> data = new ArrayList<String>();
    String line = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_item);

        TextView searchResults = findViewById(R.id.searchResults);
        EditText searchField = findViewById(R.id.searchField);

        try {
            FileInputStream fis = openFileInput("items.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                data.add(line);
                counter++;
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        if(counter>0)
            for(int i=0;i<counter;i++) lines.add(data.get(i).split(",", -1));

        final Button search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(searchField.getText().toString().length()>0){
                    searchTerm = searchField.getText().toString();
                    for(int i=0; i<lines.size(); i++){
                        if(lines.get(i)[3].contains(searchTerm)) results = results + lines.get(i)[3] + "\n";}
                    if(results=="") results = "No matches found";
                    searchResults.setText(results);}}});

        final Button clear = findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                searchTerm = "";
                searchField.setText("");
                searchResults.setText("Piece Tea");}});

        final Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();}});

    }
}