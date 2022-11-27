package com.example.ru_pizzeria_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button chicago_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chicago_btn = (Button) findViewById(R.id.chicago);
        chicago_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChicagoActivity();
            }
        });
    }

    public void openChicagoActivity() {
        Intent intent = new Intent(this, ChicagoActivity.class);
        startActivity(intent);
    }
}