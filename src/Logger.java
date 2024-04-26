package FOODIE;
import java.util.*;
/**
 * This class provides functionality for logging messages, verbose mode will be made active once App runs, allowing messages to be printed. 
 */
public class Logger{
	private static boolean active = false;
	/**
	 * Activate verbose mode, enabling logging of messages. 
	 */
	public static void activate(){
		System.err.println(" [:] Verbose mode active");
		active = true;
	}
	/**
	 * Logging a message if verbose mode is active
	 * @param s String to be logged. 
	 */
	public static void log(String s){
		if(active) System.err.println(" [:] " + s);
	}
}
