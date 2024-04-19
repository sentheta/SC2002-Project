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
		Scanner scanner = new Scanner(System.in);
		
		int order_id;
		order_id = scanner.nextInt();

		int i, j, k=0;
		for (i=0; i<branch.getOrders().size(); i++) {
			if (branch.getOrders().get(i).getId() == order_id) {
				for (j=0; j<branch.getOrders().get(i).getFood().size(); j++) {
					System.out.println(branch.getOrders().get(i).getFood().get(j) + " x" + branch.getOrders().get(i).getQuantities().get(j));
					return;
				}
			}
		}
		System.out.println("This order does not exist.");
	}

	public boolean chooseAction() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Choose Staff action:");
		System.out.println("1. Display new order");
		System.out.println("2. View an order");
		System.out.println("Other values to end staff session");

		int choice;
		try{
			choice = scanner.nextInt();
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
