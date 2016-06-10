package it.polimi.ingsw.PS19.controller.action;

import java.awt.Color;
import java.util.List;

import it.polimi.ingsw.PS19.message.requests.BuyHelperMessage;
import it.polimi.ingsw.PS19.message.requests.BuyMainActionMessage;
import it.polimi.ingsw.PS19.message.requests.ChangeKingPositionMessage;
import it.polimi.ingsw.PS19.message.requests.DrawPoliticsCardMessage;
import it.polimi.ingsw.PS19.message.requests.ElectCouncillorMessage;
import it.polimi.ingsw.PS19.message.requests.EndTurnMessage;
import it.polimi.ingsw.PS19.message.requests.GetBusinessCardMessage;
import it.polimi.ingsw.PS19.message.requests.SendFullGameMessage;
import it.polimi.ingsw.PS19.message.requests.SendOrderMessage;
import it.polimi.ingsw.PS19.model.card.BusinessCard;
import it.polimi.ingsw.PS19.model.map.City;
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
		return new SendFullGame();
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
		List<Color> politicscard = message.getPoliticsCard();
		return new DrawBusinessCard(playerId, region, card, politicscard);
	}

	@Override
	public Action visit(DrawPoliticsCardMessage message) 
	{
			int playerId = message.getId();
		return new DrawPoliticsCard(playerId);
	}
	@Override
	public Action visit(EndTurnMessage message)
	{
		int playerId = message.getId();
		return new EndTurn(playerId);
	}

	@Override
	public Action visit(ChangeKingPositionMessage message) 
	{
		City c = message.getCity();
		int id = message.getId();
		return new ChangeKingPosition(id, c);
	}

	@Override
	public Action visit(BuyMainActionMessage message) 
	{
		return new BuyMainAction(message.getId());
	}

	@Override
	public Action visit(SendOrderMessage message) 
	{
		return new AddOrder(message.getOrder(), message.getId());
	}

}
