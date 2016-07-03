/*
 * @author Andrea Milanta
 */
package it.polimi.ingsw.ps19.server;


/**
 * This class works as a timer for the waiting room
 * When the timer runs out the threads check weather the first players have changed, if not it begins a new game;
 */
public class TimerThread extends Thread 
{	
	long time;											//Time to sleep (timeout time): set at runtime
	int defaultTime;									//DefaulTime is default waiting time
	long t0;											//Time of the connection of the second player waiting
	boolean hasBeenInterrupted = false;					//Boolean to stop the thread when the program quits
	
	/**
	 * Constructor
	 */
	public TimerThread()
	{
		t0 = System.currentTimeMillis();
		defaultTime = Constants.MAX_WAIT_TIME_S*1000;
		ServerManager.serverCLI.showNotification("Timer Thread has started");
	}
	
	@Override
	public void run()
	{
		while(!hasBeenInterrupted)						
		{
			t0 = WaitingRoom.getStartTime();	
			if(t0 < 0) 
				time = defaultTime;	
			else 
			{
				time = t0 + defaultTime - System.currentTimeMillis();
			}
			try {
				sleep(time);
			} 	
			catch (InterruptedException e) 
			{
				ServerManager.log.log(e);
				this.interrupt();
			}
			if(WaitingRoom.getStartTime() == t0) 
				WaitingRoom.startGame();	//If second player has not changed start game
		}
	}
	
	@Override
	//Method called by parent to stop thread
	public void interrupt()
	{
		hasBeenInterrupted = true;
		return;
	}
}
