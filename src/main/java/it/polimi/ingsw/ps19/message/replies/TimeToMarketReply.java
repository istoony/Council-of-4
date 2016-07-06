package it.polimi.ingsw.ps19.message.replies;

import it.polimi.ingsw.ps19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.ps19.client.clientmodel.ReplyVisitor;

/**
 * Tells the player it is time to make an order
 */
public class TimeToMarketReply extends Reply 
{
	private static final long serialVersionUID = 7841881049075109657L;
	
	/**
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
