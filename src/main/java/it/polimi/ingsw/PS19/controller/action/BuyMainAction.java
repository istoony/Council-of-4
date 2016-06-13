package it.polimi.ingsw.PS19.controller.action;

import it.polimi.ingsw.PS19.message.replies.Reply;
import it.polimi.ingsw.PS19.model.Model;

public class BuyMainAction implements Action 
{
	private String result;
	private int playerId;
	
	public BuyMainAction(int id) 
	{
		playerId =id;
	}
	
	
	@Override
	public Boolean execute(Model model) 
	{
		model.getPlayerById(playerId).setMainActionCounter(model.getPlayerById(playerId).getMainActionCounter()+1);
		return true;
	}

	@Override
	public Boolean isPossible(Model model) 
	{
		result = ActionMessages.EVERYTHING_IS_OK;
		if(model.getPlayerById(playerId).getHelpers() > 4)
			return true;
		result = ActionMessages.NO_HELPERS;
		return false;
	}

	@Override
	public String getStringResult() {
		return result;
	}

	@Override
	public Reply createReplyMessage(Model model) {
		// TODO Auto-generated method stub
		return null;
	}

}
