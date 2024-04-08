import java.io.*;
import java.util.*;

public abstract class Employee implements Person{
	public enum RoleType{STAFF, MANAGER, ADMIN};
	public enum GenderType{MALE, FEMALE};

	public String name;
	public RoleType role;
	public GenderType gender;
	public int age;
	public Account acc;
		
	String getName(){return name;}
	void setName(String tmp){name = tmp;}
	RoleType getRole(){return role;}
	void setRole(RoleType tmp){role = tmp;}
	GenderType getGender(){return gender;}
	void setGender(GenderType tmp){gender = tmp;}
	int getAge(){return age;}
	void setAge(int tmp){age = tmp;}
	Account getAcc(){return acc;}
	void setAcc(Account tmp){acc = tmp;}
}