import java.io.*;
import java.util.*;

public class App{
	public static Admin admin;
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

		System.out.println("Choose user:");
		System.out.println("1. Customer");
		System.out.println("2. Employee");
		System.out.println("Other value to end program");

		try{
			System.out.print(">>> ");
			int choice = Integer.parseInt(sc.nextLine()); 

			switch(choice){
			case 1:{
				System.out.println("Enter branch name:");
				System.out.print(">>> ");
				String branchName = sc.nextLine();
	
				boolean found = false;
				for(Branch branch : branches) if(branch.getName().equals(branchName)){
					Customer customer = new Customer(branch);
					found = true;
					while(customer.chooseAction());
					break;
				}

				if(!found) System.out.println("Branch not found");
				break;
			}
			case 2:{
				System.out.println("Enter username:");
				System.out.print(">>> ");
				String username = sc.nextLine();
	
				boolean found = false;
				for(Employee employee : employees) if(employee.getAcc().getUsername().equals(username)){
					found = true;
					if(employee.getAcc().verify(username)){
						while(employee.chooseAction());
						break;
					}
				}
				if(!found) System.out.println("User not found");

				break;
			}
			}
		}
		catch(Exception e){e.printStackTrace();}

		// DataLoader.writeFile(filename);
		System.out.println("Terminating program...");
	}

	//================================================================//
    //================================================================//

	// public static ArrayList<Employee> getEmployees(){return employees;}
	// public static void setEmployees(ArrayList<Employee> tmp){employees = tmp;}
	public static ArrayList<Branch> getBranches(){return branches;}
	// public static void setBranches(ArrayList<Branch> tmp){branches = tmp;}
	public static ArrayList<PayMethod> getPayMethods(){return payMethods;}
	// public static void setPayMethods(ArrayList<PayMethod> tmp){payMethods = tmp;}

}