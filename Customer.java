import java.util.*;


public class Customer implements Person{
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
        orders.add(order);

        // make while loop to force user enter a valid itemNumber and qty
        while(true){
            try{
                System.out.println("Item number (or an invalid value to finish order): ");
                System.out.println(">>> ");
                int itemNumber = Integer.parseInt(sc.nextLine());
                if(itemNumber<1 || itemNumber>=menu.getFoods().size()) break;

                System.out.println("Quantity (or an invalid value to finish order): ");
                System.out.println(">>> ");
                int quantity = Integer.parseInt(sc.nextLine());
                if(quantity<0) break;

                Food selectedFood = menu.getFoods().get(itemNumber-1); //assume the choice start from 1 
                order.addItem(selectedFood, quantity);

            }catch(Exception e){
                break;
            }
        }
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
            PayMethod.choosePaymentMethod();
            PayMethod.pay(totalPrice);
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
                order.setStatus(Order.OrderStatus.COMPLETED); //use same name in enum in order class
                System.out.println("Order ID " + orderId + " collected");
                return;
            }
        }

        System.out.println("Order with ID " + orderId + " not found or not ready for pickup");
    }
        
    public boolean chooseAction(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose customer action:");
        System.out.println("1. Make an order");
        System.out.println("2. Check an order");
        System.out.println("3. Make a payment");
        System.out.println("4. Collect an order");
        System.out.println("Other values to end customer session");

        try{
            System.out.print(">>> ");
            int choice = Integer.parseInt(sc.nextLine());
            switch(choice){
            case 1: makeOrder(); return true;
            case 2: checkOrder(); return true;
            case 3: payOrder(); return true;
            case 4: collectOrder(); return true;
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