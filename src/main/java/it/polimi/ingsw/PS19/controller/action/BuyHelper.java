package it.polimi.ingsw.PS19.controller.action;

import it.polimi.ingsw.PS19.message.replies.Reply;
import it.polimi.ingsw.PS19.message.replies.SendFullPlayerReply;
import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.parameter.Costants;

public class BuyHelper implements Action
{
	private static final int HELPERS = 1;
	private static final int MONEY = 3;
	private int id;
	private String result;
	
	public BuyHelper(int id) 
	{
		this.id = id;
	}

	@Override
	public Boolean execute(Model model) 
	{
		model.getPlayerById(id).setMoney(model.getPlayerById(id).getMoney() - MONEY);
		model.getPlayerById(id).setHelpers(model.getPlayerById(id).getHelpers() + HELPERS);
		model.getPlayerById(id).setFastActionCounter(model.getPlayerById(id).getFastActionCounter() - 1);
		return true;
	}

	@Override
	public Boolean isPossible(Model model) 
	{
		if(Action.checkPlayerTurn(id, model))
		{
			result = ActionMessages.NOT_YOUR_TURN;
			return false;
		}
		
		if(model.getPlayerById(id).getFastActionCounter() < Costants.N_OF_ACTION_TO_ADD)
		{
			result = ActionMessages.NO_ACTION_TO_DO_IT;
			return false;
		}
		
		if(model.getPlayerById(id).getMoney() < MONEY)
		{
			result = ActionMessages.NO_MONEY;
			return false;
		}
		result = ActionMessages.EVERYTHING_IS_OK;
		return true;
	}

	@Override
	public Reply createReplyMessage(Model model) 
	{
		return new SendFullPlayerReply(model.getCurrentState().getPlayerTurnId(),
				result,model.getPlayer());
	}

}
