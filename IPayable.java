package FOODIE;
interface IPayable{
    // Payment name
    public String name = "Payment";
        
    // Display interface to make payment
    public void pay(double amount);
}