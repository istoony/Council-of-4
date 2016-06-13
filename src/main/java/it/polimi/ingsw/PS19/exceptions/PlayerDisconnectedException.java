package it.polimi.ingsw.PS19.exceptions;

/**
 * Exception to notify that a player is disconnected
 */
public class PlayerDisconnectedException extends Exception 
{
	private static final long serialVersionUID = 8676922617260112320L;
	private int playerID;
	
	public PlayerDisconnectedException(int pID) 
	{
		playerID = pID;
	}
	
	public int getPlayerID() 
	{
		return playerID;
	}
}
