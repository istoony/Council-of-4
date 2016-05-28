package it.polimi.ingsw.PS19.message;

import java.awt.Color;
import java.util.ArrayList;

import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.controller.action.BuyHelper;
import it.polimi.ingsw.PS19.controller.action.DrawPoliticsCard;
import it.polimi.ingsw.PS19.controller.action.ElectCouncillor;
import it.polimi.ingsw.PS19.controller.action.GetBusinessCard;
import it.polimi.ingsw.PS19.message.requests.SendFullGameMessage;
import it.polimi.ingsw.PS19.model.card.BusinessCard;
import it.polimi.ingsw.PS19.model.card.PoliticsCard;
import it.polimi.ingsw.PS19.model.map.King;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class MessageInterpreterVisitorImp implements MessageInterpreterVisitor {

	@Override
	public Action visit(ElectCouncillorMessage message) 
	{
		int playerId = message.getId();
		RegionType region = message.getRegion();
		Color councicolor =  message.getColor();
		Boolean mainAction = message.getMainAction();
		King king = message.getKing();
		
		if(king != null)
			return new ElectCouncillor(councicolor, playerId, king, mainAction);	
		
		return new ElectCouncillor(councicolor, playerId, region, mainAction);
	}

	@Override
	public Action visit(SendFullGameMessage message) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Action visit(BuyHelperMessage message) 
	{
		int playerId = message.getId();
		return new BuyHelper(playerId);
	}

	@Override
	public Action visit(GetBusinessCardMessage message) 
	{
		BusinessCard card = message.getCard();
		RegionType region = message.getRegion();
		int playerId = message.getId();
		ArrayList<Color> politicscard = message.getPoliticsCard();
		return new GetBusinessCard(playerId, region, card, politicscard);
	}

	@Override
	public Action visit(DrawPoliticsCardMessage message) {
			int playerId = message.getId();
		return new DrawPoliticsCard(playerId);
	}

}
