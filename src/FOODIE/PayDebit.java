package FOODIE;
import java.util.*;
import java.text.*;
import java.io.Serializable;
/**
 * PayDebit class simulates payment by a customer using a debit card.
 * Class extends PayMethod class. 
 */
public class PayDebit extends PayMethod implements Serializable{

    //================================================================//
    //================================================================//
    /**
	 * Default constructor creating a PayDebit object with name debit card.
	 */
    PayDebit(){
        super("Debit card");
    }

    /**
     * {@inheritDoc}
     * Allows customers to pay using debit card. 
     */
    public void pay(double amount){
        Scanner sc = new Scanner(System.in);
        NumberFormat formatter = new DecimalFormat("#0.00");     
        // Prints payment amount in 2 decimal places.
        System.out.println(formatter.format(amount));

        System.out.println("Please tap your card");
        sc.nextLine();
        System.out.println("Successfully paid $" + formatter.format(amount) + " using " + name);
    }

    //================================================================//
    //================================================================//
    
}
