package it.polimi.ingsw.PS19.controller.action;

import it.polimi.ingsw.PS19.message.replies.DrawBusinessCardReply;
import it.polimi.ingsw.PS19.message.replies.Reply;
import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.card.BusinessCard;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class RedrawBusinessCard implements Action 
{

	private RegionType region;
	private int playerId;
	private String result;
	
	public RedrawBusinessCard(RegionType r, int id) 
	{
		region = r;
		playerId = id;
	}
	
	@Override
	public Boolean execute(Model model) 
	{
		model.getPlayerById(playerId).setHelpers(model.getPlayerById(playerId).getHelpers() - 1);
		BusinessCard first = model.getMap().getRegionByType(region).getFirstcard();
		BusinessCard second = model.getMap().getRegionByType(region).getSecondcard();
		
		model.getMap().getRegionByType(region).getBusinessdeck().addToDeckRandom(first);
		model.getMap().getRegionByType(region).getBusinessdeck().addToDeckRandom(second);
		
		model.getMap().getRegionByType(region).drowFirstCard();
		model.getMap().getRegionByType(region).drowSecondCard();
		
		model.getPlayerById(playerId).setFastActionCounter(model.getPlayerById(playerId).getFastActionCounter() - 1);
		result = ActionMessages.EVERYTHING_IS_OK;
		return true;
	}

	@Override
	public Boolean isPossible(Model model) 
	{
		result = ActionMessages.NO_HELPERS;
		if(model.getPlayerById(playerId).getHelpers() < 1)
			return false;
		result = ActionMessages.EVERYTHING_IS_OK;
		return true;
	}

	@Override
	public String getStringResult() 
	{
		return result;
	}

	@Override
	public Reply createReplyMessage(Model model) 
	{
		return new DrawBusinessCardReply(model.getPlayer(), model.getMap().getListaRegioni(), result);
	}

}
