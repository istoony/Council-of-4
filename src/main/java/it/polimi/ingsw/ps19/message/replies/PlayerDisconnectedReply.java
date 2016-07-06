package it.polimi.ingsw.ps19.message.replies;

import java.util.List;

import it.polimi.ingsw.ps19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.ps19.client.clientmodel.ReplyVisitor;
import it.polimi.ingsw.ps19.model.Player;

/**
 * Notifies all the other players that a player has disconnected
 */
public class PlayerDisconnectedReply extends SendFullPlayerReply
{
	private static final long serialVersionUID = -6430324408373788268L;
	
	boolean newTurn;
	
	/**
	 * @param activePlayer: id of active player
	 * @param player: updated list of players and info
	 * @param result
	 * @param newTurn: true <==> turn has changed as a result of the disconnection
	 */
	public PlayerDisconnectedReply(int activePlayer, List<Player> player, String result, boolean newTurn) 
	{
		super(activePlayer, result, player);
		this.newTurn =  newTurn;
	}
	
	public boolean isNewTurn() 
	{
		return newTurn;
	}
	
	@Override
	public ClientUpdate display(ReplyVisitor replyvisitor) 
	{
		return replyvisitor.display(this);
	}

}
