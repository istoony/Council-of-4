package it.polimi.ingsw.PS19.message.requests;

import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.controller.action.MessageInterpreterVisitor;

public class EndTurnMessage extends Request
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3521953925113191453L;

	public EndTurnMessage() { }

	@Override
	public Action accept(MessageInterpreterVisitor messageInterpreter) 
	{
		return messageInterpreter.visit(this);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
