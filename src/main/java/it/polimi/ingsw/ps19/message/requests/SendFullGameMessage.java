/*
 * @author Andrea Milanta 
 */
package it.polimi.ingsw.ps19.message.requests;

import it.polimi.ingsw.ps19.controller.action.Action;
import it.polimi.ingsw.ps19.controller.action.MessageInterpreterVisitor;

/**
 * Request the server to send all the information about the game
 */
public class SendFullGameMessage extends Request 
{
	private static final long serialVersionUID = -1349912240299946517L;

	/**
	 * Constructor with given id
	 * @param iD
	 */
	public SendFullGameMessage(int iD) 
	{
		id = iD;
	}
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.message.Message#getString()
	 */
	
	@Override
	public Action accept(MessageInterpreterVisitor messageInterpreter) {
		return messageInterpreter.visit(this);
	}

}
