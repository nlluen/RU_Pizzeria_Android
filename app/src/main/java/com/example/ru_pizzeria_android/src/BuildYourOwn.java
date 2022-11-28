/**
 * THe BuildYourOwn class is meant to extend the Pizza class because it is a type of pizza.
 * This class overrides methods in the Pizza class in order to add and remove toppings. The
 * method will also calculate the correct price depending on size and the amount of toppings added
 * @author Nick Lluen, Ahnaf Rashid
 */
package com.example.ru_pizzeria_android.src;

public class BuildYourOwn extends Pizza{

    /*
    Initializes the pizza to be size small by default
     */
    public BuildYourOwn(){
        super.setSize("small");
    }


    /**
     This method will add the selected topping to the pizza
     @param obj The topping that is selected in the interface
     @return true if the topping has been added
     */
    @Override
    public boolean add(Object obj) {
        getToppings().add(Topping.valueOf(String.valueOf(obj)));
        return true;
    }
    /**
     This method will remove the selected topping from the pizza
     @param obj The topping that is selected in the interface
     @return false if the topping has been removed
     */
    @Override
    public boolean remove(Object obj) {
        getToppings().remove(Topping.valueOf((String) obj));
        return false;
    }

    /**
     This method will calculate the price of the pizza depending on the size selected and will
     add 1.59 for every topping selected up to 7 toppings
     @return double value of the price calculated
     */
    @Override
    public double price() {
        double price = 0;
        if(getSize().equals("small")){
            price = 8.99;
        } else if (getSize().equals("medium")) {
            price = 10.99;
        } else if (getSize().equals("large")) {
            price = 12.99;
        }
        if(getToppings().size() > 0){
            price = price + (getToppings().size()) * 1.59;
        }
        return price;
    }

    /**
     This method is meant to put the entire details of the pizza into a string
     @return String return a string of the entire pizza order
     */
    @Override
    public String getPizza(){
        String fullPizza = "Build Your Own";
        fullPizza = fullPizza + super.getPizza();
        return fullPizza;
    }
}
