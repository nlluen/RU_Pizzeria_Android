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
import android.widget.Toast;

import com.example.ru_pizzeria_android.src.ChicagoPizza;
import com.example.ru_pizzeria_android.src.Pizza;
import com.example.ru_pizzeria_android.src.PizzaFactory;

import java.text.DecimalFormat;
import java.util.Locale;

public class ChicagoActivity extends AppCompatActivity {
    private Button mm_btn, topping_btn;
    private Spinner flavSpinner, sizeSpinner;
    private String [] flavors = {"Deluxe", "BBQ Chicken", "Meatzza", "Build Your Own"};
    private String [] sizes = {"small","medium","large"};
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> sizeAdapter;
    private TextView crust_type, pizza_price;
    private Pizza pizza;
    private String flavor, size;

    public Pizza getChicPizza(){
        return pizza;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicago);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        flavSpinner = findViewById(R.id.pizza_flavor);
        adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, flavors);
        flavSpinner.setAdapter(adapter);
        flavor = "Deluxe";

        flavSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                flavor = flavSpinner.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(), flavor, Toast.LENGTH_LONG).show();
                if (flavor.equalsIgnoreCase("Deluxe")) {
                    selectDeluxe();
                } else if (flavor.equalsIgnoreCase("BBQ Chicken")) {
                    selectBBQChicken();
                } else if (flavor.equalsIgnoreCase("Meatzza")) {
                    selectMeatzza();
                }  else if (flavor.equalsIgnoreCase("Build Your Own")) {
                    selectBuildYourOwn();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sizeSpinner = findViewById(R.id.size);
        sizeAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,sizes);
        sizeSpinner.setAdapter(sizeAdapter);
        //size = "large";

        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                size = sizeSpinner.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(), size, Toast.LENGTH_LONG).show();
                pizza.setSize(size.toLowerCase(Locale.ROOT));
                DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
                Toast.makeText(getApplicationContext(),decimalFormat.format(pizza.price()) , Toast.LENGTH_LONG).show();
                pizza_price = (TextView) findViewById(R.id.pizza_price);
                pizza_price.setText(String.valueOf(decimalFormat.format(pizza.price())));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
        DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
        pizza_price = (TextView) findViewById(R.id.pizza_price);
        pizza_price.setText(String.valueOf(decimalFormat.format(pizza.price())));
        crust_type = (TextView) findViewById(R.id.crust_type);
        crust_type.setText("Deep Dish");
    }

    void selectBBQChicken() {
        PizzaFactory pizzaFactory = new ChicagoPizza();
        pizza = pizzaFactory.createBBQChicken();
        pizza.setCrust("(Chicago Style - Pan");
        DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
        pizza_price = (TextView) findViewById(R.id.pizza_price);
        pizza_price.setText(String.valueOf(decimalFormat.format(pizza.price())));
        crust_type = (TextView) findViewById(R.id.crust_type);
        crust_type.setText("Pan");
    }

    void selectMeatzza() {
        PizzaFactory pizzaFactory = new ChicagoPizza();
        pizza = pizzaFactory.createMeatzza();
        pizza.setCrust("(Chicago Style - Stuffed");
        DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
        pizza_price = (TextView) findViewById(R.id.pizza_price);
        pizza_price.setText(String.valueOf(decimalFormat.format(pizza.price())));
        crust_type = (TextView) findViewById(R.id.crust_type);
        crust_type.setText("Stuffed");
    }

    void selectBuildYourOwn() {
        PizzaFactory pizzaFactory = new ChicagoPizza();
        pizza = pizzaFactory.createBuildYourOwn();
        pizza.setCrust("(Chicago Style - Pan");
        DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
        pizza_price = (TextView) findViewById(R.id.pizza_price);
        pizza_price.setText(String.valueOf(decimalFormat.format(pizza.price())));
        crust_type = (TextView) findViewById(R.id.crust_type);
        crust_type.setText("Pan");
    }
}