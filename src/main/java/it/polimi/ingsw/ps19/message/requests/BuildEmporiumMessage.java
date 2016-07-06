package it.polimi.ingsw.ps19.message.requests;

import it.polimi.ingsw.ps19.controller.action.Action;
import it.polimi.ingsw.ps19.controller.action.MessageInterpreterVisitor;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.map.City;

/**
 * Message for a build emporium action
 */
public class BuildEmporiumMessage extends Request 
{
	private static final long serialVersionUID = 364559192321612582L;
	
	private City city;
	private BusinessCard businessCard;
	
	/**
	 * Constructor to build an emporium in city c using the business card b
	 * @param c: city to build the emporia in
	 * @param b: business card to use
	 */
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
