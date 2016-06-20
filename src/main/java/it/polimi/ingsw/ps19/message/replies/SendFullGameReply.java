package it.polimi.ingsw.PS19.message.replies;

import java.util.List;

import it.polimi.ingsw.PS19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.PS19.client.clientmodel.ReplyVisitor;
import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.map.AvailableCouncillor;
import it.polimi.ingsw.PS19.model.map.King;
import it.polimi.ingsw.PS19.model.map.NobilityPath;
import it.polimi.ingsw.PS19.model.map.Region;

public class SendFullGameReply extends ElectCouncillorReply
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4013509319790221079L;
	
	private NobilityPath nobilityPath;
	
	public SendFullGameReply(int activePlayer, String result, List<Player> player, List<Region> region, King king,
			AvailableCouncillor availableCouncillor, NobilityPath nobilityPath) 
	{
		super(activePlayer, result, player, region, king, availableCouncillor);
		this.nobilityPath = nobilityPath;
	}

	public NobilityPath getNobilityPath() {
		return nobilityPath;
	}
	
	@Override
	public ClientUpdate display(ReplyVisitor replyvisitor) 
	{
		return replyvisitor.display(this);
	}
}
