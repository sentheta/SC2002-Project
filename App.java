import java.io.*;
import java.util.*;

public class App{
	public static ArrayList<Employee> employees;
	public static ArrayList<Branch> branches;
	public static ArrayList<PayMethod> payMethods;
	
	private static String filename = "data";

	//================================================================//

	public static void main(String[] args){
		if(args.length > 1) filename = args[0];
		readFile();

		System.out.print(
			"Choose user:\n"
		+	"1. Customer\n"
		+	"2. Employee\n"
		);
		Scanner sc = new Scanner(System.in);
		int chosen = sc.nextInt();

		switch(chosen){
		case 1:
			Customer customer = new Customer();
			while(customer.chooseAction());
			break;
		case 2:
			String username = sc.next();
			for(Employee employee : employees) if(employee.getAcc().verify(username)){
				while(employee.chooseAction());
				break;
			}
			break;
		}

		writeFile();
		System.out.print(
			"Terminating program..."
		);
	}

	//================================================================//

	private static void readFile(){
		try{
			FileInputStream fin = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fin);
			employees = (ArrayList<Employee>) in.readObject();
			branches = (ArrayList<Branch>) in.readObject();
			payMethods = (ArrayList<PayMethod>) in.readObject();
			in.close();
		}catch(Exception e){
			// Make 1 user, that is admin only
			employees = new ArrayList<Employee>();
			branches = new ArrayList<Branch>();
			payMethods = new ArrayList<PayMethod>();

			employees.add(new Admin());
			//e.printStackTrace();
		}
	}

	private static void writeFile(){
		try{
			FileOutputStream fout = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(employees);
			out.writeObject(branches);
			out.writeObject(payMethods);
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//================================================================//

}