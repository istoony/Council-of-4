package it.polimi.ingsw.PS19.controller.action;

import it.polimi.ingsw.PS19.model.Model;

public class SendFullGame implements Action {

	private String result;
	@Override
	public Boolean execute(Model model) 
	{
		if(model == null)
			return false;
		return true;
	}

	@Override
	public Boolean isPossible(Model model) 
	{
		result = Action.EVERYTHING_IS_OK;
		return true;
	}

	@Override
	public String getStringResult() {
		return result;
	}

}
