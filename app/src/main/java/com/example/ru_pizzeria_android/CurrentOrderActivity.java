/**
 * The CurrentOrderActivity is meant to hold all the methods for modifying the current order such as removing
 * any pizza in the order. This class will update the prices accordingly whenever a pizza is removed.
 *
 * @author Nick Lluen, Ahnaf Rashid
 */
package com.example.ru_pizzeria_android;

import static com.example.ru_pizzeria_android.MainActivity.orderNum;
import static com.example.ru_pizzeria_android.MainActivity.pizzaOrder;
import static com.example.ru_pizzeria_android.MainActivity.totalOrders;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
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

    /**
     * This will initialize what the Current Order Activity will look like on creation
     * @param savedInstanceState
     *
     */
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
            /**
             * the list of pizzas in the current order
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
            }
        });
        calc();

        remove_pizza = findViewById(R.id.remove_pizza);
        remove_pizza.setOnClickListener(new View.OnClickListener() {
            /**
             * Whenever this button is clicked, it will remove whichever pizza was selected
             * @param v
             */
            @Override
            public void onClick(View v) {
                remove();
            }
        });

        /**
         * Whenever this button is clicked, it will complete the current order
         * @param v
         */
        place_order = findViewById(R.id.place_order);
        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();
            }
        });
    }

    /**
     * This method is called to update the text boxes on the interface to correspond with the subtotal price of
     * the order, the calculated tax based on 0.06625% of the subtotal, and the total cost which is both previous
     * prices added together.
     */
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

    /**
     * This method will remove the current selected pizza from the current order display and if there are no more items to
     * remove it will tell the user that they are unable to remove more
     */
    public void remove() {
        if (pizzaOrders.size() > 0) {
            order_list = findViewById(R.id.order_list);
            pizzaOrders.remove(pizzaOrders.get(index));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pizzaOrders);
            order_list.setAdapter(adapter);
            order.getPizzas().remove(index);
            calc();
        }else{
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("No Items");
            alert.setMessage("You do not have an pizzas in your cart");
            AlertDialog dialog = alert.create();
            dialog.show();
        }
    }

    /**
     * This method is called when the Place Order button is clicked and will add the current order to the total
     * order and will update the order number. This information will be displayed in the Store Order Interface.
     */
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