package it.polimi.ingsw.ps19.message.requests;

import it.polimi.ingsw.ps19.controller.action.Action;
import it.polimi.ingsw.ps19.controller.action.MessageInterpreterVisitor;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.map.City;

/**
 * Message for sending chosen bonus to server
 */
public class GetBusinessCardOrBonusMessage extends Request 
{
	private static final long serialVersionUID = 1L;

	private City city;
	private BusinessCard card;
	
	public GetBusinessCardOrBonusMessage(City ct, BusinessCard cd) 
	{
		city = ct;
		card = cd;
	}
	
	@Override
	public Action accept(MessageInterpreterVisitor messageInterpreter) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public BusinessCard getCard() 
	{
		return card;
	}
	
	public City getCity() 
	{
		return city;
	}

}
