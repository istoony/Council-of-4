package it.polimi.ingsw.PS19.client;

public class WaitingWriterThread extends Thread 
{
	private String text;
	private boolean hasBeenInterrupted = false;
	
	public WaitingWriterThread(String s)
	{
		text = s;
	}
	
	@Override
	public void run()
	{
		while(!hasBeenInterrupted)
		{
			System.out.println(text);
			try{
				Thread.sleep(5000);
			}
			catch (InterruptedException e) {
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
