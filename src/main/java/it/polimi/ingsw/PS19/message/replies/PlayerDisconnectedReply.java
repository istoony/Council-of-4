package it.polimi.ingsw.PS19.message.replies;

import it.polimi.ingsw.PS19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.PS19.client.clientmodel.ReplyVisitor;

public class PlayerDisconnectedReply extends Reply 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6430324408373788268L;
	
	public PlayerDisconnectedReply(int activePlayer, String result) 
	{
		super(activePlayer, result);
	}
	
	@Override
	public ClientUpdate display(ReplyVisitor replyvisitor) 
	{
		return replyvisitor.display(this);
	}

}
