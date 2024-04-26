package FOODIE;
import java.util.*;
import java.io.Serializable;
/**
 * This class represents a Staff of a branch. 
 * A staff has information such as name, role (Staff), gender, age, Account object and Branch. 
 */
public class Staff extends Employee implements IActionable, Serializable{
	//Branch that the staff belongs to.
	Branch branch;

    //================================================================//
    //================================================================//
	/**
	 * Constructor of Staff object, creating a Staff object with the specified attributes.
	 * @param name Name of Staff
	 * @param role Role of Staff 
	 * @param gender Gender of Staff
	 * @param age Age of Staff
	 * @param acc Account object of Staff
	 * @param branch Branch of Staff
	 */
	Staff(String name,RoleType role,GenderType gender,int age,Account acc,Branch branch){
		super(name,role,gender,age,acc);
		this.branch = branch;
	}
	/**
	 * Staff is able to display new orders to process, using the Order ArrayList in Branch object. 
	 * @see Branch#getOrders()
	 */
	public void displayNewOrders(){
		boolean found = false;
		// iterates over Branch order list.
		for(Order order : branch.getOrders()){
			if(order.getStatus() == Order.OrderStatus.NEW){
				found = true;
				// display orders.
				order.display();
			}
		}
		if(!found){
			System.out.println("No new orders");
		}
	}
	/**
	 * Staff is able to view a specific order based on Order ID.
	 * Method first asks for order ID. If valid, method will print out details of the order. 
	 */
	public void viewOrder(){
		Scanner sc = new Scanner(System.in);
		
		int id;
		// Getting order ID. 
		System.out.println("Enter order ID");
        System.out.print(">>> ");
		id = Integer.parseInt(sc.nextLine());
		// Iterating over orders to find the order, displaying it if found. 
		for(Order order : branch.getOrders()) if(order.getId() == id){
			order.display();
			return;
		}
		System.out.println("Order not found");
	}
	/** 
	 * Staff is able to process the order, preparing it for pickup.
	 * Method changes order status of an order from NEW to READY. 
	 */
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
	/**
	 * Prints out the available functionality available to a staff, such as displaying and processing orders.
	 * Allows user to choose which action they want to execute
	 * @return true if action executed, else false. 
	 */
	public boolean chooseAction(){
		Scanner sc = new Scanner(System.in);
		// Printing of available actions. 
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

	/** Gets Branch object that Staff object belongs to. */
	public Branch getBranch(){return branch;}
	/** Sets Branch object of a Staff to a new one, in case of a transfer to another branch. */
	public void setBranch(Branch tmp){this.branch = tmp;}
}
