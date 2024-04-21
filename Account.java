package FOODIE;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.Serializable;

class Account implements Serializable{
	private String username;
	private byte[] hash;

    //================================================================//
    //================================================================//

	Account(String username){
		Logger.log("Creating new account");

		this.username = username;
		hash = computeHash("password");
	}

	private byte[] computeHash(String s){
		Logger.log("Computing hash");

		String salt = "SC2002_iS_fUn";
		try{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			return md.digest((s+salt).getBytes(StandardCharsets.UTF_8));
		}
		catch(Exception e){
			return new byte[0];
		}
	}

	// verify username and password
	public Boolean verify(String username){
		Scanner sc = new Scanner(System.in);
		if(!username.equals(this.username)) return false;
		Logger.log("Username verified");
		Logger.log("Verifying password");
		

		try{
			System.out.println("Enter your password: ");
			System.out.print(">>> ");
			String input = sc.nextLine();

			if(Arrays.equals(hash, computeHash(input))){
				System.out.println("Verification succeeded");
				return true;
			}
		}
		catch(Exception e){e.printStackTrace();}

		System.out.println("Verification failed");
		return false;
	}

	// change account password
	public void changePassword(){
		Scanner sc = new Scanner(System.in);
		if(!verify(username)) return;

		try{
			System.out.println("Enter new password: ");
			System.out.print(">>> ");
			String input = sc.nextLine();
			hash = computeHash(input);
		}
		catch(Exception e){
			System.out.println("Invalid input");
			e.printStackTrace();
			return;
		}

		System.out.println("Password updated.");
	}

    //================================================================//
    //================================================================//

	public String getUsername(){return username;}
	public void setUsername(String s){username = s;}
}
