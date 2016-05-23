package it.polimi.ingsw.PS19.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import it.polimi.ingsw.PS19.message.Message;
import it.polimi.ingsw.PS19.view.connection.Connection;
import it.polimi.ingsw.PS19.view.connection.ConnectionStatus;
import it.polimi.ingsw.PS19.view.exceptions.NoSuchPlayerException;
import it.polimi.ingsw.PS19.view.exceptions.PlayerDisconnectedException;

public class View extends Observable implements Observer
{
	private HashMap<Integer, Connection> playerConnection;
	
	public View(HashMap<Integer, Connection> conns) 
	{
		playerConnection = conns;
	}
	
	/*
	 * Set Active Player
	 */
	private void setActive(int n) throws Exception
	{
		if(n < playerConnection.size() && n >= 0)
		{
			for(int i = 0; i < playerConnection.size(); i++)
			{
				if(i == n)
				{
					if(playerConnection.get(n).getStatus() == ConnectionStatus.DISCONNECTED) 
						throw new PlayerDisconnectedException(i);
					else 
						playerConnection.get(n).setActive();
				}
				else
				{
					if(playerConnection.get(n).getStatus() != ConnectionStatus.DISCONNECTED) 
						playerConnection.get(n).setInactive();
				}
			}
		}
		else throw new NoSuchPlayerException();
	}

	@Override
	public void update(Observable o, Object arg)
	{
		if(!(arg instanceof Message))
			return;
		Message mex = (Message) arg;
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
			}
		}
	}
}
