import java.util.*;

public class Food{
    private String name;
    private boolean avail; // availability
    private double price;
    private String category;

    //================================================================//
    //================================================================//

    public Food(String name,boolean avail,double price,String category) {
        Logger.log("Creating new food");
        
        this.name = name;
        this.avail = avail;
        this.price = price;
        this.category = category;
    }

    //================================================================//
    //================================================================//

 
    public String getName() {return name;}
    public void setName(String tmp) {name = tmp;}
    public boolean getAvail() {return avail;}
    public void setAvail(boolean tmp) {avail = tmp;}
    public double getPrice() {return price;}
    public void setPrice(double tmp) {price = tmp;}
    public String getCategory() {return category;}
    public void setCategory(String tmp) {category = tmp;}
}