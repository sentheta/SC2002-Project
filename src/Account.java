package FOODIE;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.Serializable;
/**
 * Represents an account that all Employees (Staff, Manager, Admin) {@link FOODIE.Employee} has, to log in to the system.
 * 
 */
public class Account implements Serializable{
	/**
	 * Username of the account.
	 */
	private String username;
	/**
	 * Stores password in a hashed format, default password is "password".
	 */
	private byte[] hash;

    //================================================================//
    //================================================================//
	/**
	 * Creates an instance of Account, with given username and default password. 
	 * @param username
	 */
	Account(String username){
		Logger.log("Creating new account");

		this.username = username;
		hash = computeHash("password");
	}
	/**
	 * Converts the password in string to a hashed value for storage, using SHA-256 algorithm
	 * This method adds salt value to input string before hashing to increase security.
	 * @param s: Password, in String format.
	 * @return Byte array representing hashed value.
	 */
	private byte[] computeHash(String s){
		Logger.log("Computing hash");

		String salt = "SC2002_iS_fUn";
		// Converting password into a hashed value.
		try{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			return md.digest((s+salt).getBytes(StandardCharsets.UTF_8));
		}
		catch(Exception e){
			return new byte[0];
		}
	}

	/**
	 * Verifies username and password exists
	 * @param username: Username of the account
	 * @return True if both username and password are verified, else False.
	 */
	public Boolean verify(String username){
		Scanner sc = new Scanner(System.in);
		// Verifying username of user
		if(!username.equals(this.username)) return false;
		Logger.log("Username verified");
		Logger.log("Verifying password");
		

		try{
			System.out.println("Enter your password: ");
			System.out.print(">>> ");
			String input = sc.nextLine();
			// Comparing hashed password to keyed in password
			if(Arrays.equals(hash, computeHash(input))){
				System.out.println("Verification succeeded");
				return true;
			}
		}
		catch(Exception e){e.printStackTrace();}
		// Password verification failed
		System.out.println("Verification failed");
		return false;
	}
	/**
	 * This method allows Employees {@link FOODIE.Employee} to change their passwords.
	 * Method first verifies that the username exists, else it indicates that there is no such account, and algorithm returns.
	 * If username is verified, users are prompted to key in new password to update their existing one.
	 * Uses hashing method computeHash in converting password to hashed value. 
	 * @see #verify(String) Method used to verify the user's identity based on the provided username.
	 * @see #computeHash(String) Method used to hash the new password before updating it. 
	 */
	// change account password
	public void changePassword(){
		Scanner sc = new Scanner(System.in);
		// verification of username
		if(!verify(username)) return;

		try{
			System.out.println("Enter new password: ");
			System.out.print(">>> ");
			String input = sc.nextLine();
			// Converting password into hash value
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
	/** 
	 * Returns the username of the account.
	 * @return String, username of account
	 */
	public String getUsername(){return username;}
	/**
	 * Changes current username to new username, based on input String
	 * @param s Input username as String
	 */
	public void setUsername(String s){username = s;}
}
