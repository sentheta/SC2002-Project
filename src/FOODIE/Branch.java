package FOODIE;
import java.util.*;
import java.io.Serializable;
/**
 *  Represents a branch of the restaurant.
 *  Each branch has a menu, name, ArrayList of Staff, Manager and Order.
 */
public class Branch implements Serializable{
	// Menu object of Branch, representing menu of branch
	private Menu menu;
	// Name of branch.
	private String name;
	// List of staff of branch
	private ArrayList<Staff> staffs;
	// List of managers of branch
	private ArrayList<Manager> managers;
	// List of orders of branch
	private ArrayList<Order> orders;

    //================================================================//
    //================================================================//

	/**
	 * Constructor for Branch class, constructing a new Branch object
	 * @param menu Menu of the branch
	 * @param name Name of the branch
	 * @param staffs ArrayList of Staff 
	 * @param managers ArrayList of Managers
	 * @param orders ArrayList of Order of a branch. 
	 */
	public Branch(Menu menu, String name, ArrayList<Staff> staffs, ArrayList<Manager> managers, ArrayList<Order> orders){
		Logger.log("Creating new branch");

		this.menu = menu;
		this.name = name; 
		this.staffs = staffs;
		this.managers = managers;
		this.orders = orders;
	}
	/**
	 * Calculates quota of managers in a branch
	 * Quota = 1 if staff less than or equal to 4, quota = 2 if staff less than or equal to 8, quota = 3 if staff less than or equal to 15
	 * @return returns quota of managers based on number of staff
	 */
	public int getQuota(){
		if(staffs.size()<=4) return 1;
		else if (staffs.size() <= 8) return 2;
		else return 3;
	}
	/**
	 * Adds a staff to the branch object. 
	 * If staff is a Manager, check that quota is not hit before adding to managers ArrayList. 
	 * If staff is a Staff, add the Staff object to the Branch staffs ArrayList
	 * @param staff Staff to be added to the branch. 
	 */
	public void addStaff(Staff staff){
		// Check that staff is a manager and quota is not exceeded.
		if (staff instanceof Manager && managers.size() < getQuota()){
			// adds manager to managers ArrayList
			managers.add((Manager) staff);
			System.out.println("Manager added");
		}
		else if (staff instanceof Manager && managers.size() >= getQuota()){
			// Printing error message if quota of mamagers is already exceeded.
			System.out.println("Over the quota of managers.");
		}
		else{
			// Adding of normal Staff to branch. 
			staffs.add(staff);
			System.out.println("Staff added");
		}
	}
	/**
	 * Removes a staff from the branch object. 
	 * Method first checks that staff is a Manager or Staff, before removing from relevant ArrayList. 
	 * @param staff Staff to be removed from branch.  
	 */
	public void removeStaff(Staff staff){
		int i;
		// Checking if staff is a Manager
		if (staff instanceof Manager){
			for (i=0; i<managers.size(); i++){
				if (managers.get(i).getName().equals(staff.getName())){
					// Removing manager from ArrayList. 
					managers.remove(i);
					System.out.println("Manager removed");
				}
			}
		}
		//Removing staff from staffs ArrayList
		else{
			for (i=0; i<staffs.size(); i++){
				if (staffs.get(i).getName().equals(staff.getName())){
					staffs.remove(i);
					System.out.println("Staff removed");
				}
			}
		}
	}
	/**
	 * Displays all the staff and all the managers of the branch
	 * Iterates through both staffs and managers ArrayList to print their names. 
	 */
	public void displayStaff(){
		int i;
		System.out.println("Staffs: ");
		for (i=0; i<staffs.size(); i++){
			System.out.println(i+1 + ": " + staffs.get(i).getName());
		}
		System.out.println("Managers:");
		for (i=0; i<managers.size(); i++){
			System.out.println(i+1 + ": " + managers.get(i).getName());
		}
	}

    //================================================================//
    //================================================================//

	/**
	 * Returns the ArrayList for all current orders of the branch.
	 * @return ArrayList of Order
	 */
	public ArrayList<Order> getOrders(){return orders;}
	/**
	 * Sets the ArrayList of current orders to a new one.
	 * @param orders ArrayList of new orders.
	 */
	public void setOrders(ArrayList<Order> orders){this.orders = orders;}
	/**
	 * Returns the Menu object of the branch
	 * @return Menu object
	 */
	public Menu getMenu(){return menu;}
	/**
	 * Sets the Menu object of the branch to a new one.
	 * @param menu new Menu object
	 */
	public void setMenu(Menu menu){this.menu = menu;}
	/**
	 * Returns name of the branch.
	 * @return String - name of branch
	 */
	public String getName(){return name;}
	/**
	 * Changes name of the branch.
	 * @param name New name of the branch
	 */
	public void setName(String name){this.name = name;}
	/**
	 * Returns ArrayList of the managers of the branch. 
	 * @return	ArrayList of Manager objects
	 */
	public ArrayList<Manager> getManagers(){return managers;}
	/**
	 * Sets the ArrayList of Manager objects to a new one.
	 * @param managers New ArrayList of Manager objects. 
	 */
	public void setManagers(ArrayList<Manager> managers){this.managers = managers;}
	/**
	 * Returns ArrayList of Staff objects of the branch.
	 * @return ArrayList of Staff. 
	 */
	public ArrayList<Staff> getStaffs(){return staffs;}
	/**
	 * Sets the ArrayList of Staff objects to a new one. 
	 * @param staffs New ArrayList of Staff objects.
	 */
	public void setStaffs(ArrayList<Staff> staffs){this.staffs = staffs;}

}

