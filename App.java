import java.io.*;
import java.util.*;

public class App{
	public static ArrayList<Employee> employees;
	public static ArrayList<Branch> branches;
	public static ArrayList<PayMethod> payMethods;

	private static final String filename = "data";

	//================================================================//
    //================================================================//

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		DataLoader.readFile(filename);

		System.out.println("Choose user:");
		System.out.println("1. Customer");
		System.out.println("2. Employee");
		System.out.println("Other value to end program");

		try{
			int chosen = sc.nextInt();
			switch(chosen){
			case 1:
				System.out.println("Enter branch name:");
				String branchName = sc.next();

				boolean found = false;
				for(Branch branch : branches) if(branch.getName() == branchName){
					Customer customer = new Customer(branch);
					found = true;
					while(customer.chooseAction());
					break;
				}

				if(!found) System.out.println("Invalid branch");
				break;
			case 2:
				String username = sc.next();
				for(Employee employee : employees) if(employee.getAcc().verify(username)){
					while(employee.chooseAction());
					break;
				}
				break;
			}
		}
		catch(Exception e){}

		DataLoader.writeFile(filename);
		System.out.print("Terminating program...");
	}

	//================================================================//
    //================================================================//

	public static ArrayList<Employee> getEmployees(){return employees;}
	// public static void setEmployees(ArrayList<Employee> tmp){employees = tmp;}
	public static ArrayList<Branch> getBranches(){return branches;}
	// public static void setBranches(ArrayList<Branch> tmp){branches = tmp;}
	public static ArrayList<PayMethod> getPayMethods(){return payMethods;}
	// public static void setPayMethods(ArrayList<PayMethod> tmp){payMethods = tmp;}

}