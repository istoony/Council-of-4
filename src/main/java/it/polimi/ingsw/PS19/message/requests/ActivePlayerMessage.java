package it.polimi.ingsw.PS19.message.requests;

import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.controller.action.MessageInterpreterVisitor;

/**
 * Message to send the active player
 */
public class ActivePlayerMessage extends Request 
{
	private static final long serialVersionUID = -5269855660545855772L;

	/**
	 * Constructor
	 * @param activePlayer
	 */
	public ActivePlayerMessage(int activePlayer) 
	{
		id = activePlayer;
	}
	
	@Override
	public Action accept(MessageInterpreterVisitor messageInterpreter) 
	{
		return null;
	}

}
