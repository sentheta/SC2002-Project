import java.io.*;
import java.util.*;

public class App{
	public static Employee[] employees;
	public static Branch[] branches;
	public static PayMethod[] payMethods;
	private static String filename = "data";

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		if(args.length > 0){
			filename = args[0];
		}
		readFile();

		System.out.print(
			"Choose user:\n"
		+	"1. Customer\n"
		+	"2. Employee\n"
		);
		int chosen = sc.nextInt();

		switch(chosen){
		case 1:
			Customer customer = new Customer();
			while(customer.chooseAction());
			break;
		case 2:
			String username = sc.next();
			for(Employee employee : employees) if(employee.account.username == username){
				if(employee.account.verify()){

				}
				else{

				}
				break;
			}
			break;
		}




		writeFile();
	}	

	private static void readFile(){

	}
	private static void writeFile(){

	}
}