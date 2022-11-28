package com.example.ru_pizzeria_android;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ru_pizzeria_android.src.ChicagoPizza;
import com.example.ru_pizzeria_android.src.Pizza;
import com.example.ru_pizzeria_android.src.PizzaFactory;

public class ChicagoActivity extends AppCompatActivity {
    private Button mm_btn, topping_btn;
    private Spinner flavSpinner, sizeSpinner;
    private String [] flavors = {"Deluxe", "BBQ Chicken", "Meatzza", "Build Your Own"};
    private String [] sizes = {"Small","Medium","Large"};
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> sizeAdapter;
    private TextView crust_type;
    private Pizza pizza;
    private String flavor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicago);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        flavSpinner = findViewById(R.id.pizza_flavor);
        adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, flavors);
        flavSpinner.setAdapter(adapter);
        sizeSpinner = findViewById(R.id.size);
        sizeAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,sizes);
        sizeSpinner.setAdapter(sizeAdapter);
        flavSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder alert = new AlertDialog.Builder(ChicagoActivity.this);
                alert.setTitle("Alert !");
                AlertDialog dialog = alert.create();
                dialog.show();
                flavor = flavSpinner.getSelectedItem().toString();
            }
        });

        topping_btn = (Button) findViewById(R.id.toppings);
        topping_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openToppingsActivity();
            }
        });

        if (flavor.equalsIgnoreCase("Deluxe")) {
            selectDeluxe();
        } else if (flavor.equalsIgnoreCase("BBQ Chicken")) {
            System.out.println("bbq");
            selectBBQChicken();
        } else if (flavor.equalsIgnoreCase("Meatzza")) {
            System.out.println("meatzzas");
            selectMeatzza();
        }  else if (flavor.equalsIgnoreCase("Build Your Own")) {
            selectBuildYourOwn();
        }


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

    void selectDeluxe() {
        PizzaFactory pizzaFactory = new ChicagoPizza();
        pizza = pizzaFactory.createDeluxe();
        pizza.setCrust("(Chicago Style - Deep Dish");
        crust_type = (TextView) findViewById(R.id.crust_type);
        crust_type.setText("Deep Dish");
    }

    void selectBBQChicken() {
        PizzaFactory pizzaFactory = new ChicagoPizza();
        pizza = pizzaFactory.createBBQChicken();
        pizza.setCrust("(Chicago Style - Pan");
        crust_type = (TextView) findViewById(R.id.crust_type);
        crust_type.setText("Pan");
    }

    void selectMeatzza() {
        PizzaFactory pizzaFactory = new ChicagoPizza();
        pizza = pizzaFactory.createMeatzza();
        pizza.setCrust("(Chicago Style - Stuffed");
        crust_type = (TextView) findViewById(R.id.crust_type);
        crust_type.setText("Stuffed");
    }

    void selectBuildYourOwn() {
        PizzaFactory pizzaFactory = new ChicagoPizza();
        pizza = pizzaFactory.createBuildYourOwn();
        pizza.setCrust("(Chicago Style - Pan");
        crust_type = (TextView) findViewById(R.id.crust_type);
        crust_type.setText("Pan");
    }
}