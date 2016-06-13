package it.polimi.ingsw.PS19.message.requests;

import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.controller.action.MessageInterpreterVisitor;
import it.polimi.ingsw.PS19.model.map.City;

/**
 * Message to move the king
 */
public class ChangeKingPositionMessage extends Request 
{
	private static final long serialVersionUID = -7024597380056383106L;

	private City city;
	
	/**
	 * Constructor
	 * @param c: destination city
	 */
	public ChangeKingPositionMessage(City c) 
	{
		city = c;
	}
	public City getCity() 
	{
		return city;
	}
	
	@Override
	public Action accept(MessageInterpreterVisitor messageInterpreter) {
		return messageInterpreter.visit(this);
	}
}
