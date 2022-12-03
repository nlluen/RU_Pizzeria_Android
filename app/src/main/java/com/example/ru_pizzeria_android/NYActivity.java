package com.example.ru_pizzeria_android;

import static com.example.ru_pizzeria_android.MainActivity.pizzaOrder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import java.util.Arrays;
import java.util.Locale;

public class NYActivity extends AppCompatActivity {
    private Button addTop,removeTop, order;
    private Spinner flavSpinner, sizeSpinner, selectToppings;
    private String [] flavors = {"Deluxe", "BBQ Chicken", "Meatzza", "Build Your Own"};
    private String [] sizes = {"small","medium","large"};
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> topAdapter;
    private ArrayAdapter<String> sizeAdapter;
    private TextView crust_type, pizza_price;
    public static Pizza nyPizza;
    private String flavor, size;
    private int index;
    private ArrayList<String> byoToppings = new ArrayList<>();
    private ArrayList<String> allToppings = new ArrayList<String>(Arrays.asList("sausage", "bbq chicken", "beef", "ham", "pepperoni", "green pepper",
            "onion", "mushroom", "provolone", "cheddar","olive","pineapple","bacon")) ;
    private String[] Toppings = new String[]{};
    public static ListView selected_toppings;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicago);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        flavListener();

        selectToppings = findViewById(R.id.select_toppings);
        topAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, allToppings);
        selectToppings.setAdapter(topAdapter);
        selected_toppings = findViewById(R.id.selected_toppings);
        selected_toppings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
            }
        });
        sizeListener();
        buttons();
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

    public void addOrder(){
        pizzaOrder.add(size);
        Toast.makeText(getApplicationContext(),"Added to Order",Toast.LENGTH_LONG).show();
    }

    public void startSpinnerPos(){
        flavSpinner = findViewById(R.id.pizza_flavor);
        Bundle extras = getIntent().getExtras();
        String flav = extras.getString("ITEM");
        System.out.println(flav);
        if(flav.equalsIgnoreCase("deluxe")){
            flavSpinner.setSelection(0);
        }else if(flav.equalsIgnoreCase("bbq")){
            flavSpinner.setSelection(1);
        }else if(flav.equalsIgnoreCase("meatzza")){
            flavSpinner.setSelection(2);
        }else if(flav.equalsIgnoreCase("byo")){
            flavSpinner.setSelection(3);
        }
    }

    public void flavListener(){
        flavSpinner = findViewById(R.id.pizza_flavor);
        adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, flavors);
        flavSpinner.setAdapter(adapter);
        startSpinnerPos();
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
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    public void sizeListener(){
        sizeSpinner = findViewById(R.id.size);
        sizeAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,sizes);
        sizeSpinner.setAdapter(sizeAdapter);
        size = "large";
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                size = sizeSpinner.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(), size, Toast.LENGTH_LONG).show();
                nyPizza.setSize(size.toLowerCase(Locale.ROOT));
                DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
                Toast.makeText(getApplicationContext(),decimalFormat.format(nyPizza.price()) , Toast.LENGTH_LONG).show();
                pizza_price = (TextView) findViewById(R.id.pizza_price);
                pizza_price.setText(String.valueOf(decimalFormat.format(nyPizza.price())));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    public void buttons(){
        order = findViewById(R.id.order);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOrder();
            }
        });
        addTop = findViewById(R.id.addTop);
        addTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTopping();
            }
        });
        removeTop = findViewById(R.id.removeTop);
        removeTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeTopping();
            }
        });
    }

    public void addTopping(){
        if(byoToppings.size() < 7) {
            String topping = selectToppings.getSelectedItem().toString();
            allToppings.remove(topping);
            byoToppings.add(topping);
            selected_toppings = findViewById(R.id.selected_toppings);
            ArrayAdapter<String> Adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, byoToppings);
            topAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, allToppings);
            selected_toppings.setAdapter(Adapter);
            selectToppings.setAdapter(topAdapter);
            nyPizza.add(topping.replaceAll("\\s", "_"));
            DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
            pizza_price = (TextView) findViewById(R.id.pizza_price);
            pizza_price.setText(String.valueOf(decimalFormat.format(nyPizza.price())));
        }else{
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("TOO MANY TOPPINGS!");
            alert.setMessage("You are not able to add more than 7 toppings");
            AlertDialog dialog = alert.create();
            dialog.show();
        }
    }

    public void removeTopping(){
        selected_toppings = findViewById(R.id.selected_toppings);
        allToppings.add(byoToppings.get(index));
        nyPizza.remove(byoToppings.get(index).replaceAll("\\s", "_"));
        byoToppings.remove(index);
        ArrayAdapter<String> Adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, byoToppings);
        topAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, allToppings);
        selected_toppings.setAdapter(Adapter);
        selectToppings.setAdapter(topAdapter);
        DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
        pizza_price = (TextView) findViewById(R.id.pizza_price);
        pizza_price.setText(String.valueOf(decimalFormat.format(nyPizza.price())));
    }

    void selectDeluxe() {
        selectToppings.setEnabled(false);
        addTop.setEnabled(false);
        removeTop.setEnabled(false);
        String[] Toppings = {"sausage", "pepperoni", "green pepper", "onion", "mushroom"};
        selected_toppings = findViewById(R.id.selected_toppings);
        ArrayAdapter<String> Adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Toppings);
        selected_toppings.setAdapter(Adapter);
        PizzaFactory pizzaFactory = new NYPizza();
        nyPizza = pizzaFactory.createDeluxe();
        nyPizza.setCrust("(New York Style - Brooklyn)");
        DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
        pizza_price = (TextView) findViewById(R.id.pizza_price);
        pizza_price.setText(String.valueOf(decimalFormat.format(nyPizza.price())));
        crust_type = (TextView) findViewById(R.id.crust_type);
        crust_type.setText("Brooklyn");
    }

    void selectBBQChicken() {
        selectToppings.setEnabled(false);
        addTop.setEnabled(false);
        removeTop.setEnabled(false);
        String[] Toppings = {"bbq chicken", "green pepper", "provolone", "cheddar"};
        selected_toppings = findViewById(R.id.selected_toppings);
        ArrayAdapter<String> Adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Toppings);
        selected_toppings.setAdapter(Adapter);
        PizzaFactory pizzaFactory = new NYPizza();
        nyPizza = pizzaFactory.createBBQChicken();
        nyPizza.setCrust("(New York Style - Thin)");
        DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
        pizza_price = (TextView) findViewById(R.id.pizza_price);
        pizza_price.setText(String.valueOf(decimalFormat.format(nyPizza.price())));
        crust_type = (TextView) findViewById(R.id.crust_type);
        crust_type.setText("Thin");
    }

    void selectMeatzza() {
        selectToppings.setEnabled(false);
        addTop.setEnabled(false);
        removeTop.setEnabled(false);
        String[] Toppings = {"sausage", "pepperoni", "beef", "ham"};
        selected_toppings = findViewById(R.id.selected_toppings);
        ArrayAdapter<String> Adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Toppings);
        selected_toppings.setAdapter(Adapter);
        PizzaFactory pizzaFactory = new NYPizza();
        nyPizza = pizzaFactory.createMeatzza();
        nyPizza.setCrust("(New York Style - Hand Tossed)");
        DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
        pizza_price = (TextView) findViewById(R.id.pizza_price);
        pizza_price.setText(String.valueOf(decimalFormat.format(nyPizza.price())));
        crust_type = (TextView) findViewById(R.id.crust_type);
        crust_type.setText("Hand Tossed");
    }

    void selectBuildYourOwn() {
        selectToppings.setEnabled(true);
        addTop.setEnabled(true);
        removeTop.setEnabled(true);
        selected_toppings = findViewById(R.id.selected_toppings);
        ArrayAdapter<String> Adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, byoToppings);
        selected_toppings.setAdapter(Adapter);
        PizzaFactory pizzaFactory = new NYPizza();
        nyPizza = pizzaFactory.createBuildYourOwn();
        nyPizza.setCrust("(New York Style - Hand Tossed)");
        DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
        pizza_price = (TextView) findViewById(R.id.pizza_price);
        pizza_price.setText(String.valueOf(decimalFormat.format(nyPizza.price())));
        crust_type = (TextView) findViewById(R.id.crust_type);
        crust_type.setText("Hand Tossed");
    }
}
