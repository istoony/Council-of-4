package it.polimi.ingsw.PS19.message;

import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.model.card.BusinessCard;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class GetBusinessCardMessage extends Message 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 843031999175292374L;
	private BusinessCard card;
	private RegionType region;
	
	public GetBusinessCardMessage(BusinessCard c, RegionType r) 
	{
		card = c;
		region = r;
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
