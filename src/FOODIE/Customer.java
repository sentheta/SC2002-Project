package FOODIE;
import java.util.*;
import java.time.*;
import java.io.Serializable;
/**
 * Represents a customer of the restaurant
 * A customer can make an order, check the status of their order, pay for their order and collect their order. 
 */
public class Customer implements IActionable, Serializable{
    /**
     * Branch object that represents the branch that they are at.
     */
    private Branch branch; 
    /**
     * ArrayList of orders that represent the numerous orders that a customer can make (customer can have more than 1 order)
     */
    private ArrayList<Order> orders;
    /**
     * Menu object that customer uses to order.
     */
    private Menu menu;

    //================================================================//
    //================================================================//
    /**
     * Constructor for Customer object, with Branch, Order ArrayList and Menu
     * @param branch Branch object that is used to initialize branch variable to. 
     */
    Customer(Branch branch){
        Logger.log("Creating new customer");

        this.branch = branch;
        this.orders = branch.getOrders();
        this.menu = branch.getMenu();
    }

    /**
     * This method allows the customer to create an Order object, that will be added to the orders ArrayList. 
     * The method first displays the Menu, then customer is prompted to key in the item and quantity. 
     * Method asks for any remarks that the customer might have for their order.
     * @see Order
     * @see Food
     */
    public void makeOrder(){
        Scanner sc = new Scanner(System.in);

        menu.display();
        Order order = new Order();

        // make while loop to force user enter a valid itemNumber and qty
        while(true){
            try{
                System.out.println("Item number (or an invalid value to finish order): ");
                System.out.print(">>> ");
                int itemNumber = Integer.parseInt(sc.nextLine());
                if(itemNumber<1 || itemNumber>menu.getFoods().size()) break;

                System.out.println("Quantity (or an invalid value to finish order): ");
                System.out.print(">>> ");
                int quantity = Integer.parseInt(sc.nextLine());
                if(quantity<0) break;

                Food selectedFood = menu.getFoods().get(itemNumber-1); //assume the choice start from 1 
                // Adding of food item to the order.
                order.addItem(selectedFood, quantity);

            }catch(Exception e){
                break;
            }
        }
        if(order.getFoods().size() == 0){
            System.out.println("Empty order. Aborted"); return;
        }

        try{
            // Asking for any remarks the customer might have.
            String remarks;
            System.out.println("Any additional remarks. Please declare TAKEAWAY if you want to do so");
            System.out.print(">>> ");
            remarks = sc.nextLine();
            order.setRemarks(remarks);
        }
        catch(Exception e){}
    // Adding Order object to orders ArrayList of the customer. 
        orders.add(order);
        System.out.println("Order " + order.getId() + " created");
    }
    /**
     * Checks the status of the order for the customer. 
     * @see Order
     */
    public void checkOrder(){
        Scanner sc = new Scanner(System.in);
        // Get order ID.
        System.out.println("Enter order ID: ");
        System.out.print(">>> ");
        int orderId = Integer.parseInt(sc.nextLine());
        // Compare keyed in order ID to customer's orders
        for (Order order : orders) if(order.getId()==orderId){
            if(order.getStatus() == Order.OrderStatus.READY){
                LocalDateTime time_now = LocalDateTime.now();
                LocalDateTime order_time = LocalDateTime.parse(order.getStartTime());
                Duration duration = Duration.between(order_time, time_now);
                if (duration.toMinutes() > 5) {
                    // System.out.println("Order " + order.getId() + " is now cancelled");
                    order.setStatus(Order.OrderStatus.CANCELLED);
                }
                else {
                    // System.out.println("Order " + order.getId() + " is ready to pickup");
                    order.display();
                }
            }
            
            System.out.println("Order ID: " + orderId);
            System.out.println("Status: " + order.getStatus());
            return;
        }

        System.out.println("Order with ID " + orderId + " not found");
    }
    /**
     * This method simulates payment for a customer's order. 
     * This method calculates the total price of the order using the order items on the Order object as well as their 
     * respective quantities. 
     */
    public void payOrder(){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter order ID: ");
        System.out.print(">>> ");
        int orderId = Integer.parseInt(sc.nextLine());

         // Checking if the Order ID exists
        for (Order order : orders) if(order.getId()==orderId){
            double totalPrice = order.calculatePrice(); //calculatePrice() in order class
                
            System.out.println("Choose your payment method: ");
            // Displaying various payment methods for customer to use
            for(int i=0; i<App.payMethods.size(); i++){
                System.out.println((i+1)+". "+App.payMethods.get(i).name);
            }
            System.out.print(">>> ");
            
            try{
                // Selection of payment method.
                int i = Integer.parseInt(sc.nextLine());
                App.payMethods.get(i-1).pay(totalPrice);
            }
            catch(Exception e){
                e.printStackTrace(); return;
            }

            return;
        }

        System.out.println("Order with ID " + orderId + " not found");
    }
     /**
     * This method lets users to collect their order, if the order status is READY. 
     * Method compares the order ID that the customer wishes to collect with the orders ArrayList of the customer,
     * then checks if the order is READY. 
     * Once customer collects the order, change order status to COMPLETED.
     */
    public void collectOrder(){
        Scanner sc = new Scanner(System.in);
        // Getting order ID. 
        System.out.println("Enter order ID: ");
        System.out.print(">>> ");
        int orderId = Integer.parseInt(sc.nextLine());
        // Iterating through orders ArrayList to find the specified order.
        for(Order order : orders){
            if(order.getId()==orderId && order.getStatus()==Order.OrderStatus.READY){
                // Collection of order, set order status to COMPLETED.
                order.setStatus(Order.OrderStatus.COMPLETED);
                System.out.println("Order ID " + orderId + " collected");
                return;
            }
        }

        System.out.println("Order with ID " + orderId + " not found or not ready for pickup");
    }
    /**
     * Method allows user to see if their order is ready to pickup.
     * If time passed since order placement is more than 5 minutes, change the order status of the order to CANCELLED.
     */
    void viewReadyToPick(){
        if (orders.size() == 0) {
    		System.out.println("No orders is ready."); 
    		return;
    	}
    	for (Order order : orders) {
    		if(order.getStatus() == Order.OrderStatus.READY){
    			LocalDateTime time_now = LocalDateTime.now();
    			LocalDateTime order_time = LocalDateTime.parse(order.getStartTime());
    			Duration duration = Duration.between(order_time, time_now);
    			if (duration.toMinutes() > 5) {
                    // Time difference bewteen time_now and order_time.
    				System.out.println("Order " + order.getId() + " is now cancelled");
                    // Cancel order if time difference is more than 5 minutes.
    				order.setStatus(Order.OrderStatus.CANCELLED);
    			}
    			else {
    				System.out.println("Order " + order.getId() + " is ready to pickup");
    				order.display();
    			}
    		}
    	}
    }
    /**
	 * Allows customer to choose from a pre-determined number of options that are available to an customer.
	 * The method first presents all available functionality, before allowing user to choose an option, 
	 *  executing the method based on the user's input.
	 *  @return true if method successfully executes, false if an invalid choice is input. 
	 */
    public boolean chooseAction(){
        Scanner sc = new Scanner(System.in);
        // Printing available functionality to a customer.
        System.out.println();
        System.out.println("--Choose customer action--");
        System.out.println("1. Make an order");
        System.out.println("2. Check an order");
        System.out.println("3. Make a payment");
        System.out.println("4. Collect an order");
        System.out.println("5. Display menu items");
        System.out.println("6. View ready to pick up orders");
        System.out.println("Other values to end customer session");

        try{
            System.out.print(">>> ");
            // Getting their input option.
            int choice = Integer.parseInt(sc.nextLine());
            switch(choice){
            case 1: makeOrder(); return true;
            case 2: checkOrder(); return true;
            case 3: payOrder(); return true;
            case 4: collectOrder(); return true;
            case 5: branch.getMenu().display(); return true;
            case 6: viewReadyToPick(); return true;
            }
        }
        catch(Exception e){}

        System.out.println("Terminating customer session...");
        return false;
    }

    //================================================================//
    //================================================================//

    /**
     * Returns a customer's list of orders
     * @return ArrayList of Order of customer.
     */
    public ArrayList<Order> getOrders(){return orders;}
    /**
     * Returns the Branch object that customer is at.
     * @return Branch object.
     */
    public Branch getBranch(){return branch;}

}
