package FOODIE;
import java.util.*;
import java.text.*;
import java.io.Serializable;

class PayMobile extends PayMethod implements IPayable, Serializable{
    
    //================================================================//
    //================================================================//

    PayMobile(){
        super("PayMobile");
    }

    public void pay(double amount){
        Scanner sc = new Scanner(System.in);
        NumberFormat formatter = new DecimalFormat("#0.00");     
        System.out.println(formatter.format(4.0));

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
