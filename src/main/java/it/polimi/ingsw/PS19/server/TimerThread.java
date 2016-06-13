/*
 * @author Andrea Milanta
 */
package it.polimi.ingsw.ps19.server;

/*
 * This class works as a timer for the waiting room
 * When the timer runs out the threads check weather the first players have changed, if not it begins a new game;
 */
public class TimerThread extends Thread 
{
	long time;											//Time to sleep (timeout time): set at runtime
	int defaultTime;									//DefaulTime is default waiting time;
	long t0;											//Time of the connection of the second player waiting
	boolean hasBeenInterrupted = false;					//Boolean to stop the thread when the program quits
	
	
	public TimerThread()
	{
		t0 = System.currentTimeMillis();	//By default t0 is time of the creation of the timer;
		defaultTime = Constants.MAX_WAIT_TIME_S*1000;
		System.out.println("Timer Thread has started");
	}
	
	@Override
	public void run()
	{
		while(!hasBeenInterrupted)								//Runs continuously until someone interrupts the thread;
		{
			t0 = WaitingRoom.getStartTime();							//Else get new t0
			if(t0 < 0) 
				time = defaultTime;								//checks there is a second player (t0 > 0) if not set default time;
			else 
			{
				time = t0 + defaultTime - System.currentTimeMillis();	//if new second player is present timeout is in t0 + default - now;
				//System.out.println("TimerStarted");
			}
			try {sleep(time);} 											//Thread sleeps for timeout time: This actually is the countdown
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(WaitingRoom.getStartTime() == t0) 
				WaitingRoom.startGame();	//If second player has not changed start game
		}
	}
	
	@Override
	//Method called by parent to stop thread
	public void interrupt()
	{
		hasBeenInterrupted = true;			//Set flag to true;
		return;
	}
}
