package FOODIE;
import java.util.*;
import java.text.*;
import java.io.Serializable;

class PayNETS extends PayMethod implements Serializable{

    //================================================================//
    //================================================================//

    PayNETS(){
        super("PayNETS");
    }


    public void pay(double amount){
        Scanner sc = new Scanner(System.in);
        NumberFormat formatter = new DecimalFormat("#0.00");     
        System.out.println(formatter.format(amount));

        System.out.println("Please tap your card");
        sc.nextLine();
        System.out.println("Successfully paid $" + amount + " using NETS");
    }

    //================================================================//
    //================================================================//
    
}
