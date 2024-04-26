package FOODIE;
import java.util.*;
import java.io.Serializable;
/**
 * This class represents a Food object on the menu
 */
public class Food implements Serializable{
    // Name of food item.
    private String name;
    // Availability of food item
    private boolean avail; // availability
    //Price of food item
    private double price;
    // Category of food item
    private String category;

    //================================================================//
    //================================================================//
    /**
     * Constructs a Food object with the specified attributes
     * @param name Name of food item
     * @param price Price of food item
     * @param category Category of food item
     */
    public Food(String name,double price,String category){
        Logger.log("Creating new food");
        
        this.name = name;
        this.avail = true;
        this.price = price;
        this.category = category;
    }

    //================================================================//
    //================================================================//

     /**
     * Gets name of the food item
     * @return Name of food item
     */
    public String getName() {return name;}
    /**
     * Sets name of food item
     * @param tmp Name of the food item 
     */
    public void setName(String tmp) {name = tmp;}
    /**
     * Gets the availability of the food item.
     * @return Availability of food item
     */
    public boolean getAvail() {return avail;}
    /**
     * Sets availability of food item.
     * @param tmp Availability of food item
     */
    public void setAvail(boolean tmp) {avail = tmp;}
    /**
     * Gets price of food item
     * @return Price of food item.
     */
    public double getPrice() {return price;}
     /**
     * Sets price of food item. 
     * @param tmp Price of food item.
     */
    public void setPrice(double tmp) {price = tmp;}
    /**
     * Gets category of food item.
     * @return Category of food item.
     */
    public String getCategory() {return category;}
    /**
     * Sets category of food item.
     * @param tmp Category of food item.
     */
    public void setCategory(String tmp) {category = tmp;}
}
