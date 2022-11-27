package com.example.ru_pizzeria_android;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Item> items = new ArrayList<>();
    private int [] itemImages = {R.drawable.deepdish,R.drawable.ny_style,R.drawable.cart,R.drawable.pizzeria};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rcview = findViewById(R.id.rcView_menu);
        setupMenuItems(); //add the list of items to the ArrayList
        ItemsAdapter adapter = new ItemsAdapter(this, items); //create the adapter
        rcview.setAdapter(adapter); //bind the list of items to the RecyclerView
        //use the LinearLayout for the RecyclerView
        rcview.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupMenuItems() {
        String [] itemNames = getResources().getStringArray(R.array.itemNames);
        for (int i = 0; i < itemNames.length; i++) {
            items.add(new Item(itemNames[i], itemImages[i], "$1.39"));
        }
    }
}