package it.polimi.ingsw.PS19.message.replies;

import it.polimi.ingsw.PS19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.PS19.client.clientmodel.ReplyVisitor;
import it.polimi.ingsw.PS19.model.Market;

public class CompleteMarketReply extends Reply 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2012056866792056947L;
	private Market market;
	
	public CompleteMarketReply(Market market, String result, int activePlayer) 
	{
		super(activePlayer, result);
		this.market = market;
	}
	
	public Market getMarket() 
	{
		return market;
	}
	@Override
	public ClientUpdate display(ReplyVisitor replyvisitor) 
	{
		return replyvisitor.display(this);
	}

}
