/**
 * The Order class will implement the Customizable interface and is meant to add all the
 * pizzas ordered and keep track of which order number they correspond to. It also has methods to
 * calculate the total price of the entire order.
 * @author Nick Lluen, Ahnaf Rashid
 */
package com.example.ru_pizzeria_android.src;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Order implements Customizable{
    int orderNum;
    private double TAX = 0.06625;
    private ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
    private boolean ordered = false;

    /**
     * Initializes the Order object
     * @param orderNum the initial order number
     */
    public Order(int orderNum){
        this.orderNum = orderNum;
    }

    /**
     * This method is meant to return the order number
     * @return int the value of the current Order number
     */
    public int getOrderNum() {
        return orderNum;
    }

    /**
     * This method will take in the current pizza that has been ordered and add it to
     * list of other pizzas
     * @param obj The current pizza
     * @return true if the pizza has been added
     */
    @Override
    public boolean add(Object obj) {
        pizzas.add((Pizza) obj);
        return true;
    }

    /**
     * This method will take in the current pizza that has been ordered and add it to
     * list of other pizzas
     * @param pizza The current pizza
     * @return true if the pizza has been added
     */
    public boolean add(Pizza pizza){
        pizzas.add(pizza);
        return true;
    }

    /**
     * This method will take in the current pizza that the user wants to remove
     * @param obj The current pizza
     * @return false if the pizza has been removed
     */
    @Override
    public boolean remove(Object obj) {
        pizzas.remove((Pizza) obj);
        return false;
    }

    /**
     * This method will return the pizzas in the arraylist
     * @return Arraylist<Pizza> will return the array list of Pizza objects
     */
    public ArrayList<Pizza> getPizzas() {
        ArrayList<String> pizzaList = new ArrayList<String>();
        for( int i = 0; i < pizzas.size(); i++ ){
            pizzaList.add( pizzas.get(i).toString());
        }
        return pizzas;
    }

    /**
     * This method will return the pizzas in the arraylist
     * @return ArrayList<String> will return the array list of String objects
     */
    public ArrayList<String> getPizzaOrder(){
        ArrayList<String> pizzaOrder = new ArrayList<String>();
        for(int i = 0; i < pizzas.size(); i++){
            pizzaOrder.add(pizzas.get(i).getPizza());
        }
        return pizzaOrder;
    }

    /**
     * This method will look at the entire pizza order and add up all the prices of each pizza
     * @return double value of the total order cost
     */
    public double calcTotal(){
        double price = 0;
        for(int i = 0; i < pizzas.size(); i++ ){
            price = price + pizzas.get(i).price();
        }
        DecimalFormat decForm = new DecimalFormat("###,###.00");
        return price;
    }

    /**
     * Calculates the total of order with tax
     * @return
     */
    public String calcTotalTax(){
        double price = 0;
        for(int i = 0; i < pizzas.size(); i++ ){
            price = price + pizzas.get(i).price();
        }
        price  = price + (price*TAX);
        DecimalFormat decForm = new DecimalFormat("###,###.00");
        return decForm.format(price);
    }

}
