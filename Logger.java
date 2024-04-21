import java.util.*;

class Logger{
	private static boolean active = false;
	public static void activate(){
		System.err.println(" [:] Verbose mode active");
		active = true;
	}
	public static void log(String s){
		if(active) System.err.println(" [:] " + s);
	}
}