package it.polimi.ingsw.PS19.client;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Starts a separate thread that writes a string every TIMEOUT milliseconds
 */
public class WaitingWriterThread extends Thread 
{
	protected static final Logger log = Logger.getLogger("Waiting_Writer_Thread_Logger");
	private static final int TIMEOUT = 5000;
	private String text;
	private boolean hasBeenInterrupted = false;
	private ClientUI userInterface;
	
	/**
	 * @param s: string that will be written by the thread
	 * @param ui: user interface
	 */
	public WaitingWriterThread(String s, ClientUI ui)
	{
		text = s;
		userInterface = ui;
	}
	
	@Override
	public void run()
	{
		while(!hasBeenInterrupted)
		{
			userInterface.showNotification(text);
			try{
				Thread.sleep(TIMEOUT);
			}
			catch (InterruptedException e) 
			{
				this.interrupt();
				log.log(Level.OFF, e.toString(), e);
				return;
			}
		}
		return;
	}
	
	@Override
	public void interrupt()
	{
		hasBeenInterrupted = true;
		return;
	}

}
