package it.polimi.ingsw.PS19.message;

import it.polimi.ingsw.PS19.controller.action.Action;

public class ActivePlayerMessage extends Message 
{
	private static final long serialVersionUID = 6744987619447786564L;
	
	
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
