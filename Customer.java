import java.util.*;


public class Customer implements Person{
    private Branch branch; 
    private ArrayList<Order> orders;
    private Menu menu;

    //================================================================//
    //================================================================//

    Customer(Branch branch){
        this.branch = branch;
        this.orders = branch.getOrders();
        this.menu = branch.getMenu();
    }

    public void makeOrder(){
        Scanner sc = new Scanner(System.in);

        menu.display();
        Order order = new Order(); // ??
        int itemNumber=0, quantity=0;
        // make while loop to force user enter a valid itemNumber and qty
        while (true){
            System.out.print("Item number (or an alphabet to finish order): ");
            String input = sc.nextLine();
            
            if (Character.isLetter(input.charAt(0))) 
                break;

            System.out.print("Quantity: ");
            quantity = sc.nextInt();

            try{
                itemNumber = Integer.parseInt(input);
                if(itemNumber<1 || itemNumber>=menu.getFoods().size()){  //getFoodItems() def in Menu? each num correspond to item 
                    System.out.println("Item not on the menu"); 
                }
            } catch (NumberFormatException e){
                System.out.println("Item number (or an alphabet to finish order): ");
                sc.nextLine(); 
            }
        }
        Food selectedFood = menu.getFoods().get(itemNumber-1); //assume the choice start from 1 
        order.addItem(selectedFood, quantity);

        orders.add(order);
    }

    public void checkOrder(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Order ID to check: ");
        int orderId = sc.nextInt();
        for (Order order : orders){
            if (order.getId() == orderId){
                System.out.println("Order ID: " + orderId);
                System.out.println("Status: " + order.getStatus());
                return;
            }
        }
        System.out.println("Order with ID " + orderId + " not found");
    }

    public void payOrder(){
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Order ID to pay: ");
        int orderId = sc.nextInt();

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
        System.out.print("Order ID to collect: ");
        int orderId = sc.nextInt();
        for (Order order : orders){
            if (order.getId() == orderId && order.getStatus() == Order.OrderStatus.READY){
                order.setStatus(Order.OrderStatus.COMPLETED); //use same name in enum in order class
                //need add setStatus() in order class maybe?
                System.out.println("Order ID " + orderId + " collected");
                return;
            }
        }
        System.out.println("Order with ID " + orderId + " not found or not ready for pickup");
    }
        
    public boolean chooseAction(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose customer action");
        System.out.println("1. Make an order");
        System.out.println("2. Check an order");
        System.out.println("3. Make a payment");
        System.out.println("4. Collect an order");
        System.out.println("Other values to end customer session");

        try{
            int choice = sc.nextInt();
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