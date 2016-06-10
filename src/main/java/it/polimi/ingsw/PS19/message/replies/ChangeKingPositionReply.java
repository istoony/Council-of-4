package it.polimi.ingsw.PS19.message.replies;

import java.util.List;

import it.polimi.ingsw.PS19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.PS19.client.clientmodel.ReplyVisitor;
import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.map.King;

public class ChangeKingPositionReply extends Reply {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4036516393257537545L;
	private List<Player> player;
	private King king;
	
	public ChangeKingPositionReply(List<Player> p, King k, String res, int activeId)
	{
		player = p;
		king =k;
		setResult(res);
		setActivePlayer(activeId);
	}

	public King getKing() {
		return king;
	}
	public List<Player> getPlayer() {
		return player;
	}
	
	@Override
	public ClientUpdate display(ReplyVisitor replyvisitor) 
	{
		return replyvisitor.display(this);
	}

}
