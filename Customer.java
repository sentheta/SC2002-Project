package FOODIE;
import java.util.*;
import java.time.*;
import java.io.Serializable;

class Customer implements IActionable, Serializable{
    
    private Branch branch; 
    private ArrayList<Order> orders;
    private Menu menu;

    //================================================================//
    //================================================================//

    Customer(Branch branch){
        Logger.log("Creating new customer");

        this.branch = branch;
        this.orders = branch.getOrders();
        this.menu = branch.getMenu();
    }


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
                order.addItem(selectedFood, quantity);

            }catch(Exception e){
                break;
            }
        }
        if(order.getFoods().size() == 0){
            System.out.println("Empty order. Aborted"); return;
        }

        try{
            String remarks;
            System.out.println("Any additional remarks. Please declare TAKEAWAY if you want to do so");
            System.out.print(">>> ");
            remarks = sc.nextLine();
            order.setRemarks(remarks);
        }
        catch(Exception e){}

        orders.add(order);
        System.out.println("Order " + order.getId() + " created");
    }

    public void checkOrder(){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter order ID: ");
        System.out.print(">>> ");
        int orderId = Integer.parseInt(sc.nextLine());

        for (Order order : orders) if(order.getId()==orderId){
            System.out.println("Order ID: " + orderId);
            System.out.println("Status: " + order.getStatus());
            return;
        }

        System.out.println("Order with ID " + orderId + " not found");
    }

    public void payOrder(){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter order ID: ");
        System.out.print(">>> ");
        int orderId = Integer.parseInt(sc.nextLine());


        for (Order order : orders) if(order.getId()==orderId){
            double totalPrice = order.calculatePrice(); //calculatePrice() in order class
                
            System.out.println("Enter order ID: ");
            for(int i=0; i<App.payMethods.size(); i++){
                System.out.println((i+1)+". "+App.payMethods.get(i).name);
            }
            System.out.print(">>> ");
            
            try{
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

    public void collectOrder(){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter order ID: ");
        System.out.print(">>> ");
        int orderId = Integer.parseInt(sc.nextLine());

        for(Order order : orders){
            if(order.getId()==orderId && order.getStatus()==Order.OrderStatus.READY){
                order.setStatus(Order.OrderStatus.COMPLETED);
                System.out.println("Order ID " + orderId + " collected");
                return;
            }
        }

        System.out.println("Order with ID " + orderId + " not found or not ready for pickup");
    }

    void viewReadyToPick(){
        // ??
        // for(Order order : branch.order()){
        //     if(order is READY and the time frame is still OK){
        //         order.display();
        //     }
        // }
        if (orders.size() == 0) {
    		System.out.println("No orders is ready."); 
    		return;
    	}
    	for (Order order : orders) {
    		if(order.getStatus() == Order.OrderStatus.READY) {
    			LocalDateTime time_now = LocalDateTime.now();
    			LocalDateTime order_time = LocalDateTime.parse(order.getStartTime());
    			Duration duration = Duration.between(order_time, time_now);
    			if (duration.toMinutes() > 5) {
    				System.out.println("Order " + order.getId() + " is now cancelled");
    				order.setStatus(Order.OrderStatus.CANCELLED);
    			}
    			else {
    				System.out.println("Order " + order.getId() + " is ready to pickup");
    				order.display();
    			}
    		}
    	}
    }
        
    public boolean chooseAction(){
        Scanner sc = new Scanner(System.in);

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


    public ArrayList<Order> getOrders(){return orders;}
    public Branch getBranch(){return branch;}

}
