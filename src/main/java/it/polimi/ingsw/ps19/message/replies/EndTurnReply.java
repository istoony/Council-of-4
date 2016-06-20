package it.polimi.ingsw.ps19.message.replies;

import java.util.List;

import it.polimi.ingsw.ps19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.ps19.client.clientmodel.ReplyVisitor;
import it.polimi.ingsw.ps19.model.Player;

public class EndTurnReply extends SendFullPlayerReply {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8131510543035792307L;
	
	public EndTurnReply(int activePlayer, String result, List<Player> player) 
	{
		super(activePlayer, result, player);
	}
	@Override
	public ClientUpdate display(ReplyVisitor replyvisitor) 
	{
		return replyvisitor.display(this);
	}

}
