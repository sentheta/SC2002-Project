import java.io.*;
import java.util.*;

public class App{
	public static Employee[] employees;
	public static Branch[] branches;
	public static PayMethod[] payMethods;
	private static String filename = "data";

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		if(args.length > 0){
			filename = args[0];
		}
		readFile();






		writeFile();
	}	

	private static void readFile(){

	}
	private static void writeFile(){

	}
}