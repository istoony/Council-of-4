package it.polimi.ingsw.PS19.message.requests;

import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.controller.action.MessageInterpreterVisitor;

/**
 * Message to notify of the beginning of a new turn
 */
public class NewTurnMessage extends Request 
{
	private static final long serialVersionUID = -7435306774785268694L;
	int activePlayer;
	
	/**
	 * Constructor
	 * @param activePlayerId
	 */
	public NewTurnMessage(int activePlayerId) 
	{
		activePlayer = activePlayerId;
		setId(-1);
	}
	
	public int getActivePlayer() 
	{
		return activePlayer;
	}
	
	@Override
	public Action accept(MessageInterpreterVisitor messageInterpreter) 
	{
		return null;
	}
}
