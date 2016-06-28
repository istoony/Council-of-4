package it.polimi.ingsw.ps19.client.clientmodel;

import it.polimi.ingsw.ps19.message.replies.ChangeKingPositionReply;
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
	 * return the proper ClientUpdate wrt the message
	 * @param message
	 * @return
	 */
	public ClientUpdate display(ChangeKingPositionReply message);
	
	public ClientUpdate display(TimeToMarketReply message);
	
	public ClientUpdate display(WaitingPlayerForMarketReply message);

	public ClientUpdate display(GetBusinessCardOrCityBonusReply message);

	public ClientUpdate display(CompleteMarketReply completeMarketReply);
	
	public ClientUpdate display(EndGameReply message);
}
