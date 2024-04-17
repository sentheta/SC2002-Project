import java.util.*;
import java.util.stream.Collectors;


public class Admin extends Employee {
	public ArrayList<Employee> employees;
	public ArrayList<Branch> branches;	
	public ArrayList<PayMethod> payMethods;
	
    //================================================================//

	Admin(){
		super("admin", Employee.RoleType.ADMIN, Employee.GenderType.MALE, 0, new Account());
	}
	// Admin(String name,RoleType role,GenderType gender,int age,Account acc){
	// 	super(name,role,gender,age,acc);
	// }

	public boolean chooseAction(){
		// ??
		return false;
	}
	
	/*
    public void addStaff(Staff staff) {
    	
    	int choice;
		Scanner sc = new Scanner(System.in);
		// I will check this later, too much error message on this part
		// do {
		// 	System.out.println("Choose 1 to add a manager and 2 to add a staff ");
		// 	choice = sc.nextInt();
		
		// 	switch (choice) {
		// 		case 1: 
		// 			System.out.println("Add the new manager's role, gender, name, age, account and branch ");
		// 			Scanner scanner = new Scanner(System.in);
		// 			Manager manager = Manager(role, gender, name, age, account, branch);
					
		// 			boolean managerexists = Stafflist.contains(manager);
		// 			if (managerexists == true) {
		// 				System.out.println("Manager exists.");
		// 			} else {
		// 				managers.add(manager);	
		// 			}
					
		// 			break;
		// 	    case 2: 
		// 			System.out.println("Add the new staff's role, gender, name, age, account and branch ");
		// 			Scanner scanner = new Scanner(System.in);
		// 			super(role, gender, name, age, account, branch);

		// 			boolean staffexists = Stafflist.contains(this.Staff);
		// 			if (staffexists == true) {
		// 				System.out.println("Staff exists.");
		// 			} else {
		// 				staffs.add(staff);
						
		// 			}
		// 			break; 	}
		// }while (choice < 3);
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

    public void assignManager(String branch, String name) {
    	Branch targetBranch = null;
        for (Branch branch : branchList) {
            if (branch.getName().equals(branchName)) {
                targetBranch = branch;
                break;
            }
        }

        if (targetBranch == null) {
            System.out.println("Branch not found.");
            return;
        }

        if (targetBranch.getManagerQuota() <= targetBranch.getCurrentManagers().size()) {
            System.out.println("Manager quota exceeded for the branch.");
            return;
        }

        Staff staff = null;
        for (Staff s : staffList) {
            if (s.getName() == name) {
                staff = s;
                break;
            }
        }

        if (staff == null) {
            System.out.println("Staff not found.");
            return;
        }
        
        staff.setRole("Branch Manager");
        staff.setBranch(branchName);
        targetBranch.getCurrentManagers().add(staff);
    }


    public void promoteToBranchManager(String name) {
    	 Staff staff = null;
         for (Staff s : staffList) {
             if (s.getName() == name) {
                 staff = s;
                 break;
             }
         }

         if (staff == null) {
             System.out.println("Staff not found.");
             return;
         }

         if ("Branch Manager".equalsIgnoreCase(staff.getRole())) {
             System.out.println("Staff is already a Branch Manager.");
             return;
         }

         Branch staffBranch = null;
         for (Branch branch : branchList) {
             if (branch.getName().equals(staff.getBranch())) {
                 staffBranch = branch;
                 if (staffBranch.getManagerQuota() > staffBranch.getCurrentManagers().size()) {
                     staff.setRole("Branch Manager");
                     staffBranch.getCurrentManagers().add(staff);
                     System.out.println("Promoted to Branch Manager.");
                 } else {
                     System.out.println("Cannot promote to Branch Manager. Quota exceeded.");
                 }
                 return;
             }
         }
         System.out.println("Staff branch not found.");
     }    

    public void transferStaff(String name, String newBranch) {
    	 Staff staff = null;
         for (Staff s : staffList) {
             if (s.getName() == name) {
                 staff = s;
                 break;
             }
         }

         if (staff == null) {
             System.out.println("Staff not found.");
             return;
         }

         boolean branchExists = false;
         for (Branch branch : branchList) {
             if (branch.getName().equals(newBranchName)) {
                 branchExists = true;
                 break;
             }
         }

         if (!branchExists) {
             System.out.println("New branch not found.");
             return;
         }

         staff.setBranch(newBranchName);
         System.out.println("Staff transferred successfully.");
     }
 

    public void addPayMethod(PayMethod method) {
    	System.out.println("Add the new payment method ");
		Scanner method = new Scanner(System.in);
    	if (payMethods.contains(method)) {
        	System.out.println("payment method already exists.");
    	} 
    	else {
            payMethods.add(method);
    	}
    }

    public void removePaymentMethod(PayMethod method) {
    	System.out.println("Payment method to be removed: ");
		Scanner method1 = new Scanner(System.in);
    	if (PayMethods.contains(method1)) {
            PayMethods.remove(method1);
        	System.out.println("payment method removed.");
    	} 
    	else {
        	System.out.println("payment method does not exist.");
    	}
    }

    public void openBranch(String branchId) {
    	System.out.println("Branch to open: ");
		Scanner openingbranch = new Scanner(System.in);
		if (Branch.contains(openingbranch)) {
	        Branch.add(openingbranch);
        	System.out.println("Branched is opened.");
    	} 
    	else {
    		System.out.println("Branch does not exist. ");
    	}
    
    }

    public void closeBranch(String branchId) {
    	System.out.println("Branch to close: ");
		Scanner closingbranch = new Scanner(System.in);
		if (Branch.contains(closingbranch)) {
	        Branch.remove(closingbranch);
        	System.out.println("Branched is closed.");
    	} 
    	else {
    		System.out.println("Branch does not exist. ");
    	}
	}
	*/

    //================================================================//



}
  
