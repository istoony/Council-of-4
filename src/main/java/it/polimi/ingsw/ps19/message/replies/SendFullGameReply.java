package it.polimi.ingsw.ps19.message.replies;

import java.util.List;

import it.polimi.ingsw.ps19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.ps19.client.clientmodel.ReplyVisitor;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.map.AvailableCouncillor;
import it.polimi.ingsw.ps19.model.map.King;
import it.polimi.ingsw.ps19.model.map.NobilityPath;
import it.polimi.ingsw.ps19.model.map.Region;

public class SendFullGameReply extends Reply 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4013509319790221079L;
	
	private List<Region> regions;
	private List<Player> player;
	private King king;
	private NobilityPath nobilitypath;
	private AvailableCouncillor availableCouncillor;
	
	public SendFullGameReply(List<Region> regions2, List<Player> p, King k, AvailableCouncillor avc, NobilityPath nob) 
	{
		regions = regions2;
		player = p;
		king = k;
		nobilitypath = nob;
		availableCouncillor = avc;
	}
	
	public List<Region> getRegions() {
		return regions;
	}
	public List<Player> getPlayer() {
		return player;
	}
	public King getKing() {
		return king;
	}
	public AvailableCouncillor getAvailableCouncillor() {
		return availableCouncillor;
	}
	public NobilityPath getNobilitypath() {
		return nobilitypath;
	}
	
	@Override
	public ClientUpdate display(ReplyVisitor replyvisitor) 
	{
		return replyvisitor.display(this);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
