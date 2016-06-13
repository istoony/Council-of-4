/*
 * @author Andrea Milanta 
 */
package it.polimi.ingsw.PS19.message.requests;

import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.controller.action.MessageInterpreterVisitor;

/**
 * Message to send full game
 */
public class SendFullGameMessage extends Request 
{
	private static final long serialVersionUID = -1349912240299946517L;

	/**
	 * Constructor
	 * @param iD
	 */
	public SendFullGameMessage(int iD) 
	{
		id = iD;
	}
	
	@Override
	public Action accept(MessageInterpreterVisitor messageInterpreter) {
		return messageInterpreter.visit(this);
	}

}
