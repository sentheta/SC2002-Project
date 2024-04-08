import java.util.Scanner;
public class Manager extends Staff implements ManagerDoable, Person{
	Scanner sc = new Scanner(System.in);
	public Manager(String role, String gender, String name, int age, Account account, String branch) {
		super(role, gender, name, age, account, branch);
	}
	public void displayStaff(Branch branch) {
		branch.displayStaff();
	}
	public void modifyMenu(Branch branch) {
		int choice;
		System.out.println("1. Add food 2. Delete food 3. Modify price. 4. Modify availability");
		choice = sc.nextInt();
		do {
		switch (choice) {
		case 1: 
			branch.getMenu().addFood();
			break;
		case 2:
			branch.getMenu().delFood();
			break;
		case 3:
			branch.getMenu().updatePrice();
			break;
		case 4:
			branch.getMenu().updateAvail();
			break;
		default:
			System.out.println("Invalid choice");
		}
		}while(choice >= 1 && choice <= 4);
	}
	
	public void chooseAction() {
		int choice;
		choice = sc.nextInt();
		do {
			int i;
			Branch branch;
			for (i=0; i<App.branch.length(); i++) {
				if (getBranch() == App.branch[i].getName()) {
					branch = App.branch[i];
				}
			if (choice < 1 || choice > 2) {
				break;
			}
			else if (choice == 1) {
				displayStaff(branch);
			}
			else {
				modifyMenu(branch);
			}
		}while (choice==1 || choice==2);
	}
}
