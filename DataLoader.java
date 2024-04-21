package FOODIE;
import java.io.*;
import java.util.*;

class DataLoader{
	
	public static void readFile(String filename){		
		File f = new File(filename);

		try{
			if(f.isFile()){
				Logger.log("Loading data from file");
				FileInputStream fin = new FileInputStream(filename);
				ObjectInputStream in = new ObjectInputStream(fin);
				App.branches = (ArrayList<Branch>) in.readObject();
				App.payMethods = (ArrayList<PayMethod>) in.readObject();

				in.close();
			}
			else{
				throw new Exception("File doesn't exist");
			}
		}
		catch(Exception e){
			Logger.log("Loading default data");
			App.branches = new ArrayList<Branch>();
			App.payMethods = new ArrayList<PayMethod>();

			App.payMethods.add(new PayCash());
			App.payMethods.add(new PayDebit());
			App.payMethods.add(new PayNETS());
			App.payMethods.add(new PayMobile());
		}

		// Merge all employees
		App.employees = new ArrayList<Employee>();
		App.employees.add(new Admin());
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
			// out.writeObject(App.employees);
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
