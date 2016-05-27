package it.polimi.ingsw.PS19.controller.action;

import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.card.BusinessCard;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class GetBusinessCard implements Action 
{

	int playerId;
	RegionType region;
	BusinessCard card;
	Boolean isFirstCard;
	
	public GetBusinessCard(int id, RegionType r, BusinessCard c) 
	{
		playerId = id;
		region = r;
		card = c;
	}
	
	@Override
	public Boolean execute(Model model) 
	{
		BusinessCard card;
		if(isFirstCard)
			card = model.getMap().getRegionByType(region).getFirstcard();
		else if(!isFirstCard)
			card = model.getMap().getRegionByType(region).getSecondcard();
		else 
			return false;
		model.getPlayerById(playerId).addCardToHand(card);
		return true;
	}

	@Override
	public Boolean isPossible(Model model) 
	{
		BusinessCard firstCard = model.getMap().getRegionByType(region).getFirstcard();
		BusinessCard secondCard = model.getMap().getRegionByType(region).getSecondcard();
		
		if(firstCard == card)
		{
			isFirstCard = true;
			return true;
		}
		if(secondCard == card)
		{
			isFirstCard = false;
			return true;
		}
		return false;
	}

}
