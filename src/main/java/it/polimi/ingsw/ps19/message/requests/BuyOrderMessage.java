package it.polimi.ingsw.ps19.message.requests;

import it.polimi.ingsw.ps19.controller.action.Action;
import it.polimi.ingsw.ps19.controller.action.MessageInterpreterVisitor;
import it.polimi.ingsw.ps19.model.Order;

/**
 * Message to notify that the player has bought an order
 */
public class BuyOrderMessage extends SendOrderMessage 
{
	private static final long serialVersionUID = -3506589834792172153L;
	private int sellerId;
	
	/**
	 * Constructor 
	 * @param o: order bought
	 * @param sellerId: selling player id
	 */
	public BuyOrderMessage(Order o, int sellerId) 
	{
		super(o);
		this.sellerId =sellerId;
	}
	public int getSellerId() 
	{
		return sellerId;
	}
	
	@Override
	public Action accept(MessageInterpreterVisitor messageInterpreter) 
	{
		return messageInterpreter.visit(this);
	}
}
