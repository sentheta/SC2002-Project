import java.io.*;
import java.util.*;

public class Account{
	public String username;
	private String password;

	Account(String username){
		this.username = username;
		password = "password";
	}

	public boolean verify(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter password: ");
		return password == sc.next;
	}
	public void changePassword(){
		Scanner sc = new Scanner(System.in);
		if(!verify()){
			System.out.print("Wrong password.");
		}
		else{
			System.out.print("Enter new password: ");
			password = sc.next();	
			System.out.print("Password updated.");
		}
	}
}