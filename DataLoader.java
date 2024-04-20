import java.io.*;
import java.util.*;

public class DataLoader{
	public static void readFile(String filename){		
		File f = new File(filename);

		try{
			if(f.isFile()){
				Logger.log("Loading data from file");
				FileInputStream fin = new FileInputStream(filename);
				ObjectInputStream in = new ObjectInputStream(fin);
				App.employees = (ArrayList<Employee>) in.readObject();
				App.branches = (ArrayList<Branch>) in.readObject();
				App.payMethods = (ArrayList<PayMethod>) in.readObject();
				in.close();
				return;
			}
		}
		catch(Exception e){}

		Logger.log("Loading default data");
		App.employees = new ArrayList<Employee>();
		App.branches = new ArrayList<Branch>();
		App.payMethods = new ArrayList<PayMethod>();
		App.employees.add(new Admin());
	}

	public static void writeFile(String filename){
		try{
			FileOutputStream fout = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(App.employees);
			out.writeObject(App.branches);
			out.writeObject(App.payMethods);
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}