package FOODIE;
import java.util.*;
import java.io.Serializable;

class Admin extends Employee implements IActionable, Serializable{
	
    //================================================================//
    //================================================================//
    

	Admin(){
		super("admin", Employee.RoleType.ADMIN, Employee.GenderType.MALE, 0, new Account("admin"));
	}

	public boolean chooseAction(){
		Scanner sc = new Scanner(System.in);

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

        System.out.println("Terminating admin session...");
		return false;
	}

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
	    	tmp = sc.nextLine().toLowerCase();
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

	
    public void removeEmployee(){
		Scanner sc = new Scanner(System.in);

		// Get info
		String username;
		try{
			System.out.println("Enter employee's username");
	    	System.out.print(">>> ");
	    	username = sc.nextLine().toLowerCase();
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

			App.employees.remove(staff);
			if(staff.role == Employee.RoleType.MANAGER) staff.getBranch().getManagers().remove(staff);
			else staff.getBranch().getStaffs().remove(staff);
	    	System.out.println("Employee removed");
    	}
		System.out.println("Employee not found");
	}


    public void displayEmployees(){
    	// ask filter
    	// if * is entered, then we should not filter by that category
    	// boolean byBranchName, byRole, byGender, byAge;
    	// String branchName;
    	// Employee.RoleType role;
    	// Employee.Gender gender;
    	// int age;

		// int i = 1;
		// for(Employee employee : App.employees)
		// if(!byBranchName || (employee instanceof Staff && ((Staff)employee).getBranch().getName().equals(branchName)))
		// if(!byRole || )
		// {
		// 	System.out.println(i + ". " + employee.getName());
		// 	i++;
		// }
	}


    public void promoteStaff(){
		Scanner sc = new Scanner(System.in);

		// Get info
		String username;
		try{
			System.out.println("Enter employee's username");
	    	System.out.print(">>> ");
	    	username = sc.nextLine().toLowerCase();
		}
		catch(Exception e){
			e.printStackTrace(); return;
		}
    	if(username.equals("admin")){
    		System.out.println("Admin cannot be promoted"); return;
    	}

		// Find employee
    	for(Employee employee : App.employees) if(employee.getUsername().equals(username)){
	    	if(employee.getRole().equals(Employee.RoleType.MANAGER)){
	    		System.out.println("Employee is already a manager"); return;
	    	}
	        
	        Staff staff = (Staff)employee;
	    	Branch branch = staff.getBranch();

	        if(branch.getQuota() == branch.getManagers().size()) {
	            System.out.println("Manager quota exceeded for the branch."); return;
	        }

	        branch.getStaffs().remove(staff);
	        App.employees.remove(staff);

			// Manager(String name,RoleType role,GenderType gender,int age,Account acc,Branch branch)
	        Manager manager = new Manager(staff.getName(), Employee.RoleType.MANAGER, staff.getGender(), staff.getAge(), staff.getAcc(), staff.getBranch());
	        branch.getManagers().add(manager);
	        App.employees.add(manager);
    	}
    	System.out.println("Employee not found");
    }


    public void transferEmployee(){
		Scanner sc = new Scanner(System.in);

		// Get info
		String username, branchName;
		try{
			System.out.println("Enter employee's username");
	    	System.out.print(">>> ");
	    	username = sc.nextLine().toLowerCase();
    		
    		System.out.println("Enter target branch name");
	    	System.out.print(">>> ");
	    	branchName = sc.nextLine();
		}
		catch(Exception e){
			e.printStackTrace(); return;
		}

		// Find employee and branch
    	Staff staff = null;
    	for(Employee emp : App.employees) if(emp.getUsername().equals(username)){
    		staff = (Staff)emp;
    	}
    	Branch newBranch = null;
    	for(Branch b : App.branches) if(b.getName().equals(branchName)){
    		newBranch = b; return;
    	}
    	if(username.equals("admin")){
    		System.out.println("Admin cannot be transferred"); return;
    	}
    	if(staff==null || newBranch==null){
    		System.out.println("Employee or branch not found"); return;
    	}


    	// Transfer staff
    	if(staff.role.equals(Employee.RoleType.MANAGER)){
    		if(newBranch.getQuota() == newBranch.getManagers().size()){
            	System.out.println("Manager quota exceeded for the target branch"); return;
    		}
    		staff.branch.getManagers().remove((Manager)staff);
	        staff.branch = newBranch;        
	        staff.branch.getManagers().add((Manager)staff);
    	}
    	else{
	        staff.branch.getStaffs().remove(staff);
	        staff.branch = newBranch;        
	        staff.branch.getStaffs().add(staff);
    	}

    	System.out.println("Employee transferred successfully");
    }
 

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

		for(PayMethod pm : App.payMethods) if(pm.getName().equals(name)){
        	System.out.println("Payment method already exists");	
			return;
		}
		App.payMethods.add(new PayMethod(name));
    }


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

		// Remove
		for(PayMethod pm : App.payMethods) if(pm.getName().equals(name)){
			App.payMethods.remove(pm);
    		System.out.println("Payment method removed");
    		return;
		}
    	System.out.println("Payment method not found");	
    }


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


    public void closeBranch(){
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
  
