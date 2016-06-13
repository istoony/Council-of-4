package it.polimi.ingsw.PS19.controller.action;

import it.polimi.ingsw.PS19.message.requests.BuyHelperMessage;
import it.polimi.ingsw.PS19.message.requests.BuyMainActionMessage;
import it.polimi.ingsw.PS19.message.requests.ChangeKingPositionMessage;
import it.polimi.ingsw.PS19.message.requests.DrawPoliticsCardMessage;
import it.polimi.ingsw.PS19.message.requests.ElectCouncillorMessage;
import it.polimi.ingsw.PS19.message.requests.EndTurnMessage;
import it.polimi.ingsw.PS19.message.requests.GetBusinessCardMessage;
import it.polimi.ingsw.PS19.message.requests.SendFullGameMessage;
import it.polimi.ingsw.PS19.message.requests.SendOrderMessage;

/**
 * Visitor interface on server side
 */
public interface MessageInterpreterVisitor 
{
	/**
	 * return the proper Action wrt the message
	 * @param message
	 * @return
	 */
	public Action visit(ElectCouncillorMessage message);
	
	/**
	 * return the proper Action wrt the message
	 * @param message
	 * @return
	 */
	public Action visit(SendFullGameMessage message);
	
	/**
	 * return the proper Action wrt the message
	 * @param message
	 * @return
	 */
	public Action visit(BuyHelperMessage message);
	
	/**
	 * return the proper Action wrt the message
	 * @param message
	 * @return
	 */
	public Action visit(GetBusinessCardMessage message);
	
	/**
	 * return the proper Action wrt the message
	 * @param message
	 * @return
	 */
	public Action visit(DrawPoliticsCardMessage message);
	
	/**
	 * return the proper Action wrt the message
	 * @param message
	 * @return
	 */
	public Action visit(EndTurnMessage message);
	
	/**
	 * return the proper Action wrt the message
	 * @param message
	 * @return
	 */
	public Action visit(ChangeKingPositionMessage message);
	
	/**
	 * return the proper Action wrt the message
	 * @param message
	 * @return
	 */
	public Action visit(BuyMainActionMessage message);
	
	/**
	 * return the proper Action wrt the message
	 * @param message
	 * @return
	 */
	public Action visit(SendOrderMessage message);
}
