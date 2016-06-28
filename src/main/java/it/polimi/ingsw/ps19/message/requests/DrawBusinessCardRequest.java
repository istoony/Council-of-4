package it.polimi.ingsw.ps19.message.requests;

import java.util.List;

import it.polimi.ingsw.ps19.controller.action.Action;
import it.polimi.ingsw.ps19.controller.action.MessageInterpreterVisitor;
import it.polimi.ingsw.ps19.model.card.BusinessCard;

public class DrawBusinessCardRequest extends Request 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8932787830759702149L;
	private List<BusinessCard> chosenCards;
	
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
