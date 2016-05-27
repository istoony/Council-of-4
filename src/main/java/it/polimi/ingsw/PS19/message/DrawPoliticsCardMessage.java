package it.polimi.ingsw.PS19.message;

import it.polimi.ingsw.PS19.controller.action.Action;

public class DrawPoliticsCardMessage extends Message
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4131935468830072532L;

	public DrawPoliticsCardMessage() 
	{
		
	}

	@Override
	public Action accept(MessageInterpreterVisitor messageInterpreter) {
		return messageInterpreter.visit(this);
	}

	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
