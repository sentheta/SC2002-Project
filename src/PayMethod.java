package FOODIE;
import java.util.*;
import java.text.*;
import java.util.concurrent.TimeUnit;
import java.io.Serializable;
/**
 * Class representing payment method.
 * Has information such as name of payment method, and functionality to simulate payment by a customer. 
 */
public class PayMethod implements IPayable, Serializable{
    /** Name of payment method. */ 
    public String name;

    //================================================================//
    //================================================================//
    /** Creates a new payment method object with name */
    PayMethod(String name){
        this.name = name;
    }
    /**
     * Simulates payment by a customer. 
     * Method formats payment amount to 2 decimal places, before processing the payment by a customer. 
     */
    public void pay(double amount){
        // Formats amount payable to 2 decimal places. 
        NumberFormat formatter = new DecimalFormat("#0.00");     
        System.out.println(formatter.format(amount));

        System.out.println("Proccessing payment...");
        try{
            TimeUnit.SECONDS.sleep(3);
        }
        catch(Exception e){
            e.printStackTrace(); return;
        }
        // Customer successfully paid for their order. 
        System.out.println("Successfully paid $" + amount + " using " + name);
    }

    //================================================================//
    //================================================================//
    /** Gets name of payment method */
    public String getName(){return name;};
    /** Sets name of payment method to a new one. */
    public void setName(String tmp){name = tmp;};
}
