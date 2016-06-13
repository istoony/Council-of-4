/*
 * @author Andrea Milanta
 */
package it.polimi.ingsw.PS19.server;

/**
 * Class that implements a simple mutex;
 */
public class Mutex 
{
	private boolean locked = false;
	
	/**
	 * Waits for the mutex to be released and the blocks it
	 */
	public void lock()								
	{
		while(locked);	
		locked = true;
	}
	
	/**
	 * Release the mutex
	 */
	public void unlock()
	{
		locked = false;
	}
}
