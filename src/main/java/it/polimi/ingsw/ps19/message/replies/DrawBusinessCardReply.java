package it.polimi.ingsw.ps19.message.replies;

import java.util.List;

import it.polimi.ingsw.ps19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.ps19.client.clientmodel.ReplyVisitor;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.map.Region;

public class DrawBusinessCardReply extends SendFullPlayerReply
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7817867055750090348L;
	
	private List<Region> region;
	
	public DrawBusinessCardReply(int activePlayer, String result, List<Player> player, List<Region> region) 
	{
		super(activePlayer, result, player);
		this.region = region;
	}
	
	
	public List<Region> getRegion() 
	{
		return region;
	}
	
	@Override
	public ClientUpdate display(ReplyVisitor replyvisitor) 
	{
		return replyvisitor.display(this);
	}


}
