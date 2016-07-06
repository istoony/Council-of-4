package it.polimi.ingsw.ps19.message.replies;

import it.polimi.ingsw.ps19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.ps19.client.clientmodel.ReplyVisitor;

/**
 * Message to wait for the other players to finish their orders
 */
public class WaitingPlayerForMarketReply extends Reply 
{
	private static final long serialVersionUID = -6431400927628413970L;
	
	/**
	 * @param activePlayer
	 * @param result
	 */
	public WaitingPlayerForMarketReply(int activePlayer, String result) {
		super(activePlayer, result);
	}

	@Override
	public ClientUpdate display(ReplyVisitor replyvisitor) 
	{
		return replyvisitor.display(this);
	}

}
