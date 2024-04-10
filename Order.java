package projectjava;
import java.util.Arrays;

class Order {
    private static int nextId = 1;
    private int id;
    private Food[] foods;
    private OrderStatus status;
    private String startTime;
    private int[] quantity;

    public Order(Food[] foods, int[] quantity) {
        this.id = nextId++;
        this.foods = foods;
        this.quantity = quantity;
        this.status = OrderStatus.NEW;
        this.startTime = getCurrentTime();
    }

    public double calculatePrice() {
        double totalPrice = 0;
        for (int i = 0; i < foods.length; i++) {
            totalPrice += foods[i].getPrice() * quantity[i];
        }
        return totalPrice;
    }

  
    public void printReceipt() {
        System.out.println("Order ID: " + id);
        System.out.println("Order Status: " + status);
        System.out.println("Start Time: " + startTime);
        System.out.println("Items:");
        for (int i = 0; i < foods.length; i++) {
            System.out.println("- " + quantity[i] + "x " + foods[i].getName() + " - $" + foods[i].getPrice() * quantity[i]);
        }
        System.out.println("Total Price: $" + calculatePrice());
    }

    public int getId() { return id;  }
    public OrderStatus getStatus() { return status;  }
    public void setStatus(OrderStatus status) { this.status = status; }
    private String getCurrentTime() { return java.time.LocalDateTime.now().toString(); }
}

enum OrderStatus {
    NEW, PROCESSING, READY, COMPLETED
}
