/**
 * The MainActivity class is meant to be the first Activity that the user sees when they load the app. It will show
 * them a list of all the possible choices that they are able to choose. The user will be able to click on all 4 pizza
 * flavors for Chicago and New York. It will also display the option to check the Current Order and the Store Orders.
 * @author Nick Lluen, Ahnaf Rashid
 */
package com.example.ru_pizzeria_android;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.ru_pizzeria_android.src.Order;
import com.example.ru_pizzeria_android.src.Pizza;
import com.example.ru_pizzeria_android.src.StoreOrder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static int orderNum = 1;
    public static Order pizzaOrder = new Order(orderNum);
    public static StoreOrder totalOrders = new StoreOrder();
    private ArrayList<Item> items = new ArrayList<>();
    private int [] itemImages = {R.drawable.chicbbq, R.drawable.chicdeluxe, R.drawable.chicmeatzza,R.drawable.chicbyo,
            R.drawable.nybbq, R.drawable.nydeluxe, R.drawable.nymeatzza, R.drawable.nybyo, R.drawable.cart,R.drawable.pizzeria};


    /**
     * Get the references of all instances of Views defined in the layout file, set up the list of
     * items to be display in the RecyclerView.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toppings);
        RecyclerView rcview = findViewById(R.id.rcView_menu);
        setupMenuItems();
        ItemsAdapter adapter = new ItemsAdapter(this, items);
        rcview.setAdapter(adapter);
        rcview.setLayoutManager(new LinearLayoutManager(this));
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
     * Helper method to set up the data (the Model of the MVC).
     */
    private void setupMenuItems() {
        String [] itemNames = getResources().getStringArray(R.array.itemNames);
        for (int i = 0; i < itemNames.length; i++) {
            items.add(new Item(itemNames[i], itemImages[i], "$1.59"));
        }
    }
}