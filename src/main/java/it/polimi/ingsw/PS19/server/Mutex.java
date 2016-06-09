/*
 * @author Andrea Milanta
 */
package it.polimi.ingsw.PS19.server;

/*
 * Class to implements a simple mutex;
 */
public class Mutex 
{
	private static boolean locked = false;			//Boolean to implements the mutex

	public Mutex(){
		//Sonar è contento
	};
	
	/*
	 * Waits for the mutex to be released and the blocks it
	 */
	public void lock()								
	{
		while(locked);							//Waits for unlocking
			locked = true;
	}
	
	/*
	 * Release the mutex
	 */
	public void unlock()
	{
		locked = false;
	}
}
