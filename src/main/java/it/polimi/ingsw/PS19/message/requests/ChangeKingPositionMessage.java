package it.polimi.ingsw.PS19.message.requests;

import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.controller.action.MessageInterpreterVisitor;
import it.polimi.ingsw.PS19.model.map.City;

public class ChangeKingPositionMessage extends Request 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7024597380056383106L;

	private City city;
	
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
