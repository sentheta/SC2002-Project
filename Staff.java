import java.util.*;

public class Staff extends Employee{
	Scanner scanner = new Scanner(System.in);
	
	Branch branch;

	Staff(String name,RoleType role,GenderType gender,int age,Account acc,Branch branch){
		super(name,role,gender,age,acc);
		this.branch = branch;
	}

	// Display all order
	public void displayOrder(Branch branch) {
		int i, j;
//		iterating over the orders array
		for (i=0; i<branch.getOrders().size(); i++) {
			if(branch.getOrders().get(i).getStatus() == Order.OrderStatus.NEW) {
				System.out.println("" + branch.getOrders().get(i).getId() + ": ");
//				iterating over food array of each order
				for (j=0; j<branch.getOrders().get(i).getFood().size(); j++) {
					System.out.println(branch.getOrders().get(i).getFood().get(j) + " x" + branch.getOrders().get(i).getQuantities().get(j));
				}
			}
		}
	}

	// View specific order
	public void viewOrder(Branch branch) {
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
		int choice;
		System.out.println("1. Display Order, 2. View Order, other values to end session");

		choice = scanner.nextInt();
		if(choice == 1) {
			displayOrder(branch);
			return true;
		}
		else if(choice == 2){
			viewOrder(branch);
			return true;
		}
		return false;
	}

	public Branch getBranch(){return branch;}
	public void setBranch(Branch tmp){this.branch = tmp;}
}
