package it.polimi.ingsw.PS19.model;

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
	
	@Override
	public String toString() 
	{
		String s = "";
		s += "\nTimeToMarket" + timeToMarket;
		return s;
	} 
	
	
}
