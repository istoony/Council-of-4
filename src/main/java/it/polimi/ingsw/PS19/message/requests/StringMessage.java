package it.polimi.ingsw.PS19.message.requests;

import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.controller.action.MessageInterpreterVisitor;

public class StringMessage extends Request 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1555049360643133272L;
	String text;

	public StringMessage(String s) 
	{
		text = s;
	}
	
	@Override
	public Action accept(MessageInterpreterVisitor messageInterpreter) 
	{
		return null;
	}

	@Override
	public String getString() 
	{
		return text;
	}

}
