package it.polimi.ingsw.PS19.message.replies;


import it.polimi.ingsw.PS19.client.ReplyVisitor;
import it.polimi.ingsw.PS19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.map.Balcony;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class ElectCouncillorReply extends Reply
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2780649009992940706L;
	private Balcony balcony;
	private RegionType region = null;
	private boolean king = false;
	private Player player;
	
	public ElectCouncillorReply(Player p, Balcony b, RegionType r) 
	{
		balcony = b;
		player = p;
		region = r;
		king = false;
	}
	
	public ElectCouncillorReply(Player p, Balcony b) 
	{
		balcony = b;
		player = p;
		region = null;
		king = true;
	}
			
	public Player getPlayer() {
		return player;
	}
	
	public Balcony getBalcony() {
		return balcony;
	}
	
	public RegionType getRegion() {
		return region;
	}
	public boolean getKing()
	{
		return king;
	}
	
	@Override
	public ClientUpdate display(ReplyVisitor replyvisitor) 
	{
		return replyvisitor.display(this);	
	}

}
