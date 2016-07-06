package it.polimi.ingsw.ps19.message.replies;

import java.util.List;

import it.polimi.ingsw.ps19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.ps19.client.clientmodel.ReplyVisitor;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.map.AvailableCouncillor;
import it.polimi.ingsw.ps19.model.map.King;
import it.polimi.ingsw.ps19.model.map.Region;

/**
 * Response to a elect councillor action
 * Gives the player updated information
 */
public class ElectCouncillorReply extends DrawBusinessCardReply 
{
	private static final long serialVersionUID = -2780649009992940706L;
	
	private King king;
	private AvailableCouncillor availableCouncillor;

	/**
	 * Constructor for message to give the players updates on the game
	 * @param activePlayer
	 * @param result
	 * @param player
	 * @param region
	 * @param king
	 * @param availableCouncillor
	 */
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
