package it.polimi.ingsw.PS19.message.replies;

import it.polimi.ingsw.PS19.Client.ReplyVisitor;
import it.polimi.ingsw.PS19.model.Player;

public class BuyHelperReply extends Reply
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1117123155364033902L;
	private Player player;
	
	public BuyHelperReply(Player p, String s) 
	{
		player = p;
		setResult(s);
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
