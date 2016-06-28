package it.polimi.ingsw.ps19.controller.action;

import it.polimi.ingsw.ps19.controller.support.ActionMessages;
import it.polimi.ingsw.ps19.controller.support.SupportMethod;
import it.polimi.ingsw.ps19.message.replies.Reply;
import it.polimi.ingsw.ps19.message.replies.SendFullPlayerReply;
import it.polimi.ingsw.ps19.model.Model;

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
		model.getPlayerById(playerId).setMainActionCounter(model.getPlayerById(playerId).getMainActionCounter() + SupportMethod.N_OF_ACTION_TO_ADD);
		model.getPlayerById(playerId).setFastActionCounter(model.getPlayerById(playerId).getFastActionCounter() -  SupportMethod.N_OF_ACTION_TO_ADD);
		model.getPlayerById(playerId).setHelpers(model.getPlayerById(playerId).getHelpers() - HELPERS_NEEDED);
		result = ActionMessages.EVERYTHING_IS_OK;
		return true;
	}

	@Override
	public Boolean isPossible(Model model) 
	{
		if(!SupportMethod.checkPlayerTurnAndAction(model, playerId, result, SupportMethod.FAST_ACTION))
			return false;
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
