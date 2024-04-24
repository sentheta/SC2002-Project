package FOODIE;
import java.util.*;
import java.io.Serializable;

class Staff extends Employee implements IActionable, Serializable{
	Branch branch;

    //================================================================//
    //================================================================//

	Staff(String name,RoleType role,GenderType gender,int age,Account acc,Branch branch){
		super(name,role,gender,age,acc);
		this.branch = branch;
	}

	public void displayNewOrders(){
		boolean found = false;
		for(Order order : branch.getOrders()){
			if(order.getStatus() == Order.OrderStatus.NEW){
				found = true;
				order.display();
			}
		}
		if(!found){
			System.out.println("No new orders");
		}
	}

	public void viewOrder(){
		Scanner sc = new Scanner(System.in);
		
		int id;
		System.out.println("Enter order ID");
        System.out.print(">>> ");
		id = Integer.parseInt(sc.nextLine());

		for(Order order : branch.getOrders()) if(order.getId() == id){
			order.display();
			return;
		}
		System.out.println("Order not found");
	}

	public void processOrder(){
		Scanner sc = new Scanner(System.in);

		// get info
		int id;
		System.out.println("Enter order ID");
        System.out.print(">>> ");
		id = Integer.parseInt(sc.nextLine());

		// update status
		for(Order order : branch.getOrders()) if(order.getId() == id){
			order.setStatus(Order.OrderStatus.READY);
			System.out.println("Order status updated");
			return;
		}
		System.out.println("Order not found");
	}

	public boolean chooseAction(){
		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println("--Choose Staff action--");
		System.out.println("1. Display new order");
		System.out.println("2. View an order");
		System.out.println("3. Process an order");
		System.out.println("4. Change password");
		System.out.println("Other values to end staff session");

		try{
        	System.out.print(">>> ");
			int choice = Integer.parseInt(sc.nextLine());
			switch(choice){
			case 1: displayNewOrders(); return true;
			case 2: viewOrder(); return true;
			case 3: processOrder(); return true;
			case 4: acc.changePassword(); return true;
			}
		}
		catch(Exception e){}

        System.out.println("Terminating staff session...");
		return false;
	}

    //================================================================//
    //================================================================//


	public Branch getBranch(){return branch;}
	public void setBranch(Branch tmp){this.branch = tmp;}
}
