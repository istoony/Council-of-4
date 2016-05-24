/**
 * 
 */
package it.polimi.ingsw.PS19.Client;
import java.util.concurrent.locks.*;
/**
 * @author Andrea
 *
 */
public class waitingWriterThread extends Thread 
{
	private String text;
	private boolean hasBeenInterrupted = false;
	public waitingWriterThread(String s)
	{
		text = s;
	}
	
	@Override
	public void run()
	{
		while(!hasBeenInterrupted)
		{
			System.out.println(text);
			try {Thread.sleep(5000);}
			catch (InterruptedException e) {
				return;
			}
		}
		//System.out.println("Thread is dead");
		return;
	}
	
	@Override
	public void interrupt()
	{
		//System.out.println("Thread interrupted");
		hasBeenInterrupted = true;
		return;
	}
}
