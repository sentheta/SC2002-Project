package scse_FOODIE;
/**
 * An interface to represent the payment implementation when customers pay for their orders. 
 */
public interface IPayable{
    // Payment name
    public String name = "Payment";
    /**
     * Display amount to pay, simulating payment.
     * @param amount Amount to pay.
     */
    // Display interface to make payment
    public void pay(double amount);
}
