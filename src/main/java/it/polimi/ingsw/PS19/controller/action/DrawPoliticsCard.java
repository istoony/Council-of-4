package it.polimi.ingsw.PS19.controller.action;

import it.polimi.ingsw.PS19.model.Model;

public class DrawPoliticsCard implements Action 
{
	int playerId;
	
	public DrawPoliticsCard(int id) 
	{
		playerId = id;
	}
	@Override
	public Boolean execute(Model model) 
	{
		model.getPlayerById(playerId).addCardToHand(model.getMap().getPoliticdeck().getFirstCard());
		return true;
	}

	@Override
	public Boolean isPossible(Model model) 
	{
		if(Action.checkPlayerTurn(playerId, model))
			return false;
		
		if(model.getMap().getPoliticdeck().getSize() == 0)
			return false;
		return true;
	}

}
