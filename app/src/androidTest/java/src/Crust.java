/**
 * The Crust class is meant to initialize and return the type of crust of the pizza
 * @author Nick Lluen, Ahnaf Rashid
 */
package src;

public class Crust {
    private String crust;


    /**
    Initializes the type of crust
    @param Crust the type of crust in string format
     */
    public Crust(String Crust){
        crust = Crust.toLowerCase();
    }

    /**
     Returns the type of crust of the pizza
     @return String of the type of crust
     */
    public String getCrust() {
        return crust;
    }

}
