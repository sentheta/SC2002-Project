package scse_FOODIE;
import java.util.*;
import java.io.Serializable;
/**
 * This class represents a Manager of the restaurant, extending from the Staff class since a manager can do what a 
 * Staff can do but more (such as modifying menu)
 * @see Staff
 */
public class Manager extends Staff implements IActionable, Serializable{

    //================================================================//
    //================================================================//
	/**
	 * Constructor for Manager object, with specified attributes
	 * @param name Name of Manager
	 * @param role Role of Manager
	 * @param gender Gender of Manager
	 * @param age Age of Manager
	 * @param acc Account object of Manager
	 * @param branch Branch object of Manager
	 */
	Manager(String name,RoleType role,GenderType gender,int age,Account acc,Branch branch){
		super(name,role,gender,age,acc,branch);
		Logger.log("Creating new manager");
	}
	/**
	 * Allows manager to change the Menu of a Branch
	 * @see Menu
	 */
	public void modifyMenu(){
		while(branch.getMenu().chooseAction());
	}
	/**
	 * Allows users to choose from functions that a Manager can perform.
	 * @return true if action executed, else false.
	 */
	public boolean chooseAction(){
		Scanner sc = new Scanner(System.in);
		// choosing of options available
		System.out.println();
		System.out.println("--Choose manager action--");
		System.out.println("1. Display new order");
		System.out.println("2. View an order");
		System.out.println("3. Process an order");
		System.out.println("4. Display staff");
		System.out.println("5. Modify menu");
		System.out.println("6. Change password");
		System.out.println("Other value to end manager session");

		try{
        	System.out.print(">>> ");
			int choice = Integer.parseInt(sc.nextLine());
			switch(choice){
			case 1: displayNewOrders(); return true;
			case 2: viewOrder(); return true;
			case 3: processOrder(); return true;
			case 4: branch.displayStaff(); return true;
			case 5: modifyMenu(); return true;
			case 6: getAcc().changePassword(); return true;
			}
		}
		catch(Exception e){};

        System.out.println("Terminating manager session...");
		return false;
	}

    //================================================================//
    //================================================================//



}
