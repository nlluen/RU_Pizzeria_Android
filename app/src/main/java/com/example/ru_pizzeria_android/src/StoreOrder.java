package com.example.ru_pizzeria_android.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class StoreOrder implements Customizable {
    private ArrayList<Order> storeOrders;

    public StoreOrder() {
        storeOrders = new ArrayList<Order>();
    }

    /**
     * Add order to the arraylist of orders
     *
     * @param obj The topping that is selected in the interface
     * @return
     */
    @Override
    public boolean add(Object obj) {
        storeOrders.add((Order) obj);
        return false;
    }

    /**
     * Remove order to the arraylist of orders
     *
     * @param obj The topping that is selected in the interface
     * @return
     */
    @Override
    public boolean remove(Object obj) {
        storeOrders.remove((Order) obj);
        return false;
    }

    /**
     * Lists out the existing order's identification number
     *
     * @return
     */
    public ArrayList<Integer> listNums() {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (int i = 0; i < storeOrders.size(); i++) {
            numbers.add(storeOrders.get(i).getOrderNum());
        }
        return numbers;
    }

    /**
     * Getter for a specific order in the list of orders
     *
     * @param orderNum
     * @return
     */
    public Order getOrder(int orderNum) {
        for (int i = 0; i < storeOrders.size(); i++) {
            if (orderNum == storeOrders.get(i).getOrderNum()) {
                return storeOrders.get(i);
            }
        }
        return null;
    }

    /**
     * Gets the first element in the list
     * @return order
     */
    public Order getFirstOrder(){
        return storeOrders.get(0);
    }

    /**
     * Writes the orders and their contents into a text file given
     *
     * @param input
     * @throws FileNotFoundException
     */
    public void export(File input) throws FileNotFoundException {
        if (input == null) {

        } else if (!input.exists()) {
            PrintWriter writer = new PrintWriter(input);
            writer.println("Store Orders \n");
            for (int i = 0; i < storeOrders.size(); i++) {
                writer.println("Order " + storeOrders.get(i).orderNum + " - $" + storeOrders.get(i).calcTotalTax());
                ArrayList<Pizza> currOrder = storeOrders.get(i).getPizzas();
                for (int j = 0; j < currOrder.size(); j++) {
                    writer.println(currOrder.get(j).getPizza());
                }
                writer.println();
            }
            writer.close();
        }
    }
}
