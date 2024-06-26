package FOODIE;
/**
 * An interface representing objects that can perform certain actions.
 */
public interface IActionable{
	// Print list of action and lets user choose ONE action
	// Return TRUE if some action is done
	// Return FALSE if to end session
	/**
     * Prints a list of actions and allows the user to choose their actions.
     * 
     * @return true if an action is performed, false to end the session.
     */
	public boolean chooseAction();
}
