import java.util.*;

public class Manager extends Staff implements Person{

    //================================================================//
    //================================================================//

	Manager(String name,RoleType role,GenderType gender,int age,Account acc,Branch branch){
		super(name,role,gender,age,acc,branch);
	}

	public void displayStaff(){
		branch.displayStaff();
	}

	public void modifyMenu(){
		while(branch.getMenu().chooseAction());
	}
	
	public boolean chooseAction(){
		Scanner sc = new Scanner(System.in);

		System.out.println("Choose manager action");
		System.out.println("1. Display new order");
		System.out.println("2. View an order");
		System.out.println("3. Display staff");
		System.out.println("4. Modify menu");
		System.out.println("Other value to end manager session");

		try{
			int choice = sc.nextInt();
			switch(choice){
			case 1: displayNewOrders(); return true;
			case 2: viewOrder(); return true;
			case 3: displayStaff(); return true;
			case 4: modifyMenu(); return true;
			}
		}
		catch(Exception e){};

        System.out.println("Terminating manager session...");
		return false;
	}

    //================================================================//
    //================================================================//



}
