package it.polimi.ingsw.ps19.message.replies;

import it.polimi.ingsw.ps19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.ps19.client.clientmodel.ReplyVisitor;

public class EndTurnReply extends Reply {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8131510543035792307L;
	
	public EndTurnReply(int id, String s) 
	{
		setResult(s);
		setActivePlayer(id);
	}
	

	@Override
	public ClientUpdate display(ReplyVisitor replyvisitor) {
		// TODO Auto-generated method stub
		return null;
	}

}
