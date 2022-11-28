package com.example.ru_pizzeria_android.src;

public class Meatzza extends Pizza{
    private String[] toppingsList = new String[] {"sausage","pepperoni","beef","ham"};

    public Meatzza(){
        for(int i = 0; i < toppingsList.length; i++){
            getToppings().add(Topping.valueOf(toppingsList[i]));
        }
        super.setSize("small");
    }

    /**
     This method will add the selected topping to the pizza
     @param obj The topping that is selected in the interface
     @return true if the topping has been added
     */
    @Override
    public boolean add(Object obj) {
        getToppings().add(Topping.valueOf((String) obj));
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
     This method will calculate the price of the pizza depending on the size selected
     @return double value of the price calculate
     */
    @Override
    public double price() {
        double price = 0;
        if(getSize().equals("small")){
            price = 15.99;
        } else if (getSize().equals("medium")) {
            price = 17.99;
        } else if (getSize().equals("large")) {
            price = 19.99;
        }
        return price;
    }

    /**
     This method is meant to put the entire details of the pizza into a string
     @return String return a string of the entire pizza order
     */
    @Override
    public String getPizza(){
        String fullPizza = "Meatzza";
        fullPizza = fullPizza + super.getPizza();
        return fullPizza;
    }
}
