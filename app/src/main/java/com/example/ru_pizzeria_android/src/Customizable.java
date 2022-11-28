/**
 * The Customizable interface is meant to define the add and remove methods that will be used
 * by the different types of Pizza classes
 * @author Nick Lluen, Ahnaf Rashid
 */
package com.example.ru_pizzeria_android.src;

public interface Customizable<E> {

    /**
     This method is meant to be overridden by the classes that extend
     this interface and is meant to add the selected topping to the pizza
     @param obj The topping that is selected in the interface
     @return true if the topping has been added
     */
    boolean add(Object obj);

    /**
     This method is meant to be overridden by the classes that extend
     this interface and is meant to remove the selected topping from the pizza
     @param obj The topping that is selected in the interface
     @return false if the topping has been removed
     */
    boolean remove(Object obj);
}
