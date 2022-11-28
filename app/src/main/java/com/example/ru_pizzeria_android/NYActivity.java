package com.example.ru_pizzeria_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NYActivity extends AppCompatActivity {
    private Button mm_btn, topping_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ny);
        mm_btn = (Button) findViewById(R.id.mm_button);
        mm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });

        topping_btn = (Button) findViewById(R.id.toppings);
        topping_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openToppingsActivity();
            }
        });
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openToppingsActivity() {
        Intent intent = new Intent(this, ToppingsActivity.class);
        startActivity(intent);
    }
}
