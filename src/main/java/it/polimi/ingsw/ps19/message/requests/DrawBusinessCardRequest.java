package it.polimi.ingsw.ps19.message.requests;

import java.util.List;

import it.polimi.ingsw.ps19.controller.action.Action;
import it.polimi.ingsw.ps19.controller.action.MessageInterpreterVisitor;
import it.polimi.ingsw.ps19.model.card.BusinessCard;

public class DrawBusinessCardRequest extends Request 
{
	private List<BusinessCard> chosenCards;
	
	public DrawBusinessCardRequest(List<BusinessCard> cards) 
	{
		chosenCards = cards;
	}
	
	@Override
	public Action accept(MessageInterpreterVisitor messageInterpreter) 
	{
		return null;
	}

}
