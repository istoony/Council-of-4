package it.polimi.ingsw.ps19.exceptions;

/**
 * Exception to notify that a player is disconnected
 */
public class PlayerDisconnectedException extends Exception 
{
	private static final long serialVersionUID = 8676922617260112320L;
	private final int playerID;
	
	/**
	 * Constructor
	 * @param pID: player id which has disconnected
	 */
	public PlayerDisconnectedException(int pID) 
	{
		playerID = pID;
	}
	
	public int getPlayerID() 
	{
		return playerID;
	}
}
