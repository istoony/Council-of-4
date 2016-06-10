package it.polimi.ingsw.ps19.message.replies;

import it.polimi.ingsw.ps19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.ps19.client.clientmodel.ReplyVisitor;

public class WaitingPlayerForMarketReply extends Reply 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6431400927628413970L;
	
	public WaitingPlayerForMarketReply(String result)
	{
		setResult(result);
		
	}

	@Override
	public ClientUpdate display(ReplyVisitor replyvisitor) {
		// TODO Auto-generated method stub
		return null;
	}

}
