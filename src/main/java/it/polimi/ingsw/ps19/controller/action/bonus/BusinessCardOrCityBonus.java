package it.polimi.ingsw.ps19.controller.action.bonus;

import it.polimi.ingsw.ps19.controller.action.Action;
import it.polimi.ingsw.ps19.controller.support.ActionMessages;
import it.polimi.ingsw.ps19.controller.support.SupportMethod;
import it.polimi.ingsw.ps19.message.replies.Reply;
import it.polimi.ingsw.ps19.message.replies.SendFullPlayerReply;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.map.City;


/**
 * Actiont to apply to a player a set of bonuses from a business 
 * card or a city he could choose as a bonus
 */
public class BusinessCardOrCityBonus implements Action 
{
	private BusinessCard card;
	private City city;
	private int playerId;
	private String result;

	/**
	 * Constructor for if the player chose a business card
	 * @param card
	 * @param playerId
	 */
	public BusinessCardOrCityBonus(BusinessCard card, int playerId) 
	{
		this.playerId = playerId;
		this.card = card;
		this.city = null;
	}
	
	/**
	 * Constructor for if the layer chose a city
	 * @param city
	 * @param playerId
	 */
	public BusinessCardOrCityBonus(City city, int playerId) 
	{
		this.playerId = playerId;
		this.city = city;
	}
	@Override
	public Boolean execute(Model model) 
	{
		if(city == null)
		{
			SupportMethod.giveListOfBonus(model, model.getPlayerById(playerId), card.getBonus());
			model.getPlayerById(playerId).setBusinessCardRequest(false);
		}
		else
		{
			SupportMethod.giveListOfBonus(model, model.getPlayerById(playerId), city.getBonus());
			model.getPlayerById(playerId).setCityBonusRequest(false);
		}
		
		return true;
	}

	@Override
	public Boolean isPossible(Model model) 
	{
		result = ActionMessages.GENERIC_ERROR;
		if(city == null && card == null)
			return false;
		result = ActionMessages.EVERYTHING_IS_OK;
		if(model.getPlayerById(playerId).isBusinessCardOrCityBonusRequest())
			return true;
		result = ActionMessages.NO_TIME_TO_BONUS;
		return false;
	}

	@Override
	public Reply createReplyMessage(Model model) 
	{
		return new SendFullPlayerReply(model.getCurrentState().getPlayerTurnId(), result, model.getPlayer());
	}

}
