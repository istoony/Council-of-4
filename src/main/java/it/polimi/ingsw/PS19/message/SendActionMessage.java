package it.polimi.ingsw.PS19.message;

import it.polimi.ingsw.PS19.controller.action.Action;

public class SendActionMessage extends Message 
{
	private Action action;
	
	public SendActionMessage(Action a) 
	{
		action = a;
	}
	
	public Action getAction() 
	{
		return action;
	}
	
	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return null;
	}

}
