package com.example.ru_pizzeria_android;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class NYActivity extends AppCompatActivity {
    private Button mm_btn, topping_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ny);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        topping_btn = (Button) findViewById(R.id.toppings);
        topping_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openToppingsActivity();
            }
        });
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


    public void openToppingsActivity() {
        Intent intent = new Intent(this, ToppingsActivity.class);
        startActivity(intent);
    }
}
