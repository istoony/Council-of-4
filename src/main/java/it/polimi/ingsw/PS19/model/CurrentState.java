package it.polimi.ingsw.PS19.model;

public class CurrentState 
{
	private int playerTurnId;
	private Boolean timeToMarket;
	
	
	public CurrentState() 
	{
		playerTurnId = 0;
		timeToMarket = false;
	}
	
	public void setPlayerTurnId(int playerTurnId) {
		this.playerTurnId = playerTurnId;
	}
	public int getPlayerTurnId() {
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
	
	@Override
	public String toString() 
	{
		String s = "";
		s += "\nTimeToMarket" + timeToMarket;
		return s;
	} 
	
	
}
