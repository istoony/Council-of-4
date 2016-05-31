package it.polimi.ingsw.PS19.message;

import it.polimi.ingsw.PS19.controller.action.Action;

public class NewTurnMessage extends Message 
{
	int activePlayer;
	
	public NewTurnMessage(int activePlayerId) 
	{
		activePlayer = activePlayerId;
		setId(-1);
	}
	
	public int getActivePlayer() 
	{
		return activePlayer;
	}
	
	@Override
	public Action accept(MessageInterpreterVisitor messageInterpreter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getString() 
	{
		String s = "Turn of player " + activePlayer;
		return s;
	}

}
