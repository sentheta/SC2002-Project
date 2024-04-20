import java.util.*;

public class Manager extends Staff implements Person{

    //================================================================//
    //================================================================//

	Manager(String name,RoleType role,GenderType gender,int age,Account acc,Branch branch){
		super(name,role,gender,age,acc,branch);
		Logger.log("Creating new manager");
	}

	public void modifyMenu(){
		while(branch.getMenu().chooseAction());
	}
	
	public boolean chooseAction(){
		Scanner sc = new Scanner(System.in);

		System.out.println("Choose manager action:");
		System.out.println("1. Display new order");
		System.out.println("2. View an order");
		System.out.println("3. Display staff");
		System.out.println("4. Modify menu");
		System.out.println("Other value to end manager session");

		try{
        	System.out.print(">>> ");
			int choice = Integer.parseInt(sc.nextLine());
			switch(choice){
			case 1: displayNewOrders(); return true;
			case 2: viewOrder(); return true;
			case 3: branch.displayStaff(); return true;
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
