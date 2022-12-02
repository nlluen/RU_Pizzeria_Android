package com.example.ru_pizzeria_android;

import static com.example.ru_pizzeria_android.MainActivity.orderNum;
import static com.example.ru_pizzeria_android.MainActivity.pizzaOrder;
import static com.example.ru_pizzeria_android.MainActivity.totalOrders;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ru_pizzeria_android.src.Order;
import com.example.ru_pizzeria_android.src.Pizza;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CurrentOrderActivity extends AppCompatActivity {
    private TextView order_number,subTotal,tax,total;
    private ListView order_list;
    private Button remove_pizza, place_order;
    private Order order;
    private ArrayList<Pizza> pizzaList;
    private List<String> pizzaOrders;
    private int index;
    private double TAX = 0.06625;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        order_number = (TextView) findViewById(R.id.orderNumber);

        int ordernum = getIntent().getExtras().getInt("OrderNUM");
        order_number.setText(String.valueOf(ordernum));
        order = pizzaOrder;
        pizzaList = order.getPizzas();
        pizzaOrders = order.getPizzaOrder();
        order_list = findViewById(R.id.order_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pizzaOrders);
        order_list.setAdapter(adapter);
        order_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
            }
        });
        calc();

        remove_pizza = findViewById(R.id.remove_pizza);
        remove_pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove();
            }
        });

        place_order = findViewById(R.id.place_order);
        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();
            }
        });
    }

    public void calc() {
        subTotal = findViewById(R.id.subTotal);
        tax = findViewById(R.id.tax);
        total = findViewById(R.id.total);
        DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
        double sub = order.calcTotal();
        subTotal.setText(String.valueOf(decimalFormat.format(sub)));
        double salesTax = sub * TAX;
        tax.setText(String.valueOf(decimalFormat.format(salesTax)));
        double TOTAL = sub + salesTax;
        total.setText(String.valueOf(decimalFormat.format(TOTAL)));
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

    public void remove() {
        order_list = findViewById(R.id.order_list);
        pizzaOrders.remove(pizzaOrders.get(index));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pizzaOrders);
        order_list.setAdapter(adapter);
        order.getPizzas().remove(index);
        calc();
    }

    public void placeOrder() {
        if (pizzaOrders != null) {
            totalOrders.add(order);
            orderNum += 1;
            pizzaOrder = new Order(orderNum);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext()," Your order has been placed!",Toast.LENGTH_LONG).show();
        }
    }
}