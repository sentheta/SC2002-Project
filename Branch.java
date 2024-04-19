import java.util.*;

public class Branch{

	private Menu menu;
	private String name;
	private ArrayList<Staff> staffs;
	private ArrayList<Manager> managers;
	private ArrayList<Order> orders;
	private int quota;

    //================================================================//
    //================================================================//


	public Branch(Menu menu, String name, ArrayList<Staff> staffs, ArrayList<Manager> managers, ArrayList<Order> orders) {
		this.menu = menu;
		this.name = name; 
		this.staffs = staffs;
		this.managers = managers;
		this.orders = orders;
		if (staffs.size() >= 1 && staffs.size() <= 4) {
			quota = 1;
		}
		else if (staffs.size() <= 8) {
			quota = 2;
		}
		else if (staffs.size() <= 15) {
			quota = 3;
		}
	}
	public void setQuota() {
		if (staffs.size() <= 4) {
			quota = 1;
		}
		else if (staffs.size() <= 8) {
			quota = 2;
		}
		else {
			quota = 3;
		}
	}
	
	public void addStaff(Staff staff) {
		if (staff instanceof Manager && managers.size() < quota) {
			managers.add((Manager) staff);
			System.out.println("Manager added");
		}
		else if (staff instanceof Manager && managers.size() >= quota) {
			System.out.println("Over the quota of managers.");
		}
		else {
			staffs.add(staff);
			setQuota();
			System.out.println("Staff added");
		}
	}
	
	public void removeStaff(Staff staff) {
		int i;
		if (staff instanceof Manager) {
			for (i=0; i<managers.size(); i++) {
				if (managers.get(i) == (Manager) staff){
					managers.remove(i);
					System.out.println("Manager removed");
				}
			}
		}
		else {
			for (i=0; i<staffs.size(); i++) {
				if (managers.get(i) == staff){
					managers.remove(i);
					setQuota();
					System.out.println("Staff removed");
				}
			}
		}
	}
	
	public void displayStaff() {
		int i;
		System.out.println("Staffs: ");
		for (i=0; i<staffs.size(); i++) {
			System.out.println(i+1 + ": " + staffs.get(i).getName());
		}
		System.out.println("Managers:");
		for (i=0; i<managers.size(); i++) {
			System.out.println(i+1 + ": " + managers.get(i).getName());
		}
	}

	public void displayNewOrders(){
		for(Order order : orders){
			if(order.getStatus() == Order.OrderStatus.NEW) {
				order.display();
			}
		}
	}

    //================================================================//
    //================================================================//


	public ArrayList<Order> getOrders() {return orders;}
	public void setOrders(ArrayList<Order> orders) {this.orders = orders;}

	public Menu getMenu() {return menu;}
	public void setMenu(Menu menu) {this.menu = menu;}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public ArrayList<Manager> getManagers() {return managers;}
	public void setManagers(ArrayList<Manager> managers) {this.managers = managers;}

	public ArrayList<Staff> getStaff() {return staffs;}
	public void setStaffs(ArrayList<Staff> staffs) {this.staffs = staffs;}

	public int getQuota() {return quota;}

}

