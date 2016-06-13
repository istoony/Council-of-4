package it.polimi.ingsw.ps19.message.requests;

import it.polimi.ingsw.ps19.controller.action.Action;
import it.polimi.ingsw.ps19.controller.action.MessageInterpreterVisitor;

public class NewTurnMessage extends Request 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7435306774785268694L;
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
	public String toString() 
	{
		String s = "Turn of player " + activePlayer;
		return s;
	}

}
