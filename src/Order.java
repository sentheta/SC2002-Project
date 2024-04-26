package FOODIE;
import java.util.*;
import java.time.*;
import java.io.Serializable;
/**
 * This class represents an Order, made by a Customer.
 * Contains information such as OrderId, status, startTime, food items, quantities and remarks.
 */
public class Order implements Serializable{
    /**
     * Enum representing the status of an Order.
     */
    enum OrderStatus{NEW, PROCESSING, READY, COMPLETED, CANCELLED};
    // Static variable to store incrementing Id.
    private static int nextId = 1;
    // Order Id 
    private int id;
    // Order time 
    private String startTime;
    // Status of order
    private OrderStatus status;
    // food items on order
    private ArrayList<Food> foods;
    // Quantities of food item on order
    private ArrayList<Integer> quantities;
    // remarks for order, if any. 
    private String remarks;

    //================================================================//
    //================================================================//

    /**
     * Default constructor for Order, with empty ArrayList for foods and quantities. 
     */
    Order(){
        this.id = nextId++;
        this.foods = new ArrayList<Food>();
        this.quantities = new ArrayList<Integer>();
        this.status = OrderStatus.NEW;
        this.startTime = LocalDateTime.now().toString();
        this.remarks = "";
    }
     /**
     * Constructor for Order, creating an Order item with the specified attributes
     * @param foods Foods present in order
     * @param quantities Quantities of foods present in order
     * @param remarks Remarks of order if any.
     */
    Order(ArrayList<Food> foods, ArrayList<Integer> quantities, String remarks){
        this.id = nextId++;
        this.foods = foods;
        this.quantities = quantities;
        this.status = OrderStatus.NEW;
        this.startTime = LocalDateTime.now().toString();
        this.remarks = remarks;
    }
    /**
     * Calculates the total price of the order for customers to pay after placing their order.
     * @return price of order
     */
    public double calculatePrice() {
        double totalPrice = 0;
        //iterating through foods ArrayList to calculate total price of order.
        for (int i=0; i<foods.size(); i++){
            totalPrice += foods.get(i).getPrice() * quantities.get(i);
        }
        return totalPrice;
    }
    /**
     * Adding a food item to the order. 
     * @param food Food object to be added to the order.
     * @param quantity Quantity of Food object to be added.
     */
    public void addItem(Food food,int quantity){
        // Adding of food and quantity respectively to the order. 
        foods.add(food);
        quantities.add(quantity);
    }

    /**
     * Prints the receipt to a customer to show what they have ordered. Includes information like order id, status, order time, 
     * items, quantities and total price. 
     */
    public void printReceipt() {
        System.out.println("Order ID: " + id);
        System.out.println("Order Status: " + status);
        System.out.println("Order Time: " + startTime);
        System.out.println("Items:");
        for(int i=0; i<foods.size(); i++){
            System.out.println("- " + quantities.get(i) + "x " + foods.get(i).getName() + " - $" + foods.get(i).getPrice()*quantities.get(i));
        }
        System.out.println("Total Price: $" + calculatePrice());
    }
    /**
     * Displays order details.
     */
    public void display(){
        System.out.println("Order ID: " + id);
        System.out.println("Order Status: " + status);
        System.out.println("Order Time: " + startTime);
        System.out.println("Items:");
        for(int i=0; i<foods.size(); i++){
            System.out.println(foods.get(i).getName() + " x" + quantities.get(i));
        }
        System.out.println("Remarks: " + remarks);
    }

    //================================================================//
    //================================================================//
    
    /** Sets nextId to a new value. */
    public static void setNextId(int tmp){nextId = tmp;}
    /** Gets the order time of the order. */
    public String getStartTime() {return startTime;}
    /** Gets the order id of the order. */
    public int getId() {return id;}
    /** Gets the ArrayList of Food of the order */
    public ArrayList<Food> getFoods() {return foods;}
    /** Gets the ArrayList of quantities of food items */
    public ArrayList<Integer> getQuantities() {return quantities;}
    /** Gets the enum of the order status */
    public OrderStatus getStatus() {return status;}
    /** Sets status of order to a new one. */
    public void setStatus(OrderStatus tmp) {this.status = tmp;}
    /** Gets remarks of order. */
    public String getRemarks() {return remarks;}
    /** Sets remarks of order. */
    public void setRemarks(String tmp) {remarks = tmp;}
}
