/**
 * The PizzaFactory interface is meant to define the 4 methods to create the 4 different pizza
 * flavor/types so that the Pizza classes can implement it.
 * @author Nick Lluen, Ahnaf Rashid
 */
package com.example.ru_pizzeria_android.src;

public interface PizzaFactory {
    /**
     This method is meant to be overridden by the classes that implement this interface and
     initializes a Deluxe Pizza object
     @return Pizza will return the Deluxe Pizza object
     */
    Pizza createDeluxe();

    /**
     This method is meant to be overridden by the classes that implement this interface and
     initializes a Meatzza Pizza object
     @return Pizza will return the Meatzza Pizza object
     */
    Pizza createMeatzza();

    /**
     This method is meant to be overridden by the classes that implement this interface and
     initializes a BBQChicken Pizza object
     @return Pizza will return the BBQChicken Pizza object
     */
    Pizza createBBQChicken();

    /**
     This method is meant to be overridden by the classes that implement this interface and
     initializes a BuildYourOwn Pizza object
     @return Pizza will return the BuildYourOwn Pizza object
     */
    Pizza createBuildYourOwn();

}
