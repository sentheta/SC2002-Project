import java.util.*;

class Order {
    enum OrderStatus {NEW, PROCESSING, READY, COMPLETED}

    private static int nextId = 1;

    private int id;
    private String startTime;
    private OrderStatus status;
    private ArrayList<Food> foods;
    private ArrayList<Integer> quantities;

    //================================================================//

    Order(){

    }
    Order(ArrayList<Food> foods, ArrayList<Integer> quantities) {
        this.id = nextId++;
        this.foods = foods;
        this.quantities = quantities;
        this.status = OrderStatus.NEW;
        this.startTime = java.time.LocalDateTime.now().toString();
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
        System.out.println("Start Time: " + startTime);
        System.out.println("Items:");
        for(int i=0; i<foods.size(); i++){
            System.out.println("- " + quantities.get(i) + "x " + foods.get(i).getName() + " - $" + foods.get(i).getPrice() * quantities.get(i));
        }
        System.out.println("Total Price: $" + calculatePrice());
    }

    //================================================================//

    public int getId() {return id;}
    public ArrayList<Food> getFood() {return foods;}
    public ArrayList<Integer> getQuantities() {return quantities;}
    public OrderStatus getStatus() {return status;}
    public void setStatus(OrderStatus tmp) {this.status = tmp;}
}
