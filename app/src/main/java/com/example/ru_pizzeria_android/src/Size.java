/**
 * The Size class is meant to initialize and return the size of the pizza
 * @author Nick Lluen, Ahnaf Rashid
 */
package com.example.ru_pizzeria_android.src;

public class Size {
    private String sizes;

    /**
     Initializes the size object
     @param size the size in string format
     */
    public Size(String size){
        size.toLowerCase();
        sizes = size;
    }

    /**
     returns the size of the pizza
     @return String of the size of pizza
     */
    public String getSize() {
        return sizes;
    }

    /**
     the equals method compares the inputted size with the current
     size
     @return true if the sizes are equal, false otherwise
     */
    public boolean equals(String Size) {
        if(sizes.equals(Size)){
            return true;
        }
        return false;
    }
}
