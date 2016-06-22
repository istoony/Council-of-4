package it.polimi.ingsw.ps19.message.requests;

import it.polimi.ingsw.ps19.message.Message;

/**
 * Message to choose if you want to reconnect to a game or play a new game;
 */
public class ConnectionMessage extends Message
{
	private static final long serialVersionUID = 1L;
	private boolean newGame;
	private int key;
	
	/**
	 * Type of connection requested
	 * @param ng: want to play a new game
	 * @param k: key for old game
	 */
	public ConnectionMessage(boolean ng, int k) 
	{
		newGame = ng;
		key = k;
	}
	
	public int getKey() 
	{
		return key;
	}
	
	public boolean getNewGame()
	{
		return newGame;
	}
}
