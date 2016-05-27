package it.polimi.ingsw.PS19.controller.action;

import it.polimi.ingsw.PS19.model.Model;

public interface Action
{

	public Boolean execute(Model model);
	
	public Boolean isPossible(Model model);
	

}
