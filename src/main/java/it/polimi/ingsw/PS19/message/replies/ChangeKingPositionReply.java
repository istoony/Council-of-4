package it.polimi.ingsw.PS19.message.replies;

import java.util.List;

import it.polimi.ingsw.PS19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.PS19.client.clientmodel.ReplyVisitor;
import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.map.King;

public class ChangeKingPositionReply extends SendFullPlayerReply {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4036516393257537545L;
	private King king;
	
	public ChangeKingPositionReply(int activePlayer, String result, List<Player> player, King king) {
		super(activePlayer, result, player);
		this.king = king;
	}

	public King getKing() {
		return king;
	}
	
	@Override
	public ClientUpdate display(ReplyVisitor replyvisitor) 
	{
		return replyvisitor.display(this);
	}

}
