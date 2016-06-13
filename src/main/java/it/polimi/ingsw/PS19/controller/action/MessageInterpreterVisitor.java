package it.polimi.ingsw.ps19.controller.action;

import it.polimi.ingsw.ps19.message.requests.BuyHelperMessage;
import it.polimi.ingsw.ps19.message.requests.BuyMainActionMessage;
import it.polimi.ingsw.ps19.message.requests.ChangeKingPositionMessage;
import it.polimi.ingsw.ps19.message.requests.DrawPoliticsCardMessage;
import it.polimi.ingsw.ps19.message.requests.ElectCouncillorMessage;
import it.polimi.ingsw.ps19.message.requests.EndTurnMessage;
import it.polimi.ingsw.ps19.message.requests.GetBusinessCardMessage;
import it.polimi.ingsw.ps19.message.requests.SendFullGameMessage;
import it.polimi.ingsw.ps19.message.requests.SendOrderMessage;

/**
 * The Interface MessageInterpreterVisitor implements Visitor pattern
 * to only read all message form view e to create action.
 * This class and all implementation don't change the model.
 */
public interface MessageInterpreterVisitor 
{
	
	/**
	 * Visit for ElectCouncillorMessage
	 *
	 * @param message the message
	 * @return the action
	 */
	public Action visit(ElectCouncillorMessage message);
	
	/**
	 * Visit for SendFullGameMessage
	 *
	 * @param message the message
	 * @return the action
	 */
	public Action visit(SendFullGameMessage message);
	
	/**
	 * Visit for BuyHelperMessage
	 *
	 * @param message the message
	 * @return the action
	 */
	public Action visit(BuyHelperMessage message);
	
	/**
	 * Visit for GetBusinessCardMessage
	 *
	 * @param message the message
	 * @return the action
	 */
	public Action visit(GetBusinessCardMessage message);
	
	/**
	 * Visit for DrawPoliticsCardMessage
	 *
	 * @param message the message
	 * @return the action
	 */
	public Action visit(DrawPoliticsCardMessage message);
	
	/**
	 * Visit for EndTurnMessage
	 * (if player don't play the fast action but only main action)
	 *
	 * @param message the message
	 * @return the action
	 */
	public Action visit(EndTurnMessage message);
	
	/**
	 * Visit for ChangeKingPositionMessage
	 *
	 * @param message the message
	 * @return the action
	 */
	public Action visit(ChangeKingPositionMessage message);
	
	/**
	 * Visit for BuyMainActionMessage
	 *
	 * @param message the message
	 * @return the action
	 */
	public Action visit(BuyMainActionMessage message);
	
	/**
	 * Visit for SendOrderMessage
	 *
	 * @param message the message
	 * @return the action
	 */
	public Action visit(SendOrderMessage message);
		
}
