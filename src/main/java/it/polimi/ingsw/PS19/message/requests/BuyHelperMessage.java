package it.polimi.ingsw.PS19.message.requests;

import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.controller.action.MessageInterpreterVisitor;

public class BuyHelperMessage extends Request {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5953738823072831707L;

	@Override
	public Action accept(MessageInterpreterVisitor messageInterpreter) 
	{
		return messageInterpreter.visit(this);
	}

	@Override
	public String getString() 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
