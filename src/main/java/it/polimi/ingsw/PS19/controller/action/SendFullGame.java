package it.polimi.ingsw.PS19.controller.action;

import it.polimi.ingsw.PS19.model.Model;

public class SendFullGame implements Action {

	private String result;
	@Override
	public Boolean execute(Model model) 
	{
		model.getCurrentState().setSendfullgame(true);
		return true;
	}

	@Override
	public Boolean isPossible(Model model) 
	{
		if(model == null)
		{
			result = Action.GENERIC_ERROR;
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

	@Override
	public void createReplyMessage() 
	{
		
	}

	@Override
	public void checkAlreadyTurn() 
	{
		return;		
	}

}
