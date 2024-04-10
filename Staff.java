import java.util.Scanner;
public class Staff extends Employee implements Person{
	Scanner scanner = new Scanner(System.in);
	private String branch;
	public Staff(String role, String gender, String name, int age, Account account, String branch) {
		super(role, gender, name, age, account);
		this.branch = branch;
	}
	public void displayOrder(Branch branch) {
		int i, j;
//		iterating over the orders array
		for (i=0; i<branch.getOrders.size(); i++) {
			if(branch.getOrders().get(i).getStatus() == "New") {
				System.out.println("" + branch.getOrders().get(i).getID() + ": ");
//				iterating over food array of each order
				for (j=0; j<branch.getOrders().get(i).getFood().size(); j++) {
					System.out.println(branch.getOrders().get(i).getFood().get(j) + " x" + branch.getOrders().get(i).getQuantity().get(j));
				}
			}
		}
	}
	public void viewOrder(Branch branch) {
		int order_id;
		order_id = scanner.nextInt();
		int i, j, k=0;
		for (i=0; i<branch.getOrders().size(); i++) {
			if (branch.getOrders().get(i).getID() == order_id) {
				for (j=0; j<branch.getOrders().get(i).getFood().size(); j++) {
					System.out.println(branch.getOrders().get(i).getFood().get(j) + " x" + branch.getOrders().get(i).getQuantity().get(j));
					return;
				}
			}
		}
		System.out.println("This order does not exist.");
	}
	public void chooseAction() {
		int choice;
		System.out.println("1. Display Order, 2. View Order");
		int i;
		Branch branch;
		for (i=0; i<App.branches.size(); i++) {
			if (getBranch() == App.branches.get(i).getName()) {
				branch = App.branches.get(i);
			}
		}
		choice = scanner.nextInt();
		if (choice < 1 || choice > 2) {
			break;
		}
		else if (choice == 1) {
			displayOrder(branch);
		}
		else {
			viewOrder(branch);
		}
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
}