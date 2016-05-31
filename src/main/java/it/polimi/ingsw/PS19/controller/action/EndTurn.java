package it.polimi.ingsw.PS19.controller.action;

import it.polimi.ingsw.PS19.model.Model;

public class EndTurn implements Action 
{
	private int playerId;
	
	public EndTurn(int id) 
	{
		playerId = id;
	}
	@Override
	public Boolean execute(Model model) 
	{
		if(model.getNumberofplayer()-1 == playerId)
			model.getCurrentState().setPlayerTurnId(0);
		else 
			model.getCurrentState().setPlayerTurnId(playerId + 1);
		return true;
	}

	@Override
	public Boolean isPossible(Model model) 
	{
		if(Action.checkPlayerTurn(playerId, model))
			return false;
		
		if(model.getPlayerById(playerId).getMainActionCounter() != 0)
			return false;
		return true;
	}

}
