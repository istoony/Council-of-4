package it.polimi.ingsw.PS19.controller.action;

import it.polimi.ingsw.PS19.model.Model;

public interface Action
{

	public Boolean execute(Model model);
	
	public Boolean isPossible(Model model);
	
	public static Boolean ceckPlayerTurn(int id1, Model m)
	{
		return id1 != m.getCurrentState().getPlayerTurnId();
	}
	

}
