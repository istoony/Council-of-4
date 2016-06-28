package it.polimi.ingsw.ps19.controller.action.bonus;

import java.util.List;

import it.polimi.ingsw.ps19.controller.action.Action;
import it.polimi.ingsw.ps19.controller.support.ActionMessages;
import it.polimi.ingsw.ps19.controller.support.SupportMethod;
import it.polimi.ingsw.ps19.message.replies.DrawBusinessCardReply;
import it.polimi.ingsw.ps19.message.replies.Reply;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.card.BusinessCard;

public class DrawBusinessCardBonus implements Action
{
	private static final int NO_OTHER_BUSINESS_CARD = 0;
	private int playerId;
	private List<BusinessCard> card;
	
	private String result;
	
	public DrawBusinessCardBonus(int playerTurn, List<BusinessCard> card) 
	{
		this.playerId = playerTurn;
		this.card = card;
	}

	@Override
	public Boolean execute(Model model) 
	{
		for (BusinessCard businessCard : card) 
		{
			SupportMethod.removeCardFromRegionAndAddToPlayer(model, model.getPlayerById(playerId), businessCard, businessCard.getType());
			
			SupportMethod.giveListOfBonus(model, model.getPlayerById(playerId), businessCard.getBonus());
		}
			model.getPlayerById(playerId).setDrawBusinessCard(NO_OTHER_BUSINESS_CARD);
			result = ActionMessages.EVERYTHING_IS_OK;
		return true;
	}

	@Override
	public Boolean isPossible(Model model) 
	{
		if(model.getPlayerById(playerId).getDrawBusinessCard() < 0)
		{
			result = ActionMessages.NO_BUSINESS_CARD_TO_SELECT;
			return false;
		}
		for (BusinessCard businessCard : card) 
			if(!SupportMethod.findExistBusinessCard(model, businessCard.getType(), businessCard))
			{
				result = ActionMessages.GENERIC_ERROR;
				return false;
			}
		
		result = ActionMessages.EVERYTHING_IS_OK;
		return false;
	}

	@Override
	public Reply createReplyMessage(Model model)
	{
		return new DrawBusinessCardReply(playerId, result, model.getPlayer(), model.getMap().getRegionList());
	}

}
