package it.polimi.ingsw.ps19.controller.action;

import it.polimi.ingsw.ps19.controller.support.ActionMessages;
import it.polimi.ingsw.ps19.message.replies.Reply;
import it.polimi.ingsw.ps19.message.replies.SendFullPlayerReply;
import it.polimi.ingsw.ps19.model.Model;

public class DrawPoliticsCard implements Action 
{
	private int playerId;
	private int numberOfCard;
	private String result;
	
	public DrawPoliticsCard(int id, int numberOfCard) 
	{
		playerId = id;
		this.numberOfCard = numberOfCard;
	}
	@Override
	public Boolean execute(Model model) 
	{
		for(int i = 0; i<numberOfCard; i++)
			model.getPlayerById(playerId).addCardToHand(model.getMap().getPoliticdeck().getFirstCard());
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
		
		if(model.getMap().getPoliticdeck().getSize() == 0)
		{
			result = ActionMessages.POLITIC_DECK_IS_OVER;
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
