package scse_FOODIE;
import java.util.*;
import java.text.*;
import java.io.Serializable;
/**
 * PayMobile class simulates payment by a customer using a mobile method.
 * Class extends PayMethod class. 
 */
public class PayMobile extends PayMethod implements Serializable{
    
    //================================================================//
    //================================================================//
    /**
	 * Default constructor, creating a PayMobile method with name PayMobile.
	 */
    PayMobile(){
        super("PayMobile");
    }
    /**
     * {@inheritDoc}
     * Method creates a QR code for customers to scan and simulate payment. 
     */
    public void pay(double amount){
        Scanner sc = new Scanner(System.in);
        NumberFormat formatter = new DecimalFormat("#0.00");     
        // Display how much to pay.
        System.out.println(formatter.format(amount));

        System.out.println("Please scan this QR code on your Mobile");
        System.out.println("##########################################################");
        System.out.println("##########################################################");
        System.out.println("##########################################################");
        System.out.println("##########################################################");
        System.out.println("########              ####  ##    ##              ########");
        System.out.println("########  ##########  ####      ####  ##########  ########");
        System.out.println("########  ##      ##  ##    ##    ##  ##      ##  ########");
        System.out.println("########  ##      ##  ####  ##  ####  ##      ##  ########");
        System.out.println("########  ##      ##  ######  ##  ##  ##      ##  ########");
        System.out.println("########  ##########  ##########  ##  ##########  ########");
        System.out.println("########              ##  ##  ##  ##              ########");
        System.out.println("########################    ##    ########################");
        System.out.println("########      ##                ##    ######  ############");
        System.out.println("########  ####  ##  ##        ######  ####  ##  ##########");
        System.out.println("########          ##    ##  ####  ######  ##  ############");
        System.out.println("########  ##  ##  ####  ############  ####    ##  ########");
        System.out.println("##########            ##    ####  ##  ##    ##  ##########");
        System.out.println("########################        ##  ##  ######  ##########");
        System.out.println("########              ##        ##      ####      ########");
        System.out.println("########  ##########  ##    ##      ##      ####  ########");
        System.out.println("########  ##      ##  ##    ##  ##      ####  ##  ########");
        System.out.println("########  ##      ##  ######  ######    ##  ##  ##########");
        System.out.println("########  ##      ##  ##      ##  ######  ##  ##  ########");
        System.out.println("########  ##########  ##  ##  ######    ##        ########");
        System.out.println("########              ##  ##  ##  ##  ##  ##  ##  ########");
        System.out.println("##########################################################");
        System.out.println("##########################################################");
        System.out.println("##########################################################");
        System.out.println("##########################################################");
        sc.nextLine();
        System.out.println("Successfully paid $" + amount + " using PayMobile");
    }

    //================================================================//
    //================================================================//
    
}
