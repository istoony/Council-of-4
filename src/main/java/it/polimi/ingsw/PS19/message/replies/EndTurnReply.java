package it.polimi.ingsw.PS19.message.replies;

import java.util.List;

import it.polimi.ingsw.PS19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.PS19.client.clientmodel.ReplyVisitor;
import it.polimi.ingsw.PS19.model.Player;

public class EndTurnReply extends Reply {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8131510543035792307L;
	
	private List<Player> player;
	
	public EndTurnReply(int id, List<Player> p, String s) 
	{
		setResult(s);
		setActivePlayer(id);
		player = p;
	}
	
	public List<Player> getPlayer() 
	{
		return player;
	}

	@Override
	public ClientUpdate display(ReplyVisitor replyvisitor) 
	{
		return replyvisitor.display(this);
	}

}
