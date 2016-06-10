package it.polimi.ingsw.PS19.message.replies;


import java.util.List;

import it.polimi.ingsw.PS19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.PS19.client.clientmodel.ReplyVisitor;
import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.map.AvailableCouncillor;
import it.polimi.ingsw.PS19.model.map.Balcony;
import it.polimi.ingsw.PS19.model.map.King;
import it.polimi.ingsw.PS19.model.map.Region;

public class ElectCouncillorReply extends Reply
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2780649009992940706L;
	private Balcony balcony;
	private List<Region> region;
	private King king;
	private List<Player> player;
	private AvailableCouncillor availableCouncillor;
	
	public ElectCouncillorReply(List<Player> p, List<Region> r, AvailableCouncillor ac, King k) 
	{
		player = p;
		region = r;
		availableCouncillor = ac;
		king = k;
	}
				
	public List<Player> getPlayer() {
		return player;
	}
	
	public Balcony getBalcony() {
		return balcony;
	}
	public AvailableCouncillor getAvailableCouncillor() {
		return availableCouncillor;
	}
	public King getKing() {
		return king;
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
