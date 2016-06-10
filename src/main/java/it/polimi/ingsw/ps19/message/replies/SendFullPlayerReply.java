package it.polimi.ingsw.ps19.message.replies;

import java.util.List;

import it.polimi.ingsw.ps19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.ps19.client.clientmodel.ReplyVisitor;
import it.polimi.ingsw.ps19.model.Player;

public class SendFullPlayerReply extends Reply
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1117123155364033902L;
	private List<Player> player;
	
	public SendFullPlayerReply(List<Player> p, String s) 
	{
		player = p;
		setResult(s);
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
