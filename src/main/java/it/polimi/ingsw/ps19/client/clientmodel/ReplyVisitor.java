package it.polimi.ingsw.ps19.client.clientmodel;

import it.polimi.ingsw.ps19.message.replies.CompleteMarketReply;
import it.polimi.ingsw.ps19.message.replies.DrawBusinessCardReply;
import it.polimi.ingsw.ps19.message.replies.ElectCouncillorReply;
import it.polimi.ingsw.ps19.message.replies.EndGameReply;
import it.polimi.ingsw.ps19.message.replies.EndTurnReply;
import it.polimi.ingsw.ps19.message.replies.GameStartedMessage;
import it.polimi.ingsw.ps19.message.replies.GetBusinessCardOrCityBonusReply;
import it.polimi.ingsw.ps19.message.replies.PlayerDisconnectedReply;
import it.polimi.ingsw.ps19.message.replies.SendFullGameReply;
import it.polimi.ingsw.ps19.message.replies.SendFullPlayerReply;
import it.polimi.ingsw.ps19.message.replies.TimeToMarketReply;
import it.polimi.ingsw.ps19.message.replies.WaitingPlayerForMarketReply;

/**
 * Interface which rapresents the visitor to manage the replies
 */
public interface ReplyVisitor 
{
	/**
	 * return the proper ClientUpdate wrt the message
	 * @param message
	 * @return
	 */
	public ClientUpdate display(SendFullGameReply message);
	
	/**
	 * return the proper ClientUpdate wrt the message
	 * @param message
	 * @return
	 */
	public ClientUpdate display(DrawBusinessCardReply message);
	
	/**
	 * return the proper ClientUpdate wrt the message
	 * @param message
	 * @return
	 */
	public ClientUpdate display(SendFullPlayerReply message);
		
	/**
	 * return the proper ClientUpdate wrt the message
	 * @param message
	 * @return
	 */
	public ClientUpdate display(ElectCouncillorReply message);
	
	/**
	 * return the proper ClientUpdate wrt the message
	 * @param message
	 * @return
	 */
	public ClientUpdate display(GameStartedMessage message);

	/**
	 * return the proper ClientUpdate wrt the message
	 * @param message
	 * @return
	 */
	public ClientUpdate display(PlayerDisconnectedReply message);
	
	/**
	 * return the proper ClientUpdate wrt the message
	 * @param message
	 * @return
	 */
	public ClientUpdate display(EndTurnReply message);
	
	/**
	 * visitor
	 * @param message
	 * @return specific ClientUpdate
	 */
	public ClientUpdate display(TimeToMarketReply message);
	
	/**
	 * visitor
	 * @param message
	 * @return specific ClientUpdate
	 */
	public ClientUpdate display(WaitingPlayerForMarketReply message);
	
	/**
	 * visitor
	 * @param message
	 * @return specific ClientUpdate
	 */
	public ClientUpdate display(GetBusinessCardOrCityBonusReply message);
	
	/**
	 * visitor
	 * @param message
	 * @return specific ClientUpdate
	 */
	public ClientUpdate display(CompleteMarketReply message);
	
	/**
	 * visitor
	 * @param message
	 * @return specific ClientUpdate
	 */
	public ClientUpdate display(EndGameReply message);
}
