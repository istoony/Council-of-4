package it.polimi.ingsw.PS19.controller.action;

import it.polimi.ingsw.PS19.model.Model;

public class SendFullGame implements Action {

	@Override
	public Boolean execute(Model model) 
	{
		if(model == null)
			return false;
		return true;
	}

}
