package it.polimi.ingsw.PS19.message;

import it.polimi.ingsw.PS19.controller.action.Action;

public class SendActionMessage extends Message 
{
	
	private static final long serialVersionUID = -3920462838497498563L;
	
	public SendActionMessage(Action a, int id) 
	{
		this.id = id;
		this.action = a;
	}
	
	
	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return null;
	}

}
