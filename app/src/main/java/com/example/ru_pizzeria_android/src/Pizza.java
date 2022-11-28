/**
 * The Pizza class is an abstract class that defines several methods and variables
 * that serves as a guideline for every other class that extends it.
 * @author Nick Lluen, Ahnaf Rashid
 */
package com.example.ru_pizzeria_android.src;

import java.util.ArrayList;

public abstract class Pizza implements Customizable {
    private ArrayList<Topping> toppings = new ArrayList<Topping>();
    private Crust crust;
    private Size size;

    /**
     This method is meant to be overridden by the classes that extend this class and should calculate the price
     of the pizza depending on the size and toppings selected by the user and will add 1.59 for every
     topping selected up to 7 toppings
     @return double value of the price calculated
     */
    public abstract double price();

    /**
     This method will set the type of crust of the pizza depending on what the user has selected
     @return false
     */
    public boolean setCrust(String pizCrust){
        crust = new Crust(pizCrust);
        return false;
    }

    /**
     This method sets the size of the pizza depending on what the user has selected
     @return false
     */
    public boolean setSize(String size){
        if(size == "small" || size == "medium" || size == "large") {
            this.size = new Size(size);
            return true;
        }
        return false;
    }

    /**
     This method will return the entire array list of toppings for the pizza
     @return Arraylist<Topping> an array list that contains every topping on the pizza
     */
    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    /**
     This method will return the size of the pizza
     @return Size of the pizza
     */
    public Size getSize() {
        return size;
    }

    /**
     This method is meant to put the entire details of the pizza into a string
     @return String return a string of the entire pizza order
     */
    public String getPizza(){
        String full_pizza = "";
        full_pizza = full_pizza + " " + crust.getCrust();
        for( int i = 0; i < toppings.size(); i++ ){
            full_pizza = full_pizza + ", " + toppings.get(i);
        }
        full_pizza = full_pizza + ", " +  size.getSize();
        return full_pizza;
    }
}
