package it.polimi.ingsw.ps19.message.replies;

import java.util.List;

import it.polimi.ingsw.ps19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.ps19.client.clientmodel.ReplyVisitor;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.map.AvailableCouncillor;
import it.polimi.ingsw.ps19.model.map.King;
import it.polimi.ingsw.ps19.model.map.Region;

public class ElectCouncillorReply extends DrawBusinessCardReply
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2780649009992940706L;
	
	private King king;
	private AvailableCouncillor availableCouncillor;

	public ElectCouncillorReply(int activePlayer, String result, List<Player> player, List<Region> region, 
			King king, AvailableCouncillor availableCouncillor) 
	{
		super(activePlayer, result, player, region);
		this.king = king;
		this.availableCouncillor = availableCouncillor;
	}
	
	public King getKing() {
		return king;
	}
	public AvailableCouncillor getAvailableCouncillor() {
		return availableCouncillor;
	}
	
	@Override
	public ClientUpdate display(ReplyVisitor replyvisitor) 
	{
		return replyvisitor.display(this);	
	}

}
