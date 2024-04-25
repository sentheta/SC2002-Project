package scse_FOODIE;
import java.util.*;
import java.text.*;
import java.io.Serializable;
/**
 * PayNETS class simulates payment by a customer using a NETS method.
 * Class extends PayMethod class. 
 */
public class PayNETS extends PayMethod implements Serializable{

    //================================================================//
    //================================================================//
    /**
	 * Default constructor creating a PayNETS object with name PayNETS.
	 */
    PayNETS(){
        super("PayNETS");
    }

    /**
     * {@inheritDoc}
     * Allows users to simulate payment using a NETS method.
     */
    public void pay(double amount){
        Scanner sc = new Scanner(System.in);
        NumberFormat formatter = new DecimalFormat("#0.00"); 
        // Display how much to pay.
        System.out.println(formatter.format(amount));
        //Simulation of payment. 
        System.out.println("Please tap your card");
        sc.nextLine();
        System.out.println("Successfully paid $" + amount + " using NETS");
    }

    //================================================================//
    //================================================================//
    
}
