package it.polimi.ingsw.PS19.message.requests;

import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.controller.action.MessageInterpreterVisitor;
import it.polimi.ingsw.PS19.model.Order;

/**
 * MEssage to send player order
 */
public class SendOrderMessage extends Request
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3248022957553827585L;
	private Order order;
	
	/**
	 * Constructor
	 * @param o
	 */
	public SendOrderMessage(Order o) 
	{
		order = o;
	}
	public Order getOrder() 
	{
		return order;
	}

	@Override
	public Action accept(MessageInterpreterVisitor messageInterpreter) 
	{
		return messageInterpreter.visit(this);
	}

}
