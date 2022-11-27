package com.example.ru_pizzeria_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button chicago_btn;
    private Button ny_btn;
    private Button store_order_btn;
    private Button order_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chicago_btn = (Button) findViewById(R.id.chicago);
        ny_btn = (Button) findViewById(R.id.new_york);
        store_order_btn = (Button) findViewById(R.id.store_order);
        order_btn = (Button) findViewById(R.id.current_order);

        chicago_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChicagoActivity();
            }
        });

        ny_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNYActivity();
            }
        });

        store_order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStoreOrderActivity();
            }
        });

        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCurrentOrderActivity();
            }
        });
    }

    public void openChicagoActivity() {
        Intent intent = new Intent(this, ChicagoActivity.class);
        startActivity(intent);
    }

    public void openNYActivity() {
        Intent intent = new Intent(this, NYActivity.class);
        startActivity(intent);
    }
    public void openStoreOrderActivity() {
        Intent intent = new Intent(this, StoreOrderActivity.class);
        startActivity(intent);
    }
    public void openCurrentOrderActivity() {
        Intent intent = new Intent(this, CurrentOrderActivity.class);
        startActivity(intent);
    }
}