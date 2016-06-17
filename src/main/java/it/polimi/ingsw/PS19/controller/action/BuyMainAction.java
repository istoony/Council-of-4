package it.polimi.ingsw.PS19.controller.action;

import it.polimi.ingsw.PS19.message.replies.Reply;
import it.polimi.ingsw.PS19.message.replies.SendFullPlayerReply;
import it.polimi.ingsw.PS19.model.Model;

public class BuyMainAction implements Action 
{
	private static final int N_OF_ACTION_TO_ADD = 1;
	private static final int HELPERS_NEEDED = 4;
	private String result;
	private int playerId;
	
	public BuyMainAction(int id) 
	{
		playerId =id;
	}
	
	
	@Override
	public Boolean execute(Model model) 
	{
		model.getPlayerById(playerId).setMainActionCounter(model.getPlayerById(playerId).getMainActionCounter() + N_OF_ACTION_TO_ADD);
		model.getPlayerById(playerId).setFastActionCounter(model.getPlayerById(playerId).getFastActionCounter() - N_OF_ACTION_TO_ADD);
		result = ActionMessages.EVERYTHING_IS_OK;
		return true;
	}

	@Override
	public Boolean isPossible(Model model) 
	{
		result = ActionMessages.EVERYTHING_IS_OK;
		if(model.getPlayerById(playerId).getHelpers() > HELPERS_NEEDED)
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
