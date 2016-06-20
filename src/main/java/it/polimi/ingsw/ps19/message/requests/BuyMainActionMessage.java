package it.polimi.ingsw.PS19.message.requests;

import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.controller.action.MessageInterpreterVisitor;

public class BuyMainActionMessage extends Request 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Action accept(MessageInterpreterVisitor messageInterpreter) {
		return messageInterpreter.visit(this);
	}

}
