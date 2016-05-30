package it.polimi.ingsw.PS19.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import it.polimi.ingsw.PS19.exceptions.viewexceptions.WriterException;
import it.polimi.ingsw.PS19.message.Message;
import it.polimi.ingsw.PS19.message.MessageType;
import it.polimi.ingsw.PS19.message.requests.PlayerDisconnectedMessage;
import it.polimi.ingsw.PS19.message.requests.SendFullGameMessage;
import it.polimi.ingsw.PS19.server.Constants;
import it.polimi.ingsw.PS19.view.connection.Connection;
import it.polimi.ingsw.PS19.view.connection.ConnectionStatus;

public class View extends Observable implements Observer, Runnable
{
	private HashMap<Integer, Connection> playerConnection;
	private int activeID = 0;
	private boolean stop = false;
	
	public View(HashMap<Integer, Connection> conns) 
	{
		playerConnection = conns;
	}
	
	/*
	 * Set Active Player
	 */
	private void setActive(int n) 
	{
		if(n < playerConnection.size() && n >= 0)
		{
			if(playerConnection.get(n).getStatus() != ConnectionStatus.DISCONNECTED)
			{
				notifyObservers(new PlayerDisconnectedMessage(n));
				return;
			}
			for(int i = 0; i < playerConnection.size(); i++)
			{
				if(i == n)
				{
					playerConnection.get(n).setActive();
					activeID = n;
				}
				else
				{
					if(playerConnection.get(n).getStatus() != ConnectionStatus.DISCONNECTED) 
						playerConnection.get(n).setInactive();
				}
			}
		}
		else return;
	}

	@Override
	public void update(Observable o, Object arg)
	{
		//Checks whether the object passed is a message or not and if so gets the id;
		if(!(arg instanceof Message))
			return;
		Message mex = (Message) arg;
		Integer id = mex.getId();
		
		//Checks if message is to set new turn, and if so changes the active connection
		
		//If no action is required by the view the message is forwarded to the clients
		forwardMessage(mex);
	}

	/*
	 * "Main of the game"
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() 
	{
		notifyObservers(new SendFullGameMessage(-1));
		while(!stop)
		{
			Connection activeConn = playerConnection.get(activeID);
			Future<Message> waitMex = activeConn.read();
			try 
			{
				Message recMex = waitMex.get(Constants.PLAYER_TIMEOUT_TIME_s, TimeUnit.SECONDS);
				notifyObservers(recMex);
			} 
			//Timeout error => Player set disconnected
			catch (TimeoutException e) 
			{
				activeConn.setDisconnected();
				notifyObservers(new PlayerDisconnectedMessage(activeID));
			}	
			//General Error. Exit
			catch (InterruptedException | ExecutionException e) 
			{
				e.printStackTrace();
				System.exit(0);
			} 
		}
	}
	
	
	public void forwardMessage(Message mex)
	{
		Integer id = mex.getId();
		
		// Broadcast
		if(id < 0)
		{
			for (Entry<Integer, Connection> player : playerConnection.entrySet()) 
			{
				try {
					player.getValue().write(mex);
				} catch (WriterException e) {
					notifyObservers(new PlayerDisconnectedMessage(player.getKey()));
					e.printStackTrace();
				}
			}
		}
		
		// To specific client
		else
		{
			try {
				playerConnection.get(id).write(mex);
			} catch (WriterException e) {
				notifyObservers(new PlayerDisconnectedMessage(id));
				e.printStackTrace();
			}		
		}
		
		/*
		//Verify writing success
		for (int i = 0; i < writeFeedback.size(); i++) 
		{
			try 
			{
				writeFeedback.get(i).get();
			} catch (InterruptedException | ExecutionException e) 
			{
				e.printStackTrace();
				playerConnection.get(i).setDisconnected();
				notifyObservers(new PlayerDisconnectedMessage(i));
			}
		}//*/
	}
}
