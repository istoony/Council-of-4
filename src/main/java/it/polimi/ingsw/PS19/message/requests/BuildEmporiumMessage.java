package it.polimi.ingsw.PS19.message.requests;

import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.controller.action.MessageInterpreterVisitor;
import it.polimi.ingsw.PS19.model.card.BusinessCard;
import it.polimi.ingsw.PS19.model.map.City;

public class BuildEmporiumMessage extends Request {

	/**
	 * 
	 */
	private static final long serialVersionUID = 364559192321612582L;
	
	private City city;
	private BusinessCard businessCard;
	
	public BuildEmporiumMessage(City c, BusinessCard b) 
	{
		city = c;
		businessCard = b;
	}
	
	public BusinessCard getBusinessCard()
	{
		return businessCard;
	}
	public City getCity() {
		return city;
	}

	@Override
	public Action accept(MessageInterpreterVisitor messageInterpreter) 
	{
		return messageInterpreter.visit(this);
	}

}
