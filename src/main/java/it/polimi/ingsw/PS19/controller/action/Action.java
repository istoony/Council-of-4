package it.polimi.ingsw.PS19.controller.action;

import it.polimi.ingsw.PS19.message.replies.Reply;
import it.polimi.ingsw.PS19.model.Model;

public interface Action
{

	public Boolean execute(Model model);
	
	public Boolean isPossible(Model model);
	
	public String getStringResult();
	
	public Reply createReplyMessage(Model model);
	
	public void checkAlreadyTurn();
	
	
	public static Boolean checkPlayerTurn(int id, Model m)
	{
		return id != m.getCurrentState().getPlayerTurnId();
	}
	

}
