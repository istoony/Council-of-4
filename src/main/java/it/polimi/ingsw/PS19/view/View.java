package it.polimi.ingsw.PS19.view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.PS19.message.Message;
import it.polimi.ingsw.PS19.view.connection.Connection;
import it.polimi.ingsw.PS19.view.connection.ConnectionStatus;
import it.polimi.ingsw.PS19.view.exceptions.NoSuchPlayerException;
import it.polimi.ingsw.PS19.view.exceptions.PlayerDisconnectedException;

public class View extends Observable implements Observer
{
	private ArrayList<Connection> playerConnection;
	
	public View(ArrayList<Connection> conns) 
	{
		playerConnection = conns;
	}
	
	public void update(Observable arg0, Message arg1) 
	{
		
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
	

}
