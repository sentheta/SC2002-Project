import java.util.*;

public class Account{
	private String username;
	private int hash; // ?? change to SHA or something

    //================================================================//

	Account(){
		this("Admin");
	}
	Account(String username){
		this.username = username;
		hash = computeHash("password");
	}

	// compute polynomial hash of string
	private int computeHash(String s){
		long ret = 0, base = 2024, mul = 1, mod = 1000000007;
		for(int i=0; i<s.length(); i++){
			ret = (ret + s.charAt(i)*mul)%mod;
			mul = mul*base%mod;
		}
		return (int)ret;
	}

	// verify username and password
	public Boolean verify(String username){
		if(username!=this.username) return false;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter password: ");
		return hash	== computeHash(sc.next());
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

	public String getUsername(){return username;}
	public void setUsername(String s){username = s;}
}