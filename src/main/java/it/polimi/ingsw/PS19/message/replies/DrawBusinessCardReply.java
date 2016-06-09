package it.polimi.ingsw.PS19.message.replies;

import java.util.List;

import it.polimi.ingsw.PS19.client.ReplyVisitor;
import it.polimi.ingsw.PS19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.map.Region;

public class DrawBusinessCardReply extends Reply
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7817867055750090348L;
	
	private List<Player> player;
	private List<Region> region;
	
	public DrawBusinessCardReply(List<Player> p, List<Region> r, String result)
	{
		player = p;
		region = r;
		setResult(result);
	}
	
	public List<Player> getPlayer() {
		return player;
	}
	public List<Region> getRegion() {
		return region;
	}
	

	@Override
	public ClientUpdate display(ReplyVisitor replyvisitor) 
	{
		return replyvisitor.display(this);
	}


}
