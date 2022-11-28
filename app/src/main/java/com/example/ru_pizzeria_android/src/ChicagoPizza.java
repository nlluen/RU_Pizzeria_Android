/**
 * The ChicagoPizza class implements the PizzaFactory interface and initializes four different
 * types of pizza flavors.
 * @author Nick Lluen, Ahnaf Rashid
 */
package com.example.ru_pizzeria_android.src;

public class ChicagoPizza implements PizzaFactory{

    /**
     Initializes a Deluxe Pizza object
     @return Pizza will return the Deluxe Pizza object
     */
    @Override
    public Pizza createDeluxe() {
        Pizza deluxePizza = new Deluxe();
        return deluxePizza;
    }

    /**
     Initializes a Meatzza Pizza object
     @return Pizza will return the Meatzza Pizza object
     */
    @Override
    public Pizza createMeatzza() {
        Pizza meatzzaPizza = new Meatzza();
        return meatzzaPizza;
    }

    /**
     Initializes a BBQChicken Pizza object
     @return Pizza will return the BBQChicken Pizza object
     */
    @Override
    public Pizza createBBQChicken() {
        Pizza bbqPizza = new BBQChicken();
        return bbqPizza;
    }

    /**
     Initializes a BuildYourOwn Pizza object
     @return Pizza will return the BuildYourOwn Pizza object
     */
    @Override
    public Pizza createBuildYourOwn() {
        Pizza byo = new BuildYourOwn();
        return byo;
    }
}
