package scse_FOODIE;
import java.util.*;
import java.text.*;
import java.io.Serializable;
/**
 * PayCash class simulates payment by a customer using cash.
 * Extends the PayMethod class. 
 */
public class PayCash extends PayMethod implements Serializable{
    
    //================================================================//
    //================================================================//
    /**
	 * Default constructor creating PayCash object with name Cash.
	 */
    PayCash(){
        super("Cash");
    }

    //================================================================//
    //================================================================//
    
}
