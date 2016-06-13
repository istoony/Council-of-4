package it.polimi.ingsw.ps19.message.replies;

import it.polimi.ingsw.ps19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.ps19.client.clientmodel.ReplyVisitor;

public class PlayerDisconnectedReply extends Reply 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6430324408373788268L;
	private int playerId;
	
	public PlayerDisconnectedReply(int id, String res) 
	{
		playerId = id;
		setResult(res);
	}
	
	public int getPlayerId() 
	{
		return playerId;
	}
	
	@Override
	public ClientUpdate display(ReplyVisitor replyvisitor) 
	{
		return replyvisitor.display(this);
	}

}
