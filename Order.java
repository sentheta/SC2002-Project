package FOODIE;
import java.util.*;
import java.io.Serializable;

class Order implements Serializable{
    
    enum OrderStatus{NEW, PROCESSING, READY, COMPLETED, CANCELLED};

    private static int nextId = 1;

    private int id;
    private String startTime;
    private OrderStatus status;
    private ArrayList<Food> foods;
    private ArrayList<Integer> quantities;
    private String remarks;

    //================================================================//
    //================================================================//


    Order(){
        this.id = nextId++;
        this.foods = new ArrayList<Food>();
        this.quantities = new ArrayList<Integer>();
        this.status = OrderStatus.NEW;
        this.startTime = java.time.LocalDateTime.now().toString();
        this.remarks = "";
    }
    Order(ArrayList<Food> foods, ArrayList<Integer> quantities, String remarks){
        this.id = nextId++;
        this.foods = foods;
        this.quantities = quantities;
        this.status = OrderStatus.NEW;
        this.startTime = java.time.LocalDateTime.now().toString();
        this.remarks = remarks;
    }

    public double calculatePrice() {
        double totalPrice = 0;
        for (int i=0; i<foods.size(); i++){
            totalPrice += foods.get(i).getPrice() * quantities.get(i);
        }
        return totalPrice;
    }

    public void addItem(Food food,int quantity){
        foods.add(food);
        quantities.add(quantity);
    }

  
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

    public void display(){
        System.out.println("Order ID: " + id);
        System.out.println("Order Status: " + status);
        System.out.println("Order Time: " + startTime);
        System.out.println("Items:");
        for(int i=0; i<foods.size(); i++){
            System.out.println(foods.get(i).getName() + " x" + quantities.get(i));
        }
    }

    //================================================================//
    //================================================================//

    
    public int getId() {return id;}
    public ArrayList<Food> getFoods() {return foods;}
    public ArrayList<Integer> getQuantities() {return quantities;}
    public OrderStatus getStatus() {return status;}
    public void setStatus(OrderStatus tmp) {this.status = tmp;}
    public String getRemarks() {return remarks;}
    public void setRemarks(String tmp) {remarks = tmp;}
}
