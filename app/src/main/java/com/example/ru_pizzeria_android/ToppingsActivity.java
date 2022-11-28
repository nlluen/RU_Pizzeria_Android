package com.example.ru_pizzeria_android;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class ToppingsActivity extends AppCompatActivity {
    private ArrayList<Item> items = new ArrayList<>();
    private int [] itemImages = {R.drawable.sausage, R.drawable.bbq_chicken, R.drawable.beef,
            R.drawable.ham, R.drawable.pepperoni, R.drawable.green_pepper, R.drawable.onion,
            R.drawable.mushroom,R.drawable.provolone,R.drawable.cheddar,R.drawable.olive,R.drawable.pineapple,R.drawable.bacon};

    /**
     * Get the references of all instances of Views defined in the layout file, set up the list of
     * items to be display in the RecyclerView.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toppings);
        RecyclerView rcview = findViewById(R.id.rcView_menu);
        setupMenuItems();
        ItemsAdapter adapter = new ItemsAdapter(this, items);
        rcview.setAdapter(adapter);
        rcview.setLayoutManager(new LinearLayoutManager(this));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Helper method to set up the data (the Model of the MVC).
     */
    private void setupMenuItems() {
        String [] itemNames = getResources().getStringArray(R.array.itemNames);
        for (int i = 0; i < itemNames.length; i++) {
            items.add(new Item(itemNames[i], itemImages[i], "$1.59"));
        }
    }
}