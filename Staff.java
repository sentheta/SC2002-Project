import java.util.*;

public class Staff extends Employee{
	Branch branch;

    //================================================================//
    //================================================================//

	Staff(String name,RoleType role,GenderType gender,int age,Account acc,Branch branch){
		super(name,role,gender,age,acc);
		this.branch = branch;
	}

	// Display all NEW order
	public void displayNewOrders(){
		branch.displayNewOrders();
	}

	// View specific order
	public void viewOrder(){
		Scanner sc = new Scanner(System.in);
		
		int order_id;
        System.out.print(">>> ");
		order_id = sc.nextInt();

		int i, j, k=0;
		for(Order order : branch.getOrders()){
			if(order.getId() == order_id){
				order.display();
				return;
			}
		}
		System.out.println("Order not found");
	}

	public boolean chooseAction() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Choose Staff action:");
		System.out.println("1. Display new order");
		System.out.println("2. View an order");
		System.out.println("Other values to end staff session");

		try{
        	System.out.print(">>> ");
			int choice = sc.nextInt();
			switch(choice){
			case 1: displayNewOrders(); return true;
			case 2: viewOrder(); return true;
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
