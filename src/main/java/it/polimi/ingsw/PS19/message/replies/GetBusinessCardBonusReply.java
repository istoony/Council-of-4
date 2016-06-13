package it.polimi.ingsw.PS19.message.replies;

import it.polimi.ingsw.PS19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.PS19.client.clientmodel.ReplyVisitor;

public class GetBusinessCardBonusReply extends Reply {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1538547307429331488L;
	
	public GetBusinessCardBonusReply(int id) 
	{
		setId(id);
	}

	@Override
	public ClientUpdate display(ReplyVisitor replyvisitor) {
		// TODO Auto-generated method stub
		return null;
	}

}
