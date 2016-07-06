package it.polimi.ingsw.ps19.controller.action;

import it.polimi.ingsw.ps19.message.requests.BuildEmporiumMessage;
import it.polimi.ingsw.ps19.message.requests.BuyHelperMessage;
import it.polimi.ingsw.ps19.message.requests.BuyMainActionMessage;
import it.polimi.ingsw.ps19.message.requests.BuyOrderMessage;
import it.polimi.ingsw.ps19.message.requests.ChangeKingPositionMessage;
import it.polimi.ingsw.ps19.message.requests.DrawBusinessCardRequest;
import it.polimi.ingsw.ps19.message.requests.ElectCouncillorMessage;
import it.polimi.ingsw.ps19.message.requests.EndTurnMessage;
import it.polimi.ingsw.ps19.message.requests.GetBusinessCardMessage;
import it.polimi.ingsw.ps19.message.requests.GetBusinessCardOrBonusMessage;
import it.polimi.ingsw.ps19.message.requests.PlayerDisconnectedMessage;
import it.polimi.ingsw.ps19.message.requests.RedrawBusinessCardMessage;
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
	
	/**
	 * Visitor for messages
	 * @param message
	 * @return
	 */
	public Action visit(RedrawBusinessCardMessage message);
	
	/**
	 * Visitor for messages
	 * @param message
	 * @return
	 */
	public Action visit(PlayerDisconnectedMessage message);

	/**
	 * Visitor for messages
	 * @param message
	 * @return
	 */
	public Action visit(BuildEmporiumMessage message);
	
	/**
	 * Visitor for messages
	 * @param message
	 * @return
	 */
	public Action visit(BuyOrderMessage message);
	
	/**
	 * Visitor for messages
	 * @param message
	 * @return
	 */
	public Action visit(GetBusinessCardOrBonusMessage message);
	
	/**
	 * Visitor for messages
	 * @param message
	 * @return
	 */
	public Action visit(DrawBusinessCardRequest message);	
}
