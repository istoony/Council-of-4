package it.polimi.ingsw.PS19.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import it.polimi.ingsw.PS19.message.Message;
import it.polimi.ingsw.PS19.message.MessageType;
import it.polimi.ingsw.PS19.message.PlayerDisconnectedMessage;
import it.polimi.ingsw.PS19.message.requests.SendFullGameMessage;
import it.polimi.ingsw.PS19.view.connection.Connection;
import it.polimi.ingsw.PS19.view.connection.ConnectionStatus;
import it.polimi.ingsw.PS19.view.exceptions.NoSuchPlayerException;
import it.polimi.ingsw.PS19.view.exceptions.PlayerDisconnectedException;

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
		Integer id = mex.getID();
		
		//Checks if message is to set new turn, and if so changes the active connection
		if(mex.getType() == MessageType.ID_ACTIVE_PLAYER)
		{
			setActive(id);
			return;
		}
		
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
			
		}
	}
	
	/*
	 * Forwards message on connections and checks that the writing was Succesful
	 */
	public void forwardMessage(Message mex)
	{
		Integer id = mex.getID();
		ArrayList<Future<Integer>> writeFeedback = new ArrayList<Future<Integer>>();
		
		// Broadcast
		if(id < 0)
		{
			for (Entry<Integer, Connection> player : playerConnection.entrySet()) 
			{
				writeFeedback.add(player.getValue().write(mex));
			}
		}
		
		// To specific client
		else
		{
			writeFeedback.add(playerConnection.get(id).write(mex));
		}
		
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
		}
	}
}
