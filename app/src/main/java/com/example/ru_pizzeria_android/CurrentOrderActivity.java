package com.example.ru_pizzeria_android;

import static com.example.ru_pizzeria_android.MainActivity.orderNum;
import static com.example.ru_pizzeria_android.MainActivity.pizzaOrder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ru_pizzeria_android.src.Order;
import com.example.ru_pizzeria_android.src.Pizza;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CurrentOrderActivity extends AppCompatActivity {
    private TextView order_number,subTotal,tax,total;
    private ListView order_list;
    private Order order;
    private ArrayList<Pizza> pizzaList;
    private String[] pizzaOrders;
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
        pizzaOrders = order.getPizzaOrder().toArray(new String[0]);
        order_list = findViewById(R.id.order_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pizzaOrders);
        order_list.setAdapter(adapter);
        calc();
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

//    public void remove() {
//        if (orderList.getSelectionModel().getSelectedItem() != null) {
//            String result = orderList.getSelectionModel().getSelectedItem();
//            int index = pizzaOrders.indexOf(result);
//            pizzaOrders.remove(result);
//            orderList.setItems(pizzaOrders);
//            order.getPizzas().remove(index);
//        }
//
//
//        calc();
//    }
}