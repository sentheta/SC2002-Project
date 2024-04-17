import java.util.*;

public abstract class Employee implements Person{
	public enum RoleType{STAFF, MANAGER, ADMIN};
	public enum GenderType{MALE, FEMALE};

	public String name;
	public RoleType role;
	public GenderType gender;
	public int age;
	public Account acc;

    //================================================================//

	Employee(String name,RoleType role,GenderType gender,int age,Account acc){
		this.name = name;
		this.role = role;
		this.gender = gender;
		this.age = age;
		this.acc = acc;
	}

    //================================================================//
	
	public String getName(){return name;}
	public void setName(String tmp){name = tmp;}
	public RoleType getRole(){return role;}
	public void setRole(RoleType tmp){role = tmp;}
	public GenderType getGender(){return gender;}
	public void setGender(GenderType tmp){gender = tmp;}
	public int getAge(){return age;}
	public void setAge(int tmp){age = tmp;}
	public Account getAcc(){return acc;}
	public void setAcc(Account tmp){acc = tmp;}
}