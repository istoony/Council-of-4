package it.polimi.ingsw.ps19.message.requests;

import it.polimi.ingsw.ps19.controller.action.Action;
import it.polimi.ingsw.ps19.controller.action.MessageInterpreterVisitor;

public class DrawPoliticsCardMessage extends Request
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
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
