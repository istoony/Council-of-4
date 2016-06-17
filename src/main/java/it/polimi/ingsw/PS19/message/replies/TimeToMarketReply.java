package it.polimi.ingsw.PS19.message.replies;

import it.polimi.ingsw.PS19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.PS19.client.clientmodel.ReplyVisitor;

public class TimeToMarketReply extends Reply {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7841881049075109657L;
	
	/**
	 * Send to player that is time to market
	 * @param activePlayer Costants.NO_ACTIVE_PLAYER
	 * @param result is string to send
	 */
	public TimeToMarketReply(int activePlayer, String result) 
	{
		super(activePlayer, result);
	}

	@Override
	public ClientUpdate display(ReplyVisitor replyvisitor) 
	{
		return replyvisitor.display(this);
	}

}
