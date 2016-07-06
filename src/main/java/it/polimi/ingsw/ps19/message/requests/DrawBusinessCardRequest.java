package it.polimi.ingsw.ps19.message.requests;

import java.util.List;

import it.polimi.ingsw.ps19.controller.action.Action;
import it.polimi.ingsw.ps19.controller.action.MessageInterpreterVisitor;
import it.polimi.ingsw.ps19.model.card.BusinessCard;

/**
 * Message to notify of business cards chosen in response of the
 * draw business bonus
 */
public class DrawBusinessCardRequest extends Request 
{
	private static final long serialVersionUID = -8932787830759702149L;
	private List<BusinessCard> chosenCards;
	
	/**
	 * Constructor for a message carrying cards
	 * @param cards: list of business card chosen by the player
	 */
	public DrawBusinessCardRequest(List<BusinessCard> cards) 
	{
		chosenCards = cards;
	}
	
	public List<BusinessCard> getChosenCards() 
	{
		return chosenCards;
	}
	
	@Override
	public Action accept(MessageInterpreterVisitor messageInterpreter) 
	{
		return messageInterpreter.visit(this);
	}

}
