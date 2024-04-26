package FOODIE;
import java.util.*;
import java.io.Serializable;
/** 
 * An abstract class representing an Employee.
 */
public abstract class Employee implements IActionable, Serializable{
	/**
	 * Enumeration for role type of the employee
	 */
	public enum RoleType{STAFF, MANAGER, ADMIN};
	/**
	 * Enumeration for gender of employee
	 */
	public enum GenderType{MALE, FEMALE};
	//Name of employee
	public String name;
	//Role of employee
	public RoleType role;
	// Gender of employee
	public GenderType gender;
	// Age of employee
	public int age;
	// Account object of employee
	public Account acc;

    //================================================================//
    //================================================================//
	/**
	 * Constructor for Employee class, creates an Employee object with name, role, gender, age and account.
	 * @param name Name of employee
	 * @param role Role of employee (Staff/Manager)
	 * @param gender Gender of employee
	 * @param age Age of employee
	 * @param acc Account of employee
	 */
	Employee(String name,RoleType role,GenderType gender,int age,Account acc){
		this.name = name;
		this.role = role;
		this.gender = gender;
		this.age = age;
		this.acc = acc;
	}
	/**
	 * Gets the account username of the employee
	 * @return String - username of account.
	 */
	public String getUsername(){
		return acc.getUsername();
	}

    //================================================================//
    //================================================================//
	/** 
	 * Gets name of employee
	 * @return String - name of employee
	 */
	public String getName(){return name;}
	/**
	 * Sets name of employee
	 * @param tmp new name of employee
	 */
	public void setName(String tmp){name = tmp;}
	/**
	 * Gets role of the employee
	 * @return Enumeration of RoleType (STAFF/MANAGER)
	 */
	public RoleType getRole(){return role;}
	/**
	 * Sets role of employee
	 * @param tmp New Role of employee
	 */
	public void setRole(RoleType tmp){role = tmp;}
	/**
	 * Gets gender of employee
	 * @return Enumeration of GenderType (MALE/FEMALE)
	 */
	public GenderType getGender(){return gender;}
	/**
	 * Sets gender of employee
	 * @param tmp New gender of employee
	 */
	public void setGender(GenderType tmp){gender = tmp;}
	/**
	 * Gets current age of employee.
	 * @return int - age of employee
	 */
	public int getAge(){return age;}
	/** 
	 * Sets current age of employee
	 * @param tmp age of employee
	 */
	public void setAge(int tmp){age = tmp;}
	/**
	 * Gets Account of employee
	 * @return Account object of employee
	 */
	public Account getAcc(){return acc;}
	/**
	 * Sets employee's account.
	 * @param tmp Employee's Account object.
	 */
	public void setAcc(Account tmp){acc = tmp;}
}
