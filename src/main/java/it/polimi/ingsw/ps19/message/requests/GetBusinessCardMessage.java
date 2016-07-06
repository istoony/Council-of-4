package it.polimi.ingsw.ps19.message.requests;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.controller.action.Action;
import it.polimi.ingsw.ps19.controller.action.MessageInterpreterVisitor;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.parameter.Costants;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

public class GetBusinessCardMessage extends Request 
{

	private static final long serialVersionUID = 843031999175292374L;
	private BusinessCard card;
	private RegionType region;
	private List<Color> politicsCard;
	
	public GetBusinessCardMessage(BusinessCard c, RegionType r) 
	{
		card = c;
		region = r;
		this.politicsCard = new ArrayList<>();
	}
	
	public GetBusinessCardMessage(BusinessCard c, RegionType r, List<Color> politicsCard) 
	{
		card = c;
		region = r;
		this.politicsCard = politicsCard;
	}
	
	public void addPoliticCard(Color card)
	{
		politicsCard.add(card);
	}
	
	public List<Color> getPoliticsCard() 
	{
		return Costants.clone(politicsCard);
	}
	
	public BusinessCard getCard() 
	{
		return card;
	}
	
	public RegionType getRegion() 
	{
		return region;
	}
	
	@Override
	public Action accept(MessageInterpreterVisitor messageInterpreter) 
	{
		return messageInterpreter.visit(this);
	}
}
