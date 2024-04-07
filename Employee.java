import java.io.*;
import java.util.*;

public abstract class Employee implements Person{
	public enum RoleType{STAFF, MANAGER, ADMIN};
	public enum GenderType{MALE, FEMALE};

	public String name;
	public RoleType role;
	public GenderType gender;
	public int age;
	public Account account;

	Employee(){

	}

	String getName(){return name;}
	void setName(String s){name = s;}
	RoleType getRole(){return role;}
	void setRole(RoleType tmp){role = tmp;}

}