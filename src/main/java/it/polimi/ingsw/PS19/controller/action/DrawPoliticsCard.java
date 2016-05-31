package it.polimi.ingsw.PS19.controller.action;

import it.polimi.ingsw.PS19.model.Model;

public class DrawPoliticsCard implements Action 
{
	private int playerId;
	private String result;
	
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
		{
			result = Action.NOT_YOUR_TURN;
			return false;
		}
		
		if(model.getMap().getPoliticdeck().getSize() == 0)
		{
			result = Action.POLITIC_DECK_IS_OVER;
			return false;
		}
		result = Action.EVERYTHING_IS_OK;
		return true;
	}
	@Override
	public String getStringResult() 
	{
		return result;
	}

}
