package com.example.ru_pizzeria_android;

import static com.example.ru_pizzeria_android.MainActivity.pizzaOrder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ru_pizzeria_android.src.NYPizza;
import com.example.ru_pizzeria_android.src.Pizza;
import com.example.ru_pizzeria_android.src.PizzaFactory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

public class NYActivity extends AppCompatActivity {
    private Button topping_btn, order;
    private Spinner flavSpinner, sizeSpinner;
    private String [] flavors = {"Deluxe", "BBQ Chicken", "Meatzza", "Build Your Own"};
    private String [] sizes = {"small","medium","large"};
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> sizeAdapter;
    private TextView crust_type, pizza_price;
    public static Pizza chicPizza;
    private String flavor, size;
    public static ArrayList<String> byoToppings = new ArrayList<>();
    public static ListView selected_toppings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ny);
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
                chicPizza.setSize(size.toLowerCase(Locale.ROOT));
                DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
                Toast.makeText(getApplicationContext(),decimalFormat.format(chicPizza.price()) , Toast.LENGTH_LONG).show();
                pizza_price = (TextView) findViewById(R.id.pizza_price);
                pizza_price.setText(String.valueOf(decimalFormat.format(chicPizza.price())));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        order = findViewById(R.id.order);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOrder();
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
        intent.putExtra("PizzaType","chicago");
        startActivity(intent);
    }

    public void addOrder(){
        pizzaOrder.add(chicPizza);
        Toast.makeText(getApplicationContext(),"Added to Order",Toast.LENGTH_LONG).show();
    }

    void selectDeluxe() {
        String[] Toppings = {"sausage", "pepperoni", "green pepper", "onion", "mushroom"};
        selected_toppings = findViewById(R.id.selected_toppings);
        ArrayAdapter<String> Adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Toppings);
        selected_toppings.setAdapter(Adapter);
        PizzaFactory pizzaFactory = new NYPizza();
        chicPizza = pizzaFactory.createDeluxe();
        chicPizza.setCrust("(New York Style - Brooklyn)");
        DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
        pizza_price = (TextView) findViewById(R.id.pizza_price);
        pizza_price.setText(String.valueOf(decimalFormat.format(chicPizza.price())));
        crust_type = (TextView) findViewById(R.id.crust_type);
        crust_type.setText("Brooklyn");
    }

    void selectBBQChicken() {
        String[] Toppings = {"bbq chicken", "green pepper", "provolone", "cheddar"};
        selected_toppings = findViewById(R.id.selected_toppings);
        ArrayAdapter<String> Adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Toppings);
        selected_toppings.setAdapter(Adapter);
        PizzaFactory pizzaFactory = new NYPizza();
        chicPizza = pizzaFactory.createBBQChicken();
        chicPizza.setCrust("(New York Style - Thin)");
        DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
        pizza_price = (TextView) findViewById(R.id.pizza_price);
        pizza_price.setText(String.valueOf(decimalFormat.format(chicPizza.price())));
        crust_type = (TextView) findViewById(R.id.crust_type);
        crust_type.setText("Thin");
    }

    void selectMeatzza() {
        String[] Toppings = {"sausage", "pepperoni", "beef", "ham"};
        selected_toppings = findViewById(R.id.selected_toppings);
        ArrayAdapter<String> Adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Toppings);
        selected_toppings.setAdapter(Adapter);
        PizzaFactory pizzaFactory = new NYPizza();
        chicPizza = pizzaFactory.createMeatzza();
        chicPizza.setCrust("(New York Style - Hand Tossed)");
        DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
        pizza_price = (TextView) findViewById(R.id.pizza_price);
        pizza_price.setText(String.valueOf(decimalFormat.format(chicPizza.price())));
        crust_type = (TextView) findViewById(R.id.crust_type);
        crust_type.setText("Hand Tossed");
    }

    void selectBuildYourOwn() {
        selected_toppings = findViewById(R.id.selected_toppings);
        ArrayAdapter<String> Adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, byoToppings);
        selected_toppings.setAdapter(Adapter);
        PizzaFactory pizzaFactory = new NYPizza();
        chicPizza = pizzaFactory.createBuildYourOwn();
        chicPizza.setCrust("(New York Style - Hand Tossed)");
        DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
        pizza_price = (TextView) findViewById(R.id.pizza_price);
        pizza_price.setText(String.valueOf(decimalFormat.format(chicPizza.price())));
        crust_type = (TextView) findViewById(R.id.crust_type);
        crust_type.setText("Hand Tossed");
    }
}
