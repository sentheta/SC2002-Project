package FOODIE;
import java.io.*;
import java.util.*;

class App implements Serializable{
	public static ArrayList<Employee> employees;	// non-admin employees
	public static ArrayList<Branch> branches;
	public static ArrayList<PayMethod> payMethods;

	private static final String filename = "data";

	//================================================================//
    //================================================================//

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		if(args.length>=1 && args[0].equals("verbose")){
			Logger.activate();
		}
		DataLoader.readFile(filename);

		while(true){
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

		DataLoader.writeFile(filename);
		System.out.println("Terminating program...");
	}

	public static void beCustomer() {
	    Scanner sc = new Scanner(System.in);
	    branches branches = new branches(); 

	    System.out.println("--Choose branch--");
	    try {
	        Branch chosenBranch = branches.chooseBranch();

	        if (chosenBranch != null) {
	            Customer customer = new Customer(chosenBranch);
	            while (customer.chooseAction()) {
	            }
	        } else {
	            System.out.println("Branch not found");
	        }
	    } catch (Exception e) {
	        System.out.println("Branch not found");
	    }
	}


	public static void beEmployee(){
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter username:");
		System.out.print(">>> ");

		try{
			String username = sc.nextLine();

			boolean found = false;
			for(Employee employee : employees) if(employee.getUsername().equals(username)){
				found = true;
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
