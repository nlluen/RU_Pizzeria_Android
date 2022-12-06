package com.example.ru_pizzeria_android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * this class is the Activity to be started when an item on the
 * RecyclerView was clicked
 * Get the name of the item from an intent extra. The text of the button is set to the item name.
 * @author Lily Chang
 */
public class ItemSelectedActivity extends AppCompatActivity {
    private Button btn_itemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_selected);
        btn_itemName = findViewById(R.id.btn1);
        Intent intent = getIntent();
        btn_itemName.setText(intent.getStringExtra("ITEM"));
    }
}