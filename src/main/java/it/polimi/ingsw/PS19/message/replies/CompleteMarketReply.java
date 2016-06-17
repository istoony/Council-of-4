package it.polimi.ingsw.PS19.message.replies;

import it.polimi.ingsw.PS19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.PS19.client.clientmodel.ReplyVisitor;

public class CompleteMarketReply extends Reply 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2012056866792056947L;
	private int marketSize;
	
	public CompleteMarketReply(int activePlayer, String result, int marketSize) 
	{
		super(activePlayer, result);
		this.marketSize = marketSize;
	}
	
	public int getMarketSize() 
	{
		return marketSize;
	}
	@Override
	public ClientUpdate display(ReplyVisitor replyvisitor) {
		// TODO Auto-generated method stub
		return null;
	}

}
