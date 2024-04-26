package FOODIE;
import java.util.*;
import java.io.Serializable;
/**
 * Represents an admin user which extends from the Employee class.
 * Admins have more power than other derived classes of Employee class, being able to modifying employee details, payment methods and branch details
 * 
 */
public class Admin extends Employee implements IActionable, Serializable{
	
    //================================================================//
    //================================================================//
    
	/**
	* Default constructor for Admin class
	* Creates new Admin object with name, role, gender, age and Account. 
	*/
	Admin(){
		super("admin", Employee.RoleType.ADMIN, Employee.GenderType.MALE, 0, new Account("admin"));
	}
	/**
	 * Allows admin to choose from a pre-determined number of options that are available to an admin.
	 * The method first presents all available functionality, before allowing user to choose an option, 
	 *  executing the method based on the user's input.
	 *  @return true if method successfully executes, false if an invalid choice is input. 
	 */
	public boolean chooseAction(){
		Scanner sc = new Scanner(System.in);
		// Printing of available options to user.
		System.out.println();
		System.out.println("--Choose admin action--");
		System.out.println("1. Add staff");
		System.out.println("2. Remove employee");
		System.out.println("3. Display employee list");
		System.out.println("4. Promote staff to manager");
		System.out.println("5. Transfer employee to other branch");
		System.out.println("6. Add payment method");
		System.out.println("7. Remove payment method");
		System.out.println("8. Open branch");
		System.out.println("9. Close branch");
		System.out.println("10. Change password");

	    System.out.print(">>> ");

	    try{
			int choice = Integer.parseInt(sc.nextLine());
		    	// depending on user choice, different methods will run.
			switch(choice){
			case 1: addStaff(); return true;
			case 2: removeEmployee(); return true;
			case 3: displayEmployees(); return true;
			case 4: promoteStaff(); return true;
			case 5: transferEmployee(); return true;
			case 6: addPayMethod(); return true;
			case 7: removePayMethod(); return true;
			case 8: openBranch(); return true;
			case 9: closeBranch(); return true;
			case 10: getAcc().changePassword(); return true;
			}
	    }
	    catch(Exception e){}
	// return false if invalid input is entered.
        System.out.println("Terminating admin session...");
		return false;
	}
/** 
 * Adds new staff to database
 * Method asks for new staff's name, role, gender, age, account username, and branch 
 * Method then creates a new Staff object and adds it into main employees ArrayList in App class, and the ArrayList in 
 * Branch class. 
 */
    public void addStaff(){
		Scanner sc = new Scanner(System.in);

		// Staff(String name,RoleType role,GenderType gender,int age,Account acc,Branch branch)
    	String name;
    	GenderType gender;
    	int age;
    	Account acc;
    	Branch branch = null;

		// Get infos
	    String tmp;
    	try{
	    	System.out.println("Enter new staff's name");
	    	System.out.print(">>> ");
	    	name = sc.nextLine();

	    	System.out.println("Enter new staff's gender (M for Male, other value for Female)");
	    	System.out.print(">>> ");
	    	tmp = sc.nextLine();
	    	if(tmp.equals("M")) gender = Employee.GenderType.MALE;
	    	else gender = Employee.GenderType.FEMALE;

	    	System.out.println("Enter new staff's age");
	    	System.out.print(">>> ");
	    	age = Integer.parseInt(sc.nextLine());

	    	System.out.println("Enter new staff's username");
	    	System.out.print(">>> ");
	    	tmp = sc.nextLine();
	    	acc = new Account(tmp);

	    	System.out.println("Enter new staff's branch name");
	    	System.out.print(">>> ");
	    	tmp = sc.nextLine();
	    	for(Branch b : App.branches) if(b.getName().equals(tmp)){
	    		branch = b;
	    		break;
	    	}
	    	if(branch == null){
	    		System.out.println("Invalid branch. New staff cancelled.");
	    		return;
	    	}
    	}
    	catch(Exception e){
    		e.printStackTrace(); return;
    	}

    	// Add staff
    	Staff staff = new Staff(name,role,gender,age,acc,branch);
    	App.employees.add(staff);
    	branch.getStaffs().add(staff);
	}

	/**
	 * Removes Employee from database. 
	 * Method asks for employee's username for verification first, before removing the Employee from App employees ArrayList.
	 * and the Branch's ArrayList.
	 */
    public void removeEmployee(){
		Scanner sc = new Scanner(System.in);

		// Get info
		String username;
		try{
			System.out.println("Enter employee's username");
	    	System.out.print(">>> ");
	    	username = sc.nextLine();
	    	for(char c : username.toCharArray()) if(c == ' '){
	    		throw new Exception("Username should not have space");
	    	}
		}
		catch(Exception e){
			e.printStackTrace(); return;
		}
    	if(username.equals("admin")){
    		System.out.println("Admin cannot be removed"); return;
    	}

		// Remove employee
		// index 0 is skipped for admin		
		for(int i=1; i<App.employees.size(); i++){
			Staff staff = (Staff)App.employees.get(i);
			if(!staff.getUsername().equals(username)) continue;
			// Removing of staff from App and Branch employee ArrayList. 
			App.employees.remove(staff);
			if(staff.role == Employee.RoleType.MANAGER) staff.getBranch().getManagers().remove(staff);
			else staff.getBranch().getStaffs().remove(staff);
	    	System.out.println("Employee removed");
    	}
		System.out.println("Employee not found");
	}

	/**
     * Allows Admin to filter Employees by certain categories such as Branch name, Role, Gender, Age. 
     */
    public void displayEmployees(){
		Scanner sc = new Scanner(System.in);

    	// ask filter
    	// if * is entered, then we should not filter by that category
    	String branchName;
    	String role;
    	String gender;
    	String age;
    	try{
	    	System.out.println("Enter branch name: (or *)");
	    	System.out.print(">>> ");
	    	branchName = sc.nextLine();

	    	System.out.println("Enter role: (or *)");
	    	System.out.print(">>> ");
	    	role = sc.nextLine();

	    	System.out.println("Enter gender: (or *)");
	    	System.out.print(">>> ");
	    	gender = sc.nextLine();

	    	System.out.println("Enter age: (or *)");
	    	System.out.print(">>> ");
	    	age = sc.nextLine();
    	}
    	catch(Exception e){
	    	System.out.println("Aborted.");
	    	return;
    	}

		int i = 1;
		for(Employee employee : App.employees)
		if(branchName.equals("*")
			|| (employee instanceof Staff && ((Staff)employee).getBranch().getName().equals(branchName))
		)
		if(role.equals("*")
			|| (employee.getRole().equals(Employee.RoleType.ADMIN) && role.toUpperCase().equals("ADMIN"))
			|| (employee.getRole().equals(Employee.RoleType.MANAGER) && role.toUpperCase().equals("MANAGER"))
			|| (employee.getRole().equals(Employee.RoleType.STAFF) && role.toUpperCase().equals("STAFF"))
		)
		if(gender.equals("*")
			|| (employee.getGender().equals(Employee.GenderType.MALE) && gender.toUpperCase().equals("MALE"))
			|| (employee.getGender().equals(Employee.GenderType.FEMALE) && gender.toUpperCase().equals("FEMALE"))
		)
		if(age.equals("*")
			|| (employee.getAge() == Integer.parseInt(age))
		)
		{
			System.out.println(i + ". " + employee.getName());
			i++;
		}
	}

/** 
 * Promotes staff to a manager role. 
 * This method prompts user for username of staff member to be promoted
 * If valid employee is found (cannot be admin), check that the employee is not a manager already.
 * The method then checks that the quota for managers of a branch is not exceeded before promoting staff.
 * Method then removes staff from App employees ArrayList, creating a new Manager object then adding this new object back.
 */
    public void promoteStaff(){
		Scanner sc = new Scanner(System.in);

		// Get info
		String username;
		try{
			System.out.println("Enter employee's username");
	    	System.out.print(">>> ");
	    	username = sc.nextLine();
		}
		catch(Exception e){
			e.printStackTrace(); return;
		}
    	if(username.equals("admin")){
    		System.out.println("Admin cannot be promoted"); return;
    	}

		// Find employee
    	for(Employee employee : App.employees) if(employee.getUsername().equals(username)){
		// Check if employee is manager
	    	if(employee.getRole().equals(Employee.RoleType.MANAGER)){
	    		System.out.println("Employee is already a manager"); return;
	    	}
	        
	        Staff staff = (Staff)employee;
	    	Branch branch = staff.getBranch();
		// checking if branch manager quota is hit
	        if(branch.getQuota() == branch.getManagers().size()) {
	            System.out.println("Manager quota exceeded for the branch."); return;
	        }
		// remove staff from both branch and main dataset. 
	        branch.getStaffs().remove(staff);
	        App.employees.remove(staff);
		// Creating new Manager object, adding it back to branch and main dataset. 
			// Manager(String name,RoleType role,GenderType gender,int age,Account acc,Branch branch)
	        Manager manager = new Manager(staff.getName(), Employee.RoleType.MANAGER, staff.getGender(), staff.getAge(), staff.getAcc(), staff.getBranch());
	        branch.getManagers().add(manager);
	        App.employees.add(manager);
    	}
    	System.out.println("Employee not found");
    }

    /**
 * Transfers an employee from one branch to another.
 * Method asks for employee's username and their branch name to be transferred to. 
 * Iterates through employees ArrayList in App class to find the employee.
 * If employee is found, we remove the employee from their current branch and add them to their new target branch. 
 */
    public void transferEmployee(){
		Scanner sc = new Scanner(System.in);

		// Get info
		String username, branchName;
		try{
			System.out.println("Enter employee's username");
	    	System.out.print(">>> ");
	    	username = sc.nextLine();
    		
    		System.out.println("Enter target branch name");
	    	System.out.print(">>> ");
	    	branchName = sc.nextLine();
		}
		catch(Exception e){
			e.printStackTrace(); return;
		}

		// Find employee and branch
    	Staff staff = null;
    	for(Employee emp : App.employees) {
		if(emp.getUsername().equals(username)){
    			staff = (Staff)emp;
		}
    	}
    	Branch newBranch = null;
    	for(Branch b : App.branches) {
		if(b.getName().equals(branchName)){
    			newBranch = b;
		}
    	}
    	if(username.equals("admin")){
    		System.out.println("Admin cannot be transferred"); return;
    	}
    	if(staff==null || newBranch==null){
    		System.out.println("Employee or branch not found"); return;
    	}


    	// Transfer staff
    	if(staff.role.equals(Employee.RoleType.MANAGER)){
		// Checking for quota of branch.
    		if(newBranch.getQuota() == newBranch.getManagers().size()){
            	System.out.println("Manager quota exceeded for the target branch"); return;
    		}
		// Removing managers from current branch and adding to new branch.
    		staff.branch.getManagers().remove((Manager)staff);
	        staff.branch = newBranch;        
	        staff.branch.getManagers().add((Manager)staff);
    	}
    	else{
		// Removing staff from current branch and adding to new branch. 
	        staff.branch.getStaffs().remove(staff);
	        staff.branch = newBranch;        
	        staff.branch.getStaffs().add(staff);
    	}

    	System.out.println("Employee transferred successfully");
    }
 
/**
 * Method allows user to add a new payment method to the App payMeythods ArrayList. 
 * Method asks for new payment method name, then iterates through App payMethods ArrayList to see if it exists.
 * If it already exists, method will return. Else, it will add new PaymentMethod object
 */
    public void addPayMethod(){
		Scanner sc = new Scanner(System.in);

		// Get info
		String name;
		try{
			System.out.println("Enter payment method's name");
	    	System.out.print(">>> ");
	    	name = sc.nextLine();
		}
		catch(Exception e){
			e.printStackTrace(); return;
		}
		// Checking for existing payment method
		for(PayMethod pm : App.payMethods) if(pm.getName().equals(name)){
        	System.out.println("Payment method already exists");	
			return;
		}
	    // Add new payment method if not added. 
		App.payMethods.add(new PayMethod(name));
    }

/**
    * Method allows admin to remove payment method from the App payMethods ArrayList. 
    * Method asks for payment method name, then iterates through App payMethods ArrayList to see if it exists.
    * If it exists, method will remove PaymentMethod object.
    */
    public void removePayMethod(){
		Scanner sc = new Scanner(System.in);

		// Get info
		String name;
		try{
			System.out.println("Enter payment method's name");
	    	System.out.print(">>> ");
	    	name = sc.nextLine();
		}
		catch(Exception e){
			e.printStackTrace(); return;
		}

		// Remove, checking for existing payment method.
		for(PayMethod pm : App.payMethods) if(pm.getName().equals(name)){
			App.payMethods.remove(pm);
    		System.out.println("Payment method removed");
    		return;
		}
    	System.out.println("Payment method not found");	
    }

/** 
 * Opens a new branch for the organization
 * Method asks for new branch name, before creating a new Branch object with the name, default Menu object, staffs, managers and orders ArrayList.
 * Method then adds this new Branch object to App branches ArrayList. 
 * 
 */
    public void openBranch(){
   		Scanner sc = new Scanner(System.in);

		String name;
		try{
			System.out.println("Enter new branch name");
	    	System.out.print(">>> ");
	    	name = sc.nextLine();
		}
		catch(Exception e){
			e.printStackTrace(); return;
		}

		for(Branch b : App.branches) if(b.getName().equals(name)){
			System.out.println("Branch already exists"); return;
		}
		// Branch(Menu menu, String name, ArrayList<Staff> staffs, ArrayList<Manager> managers, ArrayList<Order> orders)
    	Branch branch = new Branch(new Menu(),name,new ArrayList<>(),new ArrayList<>(), new ArrayList<>());
    	App.branches.add(branch);
		System.out.println("Branch opened");
    }

    /** 
     * Closes a branch for the organization
     * Method asks for new branch name, before verifying that such a branch exists.
     * Method then removes this Branch object from App branches ArrayList. 
     */
    public void closeBranch(){
   		Scanner sc = new Scanner(System.in);

		String name;
		try{
			// Getting branch name.
			System.out.println("Enter new branch name");
	    	System.out.print(">>> ");
	    	name = sc.nextLine();
		}
		catch(Exception e){
			e.printStackTrace(); return;
		}
		// Checking if branch exists
		for(Branch branch : App.branches) if(branch.getName().equals(name)){
    		App.branches.remove(branch);
			System.out.println("Branch closed");
    		return;
		}
		System.out.println("Branch not found"); return;
	}
	

    //================================================================//
    //================================================================//



}
  
