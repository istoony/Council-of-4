package it.polimi.ingsw.ps19.message.replies;

import it.polimi.ingsw.ps19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.ps19.client.clientmodel.ReplyVisitor;

public class PlayerDisconnectedReply extends Reply 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6430324408373788268L;
	
	boolean newTurn;
	
	public PlayerDisconnectedReply(int activePlayer, String result, boolean newTurn) 
	{
		super(activePlayer, result);
		this.newTurn =  newTurn;
	}
	
	public boolean isNewTurn() 
	{
		return newTurn;
	}
	
	@Override
	public ClientUpdate display(ReplyVisitor replyvisitor) 
	{
		return replyvisitor.display(this);
	}

}
