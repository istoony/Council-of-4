package it.polimi.ingsw.ps19.controller.action;

import it.polimi.ingsw.ps19.controller.support.ActionMessages;
import it.polimi.ingsw.ps19.message.replies.Reply;
import it.polimi.ingsw.ps19.message.replies.SendFullPlayerReply;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.parameter.Costants;

public class BuyMainAction implements Action 
{
	private static final int HELPERS_NEEDED = 3;
	private String result;
	private int playerId;
	
	public BuyMainAction(int id) 
	{
		playerId =id;
	}
	
	
	@Override
	public Boolean execute(Model model) 
	{
		model.getPlayerById(playerId).setMainActionCounter(model.getPlayerById(playerId).getMainActionCounter() + Costants.N_OF_ACTION_TO_ADD);
		model.getPlayerById(playerId).setFastActionCounter(model.getPlayerById(playerId).getFastActionCounter() -  Costants.N_OF_ACTION_TO_ADD);
		result = ActionMessages.EVERYTHING_IS_OK;
		return true;
	}

	@Override
	public Boolean isPossible(Model model) 
	{
		if(Action.checkPlayerTurn(playerId, model))
		{
			result = ActionMessages.NOT_YOUR_TURN;
			return false;
		}
		if(model.getPlayerById(playerId).getFastActionCounter() < Costants.N_OF_ACTION_TO_ADD)
		{
			result = ActionMessages.NO_ACTION_TO_DO_IT;
			return false;
		}
		result = ActionMessages.EVERYTHING_IS_OK;
		if(model.getPlayerById(playerId).getHelpers() >= HELPERS_NEEDED)
			return true;
		result = ActionMessages.NO_HELPERS;
		return false;
	}

	@Override
	public Reply createReplyMessage(Model model) 
	{
		return new SendFullPlayerReply(model.getCurrentState().getPlayerTurnId(),
				result,model.getPlayer());
	}

}
