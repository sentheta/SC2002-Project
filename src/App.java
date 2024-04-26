package FOODIE;
import java.io.*;
import java.util.*;
/** 
 * This is the main class of the application. Manages user interaction and control flow of the program. 
 * Contains methods for handling both user and employee actions, and initializing database by loading and saving data from and to 
 * a file.
 */
public class App implements Serializable{
	/**
	 *  ArrayList containing non-admin employees
	 */
	public static ArrayList<Employee> employees;	// non-admin employees
	/**
	 *  ArrayList containing branches of the company
	 */
	public static ArrayList<Branch> branches;
	/**
	 *  ArrayList containing different payment methods
	 */
	public static ArrayList<PayMethod> payMethods;
	/**
	 * Constant String filename where we save and load our data from. 
	 */
	private static final String filename = "data";

	//================================================================//
    //================================================================//
	/*
	 * Main method of the application
	 * Controls the flow of the application by handling user input.
	 * @param args Command-line arguments. If "verbose" is provided as the first argument,
         *             verbose mode will be activated.
	 */
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		// Activation of Logger
		if(args.length>=1 && args[0].equals("verbose")){
			Logger.activate();
		}
		// Reading data from file.
		DataLoader.readFile(filename);
		// Iterates until user exits program
		while(true){
			System.out.println();
			System.out.println("--Choose user--");
			System.out.println("1. Customer");
			System.out.println("2. Employee");
			System.out.println("Other value to end program");

			try{
				System.out.print(">>> ");
				int choice = Integer.parseInt(sc.nextLine());

				switch(choice){
				case 1: beCustomer(); break;
				case 2: beEmployee(); break;
				default: throw new Exception();
				}
			}
			catch(Exception e){break;}
		}
		// Writes data to file
		DataLoader.writeFile(filename);
		System.out.println("Terminating program...");
	}
	/**
	 * Allows user to act as a customer. 
	 * Prompts user to enter a branch that they are at.
	 * @see Customer
	 */
	public static void beCustomer(){
		Scanner sc = new Scanner(System.in);

		System.out.println("Choose branch index");
		Logger.log("Branch size: " + branches.size());
		for(int i=0; i<branches.size(); i++){
			System.out.println((i+1) + ". " + branches.get(i).getName());
		}
		System.out.print(">>> ");

		try{
			int i = Integer.parseInt(sc.nextLine());

			Customer customer = new Customer(branches.get(i-1));
			while(customer.chooseAction());
			return;
		}
		catch(Exception e){};

		System.out.println("Branch not found");

	}
	/** 
	 * Allows user to act as an employee
	 * Prompts user to enter their username and password.
	 * If employee is verified, they are free to do what they are permitted to do.
	 * Else, they will not be able to log in to the system.
	 */
	public static void beEmployee(){
		Scanner sc = new Scanner(System.in);
		// Get their username
		System.out.println("Enter username:");
		System.out.print(">>> ");

		try{
			String username = sc.nextLine();

			boolean found = false;
			// iterates over employees ArrayList to find employee username.
			for(Employee employee : employees) if(employee.getUsername().equals(username)){
				found = true;
				// Verification of password. 
				if(employee.getAcc().verify(username)){
					while(employee.chooseAction());
					break;
				}
			}
			if(!found) System.out.println("User not found");
		}
		catch(Exception e){};
	}

	//================================================================//
    //================================================================//

	// public static ArrayList<Employee> getEmployees(){return employees;}
	// public static void setEmployees(ArrayList<Employee> tmp){employees = tmp;}
	// public static ArrayList<Branch> getBranches(){return branches;}
	// public static void setBranches(ArrayList<Branch> tmp){branches = tmp;}
	// public static ArrayList<PayMethod> getPayMethods(){return payMethods;}
	// public static void setPayMethods(ArrayList<PayMethod> tmp){payMethods = tmp;}

}
