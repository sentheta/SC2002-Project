import java.io.*;
import java.util.*;

public class Employee implements Person{
	public enum RoleType{STAFF, MANAGER, ADMIN};
	public enum GenderType{MALE, FEMALE};

	public String name;
	public RoleType role;
	public GenderType gender;
	public int age;
	public Account account;

	Employee(){

	}
}