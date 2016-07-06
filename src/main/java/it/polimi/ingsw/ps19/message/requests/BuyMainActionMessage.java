package it.polimi.ingsw.ps19.message.requests;

import it.polimi.ingsw.ps19.controller.action.Action;
import it.polimi.ingsw.ps19.controller.action.MessageInterpreterVisitor;

/**
 * Notifies the player intention of buying an extra main action
 */
public class BuyMainActionMessage extends Request 
{
	private static final long serialVersionUID = 1L;

	@Override
	public Action accept(MessageInterpreterVisitor messageInterpreter) {
		return messageInterpreter.visit(this);
	}

}
