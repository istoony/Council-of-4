package it.polimi.ingsw.ps19.controller.action;

import it.polimi.ingsw.ps19.controller.support.ActionMessages;
import it.polimi.ingsw.ps19.controller.support.SupportMethod;
import it.polimi.ingsw.ps19.message.replies.Reply;
import it.polimi.ingsw.ps19.message.replies.SendFullGameReply;
import it.polimi.ingsw.ps19.model.Model;

public class SendFullGame extends SupportMethod implements Action 
{

	private String result;
	@Override
	public Boolean execute(Model model) 
	{
		return true;
	}

	@Override
	public Boolean isPossible(Model model) 
	{
		if(model == null)
		{
			result = ActionMessages.GENERIC_ERROR;
			return false;
		}
		result = ActionMessages.EVERYTHING_IS_OK;
		return true;
	}
	
	@Override
	public Reply createReplyMessage(Model model) 
	{
		return new SendFullGameReply(model.getCurrentState().getPlayerTurnId(), result, 
				model.getPlayer(), model.getMap().getRegionList(), model.getMap().getKing(),
				model.getMap().getAvailableCouncillor(), model.getMap().getNobilityPath());
	}

}
