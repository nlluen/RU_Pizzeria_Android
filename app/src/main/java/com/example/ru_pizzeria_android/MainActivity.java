package com.example.ru_pizzeria_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ru_pizzeria_android.src.Order;
import com.example.ru_pizzeria_android.src.Pizza;
import com.example.ru_pizzeria_android.src.StoreOrder;

public class MainActivity extends AppCompatActivity {
    private Button chicago_btn;
    private Button ny_btn;
    private Button store_order_btn;
    private Button order_btn;
    public static int orderNum = 1;
    public static Order pizzaOrder = new Order(orderNum);
    public static StoreOrder totalOrders = new StoreOrder();

    /**
     * Getter for order.
     *
     * @return
     */
    public Order getPizzaOrder() {
        return pizzaOrder;
    }

    /**
     * Adds the pizza from either chicago or new york view to the current order.
     *
     * @param pizza
     */
    public void addOrder(Pizza pizza) {
        pizzaOrder.add(pizza);
    }

    /**
     * When current order is placed it resets and adds the new space on the store
     * order list.
     */
    public void newOrder() {
        orderNum += 1;
        pizzaOrder = new Order(orderNum);
    }

    /**
     * Add current order to store orders.
     *
     * @param pizza_Order
     */
    public void addStoreOrders(Order pizza_Order) {
        totalOrders.add(pizza_Order);
    }

    /**
     * Getter for the list of store orders.
     *
     * @return
     */
    public StoreOrder getTotalOrders() {
        return totalOrders;
    }

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
        intent.putExtra("OrderNUM", orderNum);
        startActivity(intent);
    }
}