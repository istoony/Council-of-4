package it.polimi.ingsw.PS19.message.replies.subreplies;

import java.util.List;

import it.polimi.ingsw.PS19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.PS19.client.clientmodel.ReplyVisitor;
import it.polimi.ingsw.PS19.message.replies.ElectCouncillorReply;
import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.map.AvailableCouncillor;
import it.polimi.ingsw.PS19.model.map.King;
import it.polimi.ingsw.PS19.model.map.Region;

public class GetCityBonusReply extends ElectCouncillorReply
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2110658859199057958L;

	public GetCityBonusReply(List<Player> p, List<Region> r, AvailableCouncillor ac, King k) 
	{
		super(p,r,ac, k);
	}
	
	@Override
	public ClientUpdate display(ReplyVisitor replyvisitor) 
	{
		return replyvisitor.display(this);
	}

}
