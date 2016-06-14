package it.polimi.ingsw.PS19.controller.action;

import it.polimi.ingsw.PS19.message.replies.Reply;
import it.polimi.ingsw.PS19.model.Model;

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
		return sendFullGame(model);
	}

}
