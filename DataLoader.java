package FOODIE;
import java.io.*;
import java.util.*;

class DataLoader{
	
	public static void readFile(String filename){		
		File f = new File(filename);

		App.employees = new ArrayList<Employee>();

		try{
			if(f.isFile()){
				Logger.log("Loading data from file");
				FileInputStream fin = new FileInputStream(filename);
				ObjectInputStream in = new ObjectInputStream(fin);
				
				App.branches = (ArrayList<Branch>) in.readObject();
				App.payMethods = (ArrayList<PayMethod>) in.readObject();
				App.employees.add((Admin) in.readObject());

				in.close();
			}
			else{
				throw new Exception("File doesn't exist");
			}
		}
		catch(Exception e){
			// Data is loaded according to the given Excel file scenario
			Logger.log("Loading default data");
			App.branches = new ArrayList<Branch>();
			App.payMethods = new ArrayList<PayMethod>();

			App.payMethods.add(new PayCash());
			App.payMethods.add(new PayDebit());
			App.payMethods.add(new PayNETS());
			App.payMethods.add(new PayMobile());

			App.employees.add(new Admin());

			// Branch(menu,name,staffs,managers,orders)
			Branch branchNTU = new Branch(new Menu(),"NTU",new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
			Branch branchJP = new Branch(new Menu(),"JP",new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
			Branch branchJE = new Branch(new Menu(),"JE",new ArrayList<>(),new ArrayList<>(),new ArrayList<>());

			// Staff(name,role,gender,age,acc,branch) 
			// Account(username)
			branchNTU.getStaffs().add(new Staff(
				"Kumar Blackmore",
				Employee.RoleType.STAFF,
				Employee.GenderType.MALE,
				32,
				new Account("kumarB"),
				branchNTU));
			branchNTU.getManagers().add(new Manager(
				"Alexei",
				Employee.RoleType.MANAGER,
				Employee.GenderType.MALE,
				25,
				new Account("Alexei"),
				branchNTU));
			branchJP.getManagers().add(new Manager(
				"Tom Chan",
				Employee.RoleType.MANAGER,
				Employee.GenderType.MALE,
				56,
				new Account("TomC"),
				branchJP));
			branchJP.getStaffs().add(new Staff(
				"Justin Loh",
				Employee.RoleType.STAFF,
				Employee.GenderType.MALE,
				49,
				new Account("JustinL"),
				branchJP));
			branchJE.getManagers().add(new Manager(
				"Alica Ang",
				Employee.RoleType.MANAGER,
				Employee.GenderType.FEMALE,
				27,
				new Account("AlicaA"),
				branchJE));
			branchJE.getStaffs().add(new Staff(
				"Mary Lee",
				Employee.RoleType.STAFF,
				Employee.GenderType.FEMALE,
				44,
				new Account("MaryL"),
				branchJE));

			//public Food(name,price,category)
			branchNTU.getMenu().getFoods().add(new Food(
				"Fries", 3.2, "side"
			));
			branchNTU.getMenu().getFoods().add(new Food(
				"3PC Set Meal", 9.9, "set meal"
			));
			branchJP.getMenu().getFoods().add(new Food(
				"Cajun Fish", 5.6, "burger"
			));
			branchJE.getMenu().getFoods().add(new Food(
				"Cole Slaw", 2.7, "side"
			));
			branchJE.getMenu().getFoods().add(new Food(
				"3PC Set Meal", 10.4, "set meal"
			));
			branchJP.getMenu().getFoods().add(new Food(
				"Chicken Nugget", 6.9, "side"
			));
			branchNTU.getMenu().getFoods().add(new Food(
				"Chicken Nugget", 6.6, "side"
			));
			branchJE.getMenu().getFoods().add(new Food(
				"Pepsi", 2.1, "drink"
			));

			App.branches.add(branchNTU);
			App.branches.add(branchJP);
			App.branches.add(branchJE);
		}

		// Merge all employees
		for(Branch branch : App.branches){
			App.employees.addAll(branch.getStaffs());
			App.employees.addAll(branch.getManagers());
		}

	}

	public static void writeFile(String filename){
		Logger.log("Writing to file");

		try{
			FileOutputStream fout = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(App.branches);
			out.writeObject(App.payMethods);
			out.writeObject(App.employees.get(0));
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
