package it.polimi.ingsw.ps19.model;

import java.util.HashMap;
import java.util.Map;

public class CurrentState 
{
	private int playerTurnId;
	private Boolean timeToMarket;
	private int numberOfPlayer;
	private Map<Integer, Boolean> connection;
	
	
	public CurrentState(int numberOfPlayer) 
	{
		playerTurnId = 0;
		timeToMarket = false;
		this.numberOfPlayer = numberOfPlayer;
		connection = new HashMap<>();
	}
	
	public void addPlayer(int id)
	{
		connection.put(id, true);
	}
	
	public void setPlayerTurnId(int playerTurnId) 
	{
		this.playerTurnId = playerTurnId;
	}
	public int getPlayerTurnId() 
	{
		return playerTurnId;
	}
	public void setTimeToMarket(Boolean timeToMarket) 
	{
		this.timeToMarket = timeToMarket;
	}
	public Boolean getTimeToMarket() 
	{
		return timeToMarket;
	}
	
	public int getNumberOfPlayer() 
	{
		return numberOfPlayer;
	}
	
	public void disconnectPlayer(int id)
	{
		connection.put(id, false);
	}
	
	public boolean isConnectedById(int id)
	{
		return connection.get(id);
	}
	
	public int giveNextCorrectId(int i)
	{
		int next = playerTurnId;
		if(playerTurnId == i)
			next= (playerTurnId + 1) % numberOfPlayer;
		while(!connection.get(next))
			next = (next + 1) % numberOfPlayer;
		return next;
	}
	
	@Override
	public String toString() 
	{
		String s = "";
		s += "\nTimeToMarket" + timeToMarket;
		return s;
	}

	public int getNumberOfDisconnectedPlayer() 
	{
		int n = 0;
		for(int i = 0; i<connection.size(); i++)
			if(!connection.get(i))
				n++;
		return n;
	} 
	
	
}
