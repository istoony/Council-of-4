package it.polimi.ingsw.PS19.message.requests;

import it.polimi.ingsw.PS19.controller.action.Action;

public class ActivePlayerMessage extends Request 
{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5269855660545855772L;

	public ActivePlayerMessage(int activePlayer) 
	{
		id = activePlayer;
	}
	
	@Override
	public String getString() 
	{
		return null;
	}

	@Override
	public Action accept(MessageInterpreterVisitor messageInterpreter) {
		// TODO Auto-generated method stub
		return null;
	}

}
