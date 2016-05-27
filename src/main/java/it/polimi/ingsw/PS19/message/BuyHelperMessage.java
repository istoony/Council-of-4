package it.polimi.ingsw.PS19.message;

import it.polimi.ingsw.PS19.controller.action.Action;

public class BuyHelperMessage extends Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6631486135334169951L;

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
