package it.polimi.ingsw.PS19.message;

import it.polimi.ingsw.PS19.controller.action.Action;

public class StringMessage extends Message 
{
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
