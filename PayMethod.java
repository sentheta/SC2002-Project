import java.io.*;
import java.util.*;

public class PayMethod {
    private String name;
   
    public PayMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void pay(double amount) {
        System.out.println("Payment of $" + amount + " made using " + name);
    }
}
