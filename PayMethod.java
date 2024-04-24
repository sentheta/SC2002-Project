package FOODIE;
import java.util.*;
import java.text.*;
import java.util.concurrent.TimeUnit;
import java.io.Serializable;

class PayMethod implements IPayable, Serializable{
    
    public String name;

    //================================================================//
    //================================================================//

    PayMethod(String name){
        this.name = name;
    }

    public void pay(double amount){
        NumberFormat formatter = new DecimalFormat("#0.00");     
        System.out.println(formatter.format(4.0));

        System.out.println("Proccessing payment...");
        try{
            TimeUnit.SECONDS.sleep(3);
        }
        catch(Exception e){
            e.printStackTrace(); return;
        }
        System.out.println("Successfully paid $" + amount + " using " + name);
    }

    //================================================================//
    //================================================================//
    
    public String getName(){return name;};
    public void setName(String tmp){name = tmp;};
}
