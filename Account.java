import java.util.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Account{
	private String username;
	private byte[] hash;

    //================================================================//
    //================================================================//

	Account(String username){
		this.username = username;
		hash = computeHash("password");
	}

	private byte[] computeHash(String s){
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
		if(username!=this.username) return false;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter password: ");
		return hash == computeHash(sc.next());
	}

	// change account password
	public void changePassword(){
		Scanner sc = new Scanner(System.in);

		if(!verify(username)){
			System.out.print("Wrong password.");
		}
		else{
			System.out.print("Enter new password: ");
			hash = computeHash(sc.next());
			System.out.print("Password updated.");
		}
	}

    //================================================================//
    //================================================================//

	public String getUsername(){return username;}
	public void setUsername(String s){username = s;}
}