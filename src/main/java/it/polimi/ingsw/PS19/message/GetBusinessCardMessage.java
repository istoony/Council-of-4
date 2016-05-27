package it.polimi.ingsw.PS19.message;

import java.util.ArrayList;

import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.model.card.BusinessCard;
import it.polimi.ingsw.PS19.model.card.PoliticsCard;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class GetBusinessCardMessage extends Message 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 843031999175292374L;
	private BusinessCard card;
	private RegionType region;
	private ArrayList<PoliticsCard> politicsCard;
	
	public GetBusinessCardMessage(BusinessCard c, RegionType r) 
	{
		card = c;
		region = r;
		this.politicsCard = new ArrayList<>();
	}
	
	public GetBusinessCardMessage(BusinessCard c, RegionType r, ArrayList<PoliticsCard> politicsCard) 
	{
		card = c;
		region = r;
		this.politicsCard = politicsCard;
	}
	
	public void addPoliticCard(PoliticsCard card)
	{
		politicsCard.add(card);
	}
	
	public ArrayList<PoliticsCard> getPoliticsCard() 
	{
		return politicsCard;
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

	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return null;
	}

}
