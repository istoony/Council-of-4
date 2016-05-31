package it.polimi.ingsw.PS19.message.requests;

import it.polimi.ingsw.PS19.controller.action.Action;

public interface MessageInterpreterVisitor 
{
	public Action visit(ElectCouncillorMessage message);
	
	public Action visit(SendFullGameMessage message);
	
	public Action visit(BuyHelperMessage message);
	
	public Action visit(GetBusinessCardMessage message);
	
	public Action visit(DrawPoliticsCardMessage message);
	
	public Action visit(EndTurnMessage message);
		
}
