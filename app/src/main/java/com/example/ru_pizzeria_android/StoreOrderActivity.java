package com.example.ru_pizzeria_android;

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
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ru_pizzeria_android.src.Order;
import com.example.ru_pizzeria_android.src.Pizza;
import com.example.ru_pizzeria_android.src.StoreOrder;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class StoreOrderActivity extends AppCompatActivity {
    private TextView storeOrderTotal;
    private Spinner order_spinner;
    private ListView storeOrderList;
    private Button cancel_order;
    private double TAX = 0.06625;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_order);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        order_spinner = findViewById(R.id.order_spinner);
        storeOrderTotal = findViewById(R.id.storeOrderTotal);
        storeOrderList = findViewById(R.id.storeOrderList);
        StoreOrder storeOrder = totalOrders;
        ArrayList<Integer> storeOrderNums = storeOrder.listNums();
        ArrayAdapter<Integer> orderNumAdapter = new ArrayAdapter<Integer>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, storeOrderNums);
        order_spinner.setAdapter(orderNumAdapter);
        if(!storeOrderNums.isEmpty()){
            Order currOrder = storeOrder.getFirstOrder();
            double price = currOrder.calcTotal();
            price = price + price * TAX;
            DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
            storeOrderTotal.setText(String.valueOf(decimalFormat.format(price)));
            ArrayAdapter<String> pizzasAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,currOrder.getPizzaOrder());
            storeOrderList.setAdapter(pizzasAdapter);
        }

        order_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                orderNumClick();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cancel_order = findViewById(R.id.cancel_order);
        cancel_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelOrder();
            }
        });
    }

    void orderNumClick(){
        Integer selected = (Integer) order_spinner.getSelectedItem();
        storeOrderTotal = findViewById(R.id.storeOrderTotal);
        if(selected != null){
            Order currOrder = totalOrders.getOrder(selected);
            double orderPrice = currOrder.calcTotal();
            orderPrice = orderPrice + orderPrice * TAX;
            DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
            storeOrderTotal.setText(String.valueOf(decimalFormat.format(orderPrice)));
            ArrayAdapter<String> pizzasAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,currOrder.getPizzaOrder());
            storeOrderList.setAdapter(pizzasAdapter);

        }
    }

    public void cancelOrder(){
        Integer selected = Integer.valueOf(order_spinner.getSelectedItem().toString());
        Order chosenOrder = totalOrders.getOrder(selected);

        totalOrders.remove(chosenOrder);
        storeOrderList.setAdapter(null);
        storeOrderTotal.setText("");
        ArrayList<Integer> storeOrderNums = totalOrders.listNums();
        ArrayAdapter<Integer> numsAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, storeOrderNums);
        order_spinner.setAdapter(numsAdapter);
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


}