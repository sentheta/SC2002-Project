import java.io.*;
import java.util.*;

public class Food{
    private String name;
    private boolean availability;
    private double price;
    private String category;

    
    public Food(String name, boolean availability, double price, String category) {
        this.name = name;
        this.availability = availability;
        this.price = price;
        this.category = category;
    }
    
 
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public boolean getAvailable() {return availability;}
    public void setAvailable(boolean availability) {this.availability = availability;}
    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}
    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}
}