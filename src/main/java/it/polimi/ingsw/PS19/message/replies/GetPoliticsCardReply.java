package it.polimi.ingsw.PS19.message.replies;

import it.polimi.ingsw.PS19.Client.ReplyVisitor;
import it.polimi.ingsw.PS19.model.Player;

public class GetPoliticsCardReply extends Reply 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7817867055750090348L;
	
	private Player player;

	public GetPoliticsCardReply(Player p) 
	{
		player = p;
	}
	
	public Player getPlayer() 
	{
		return player;
	}
	@Override
	public void display(ReplyVisitor replyvisitor) 
	{
		return replyvisitor.display(this);

	}

	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return null;
	}

}
