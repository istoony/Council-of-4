package it.polimi.ingsw.ps19.message.replies;

import java.util.List;

import it.polimi.ingsw.ps19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.ps19.client.clientmodel.ReplyVisitor;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.parameter.Costants;

/**
 * OVERVIEW: The Class SendFullPlayerReply is the most used Reply that add an update version
 * of a list of players.
 */
public class SendFullPlayerReply extends Reply
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1117123155364033902L;
	
	/** The List of all players i a current game. */
	private List<Player> player;
	
	/**
	 * Instantiates a new send full player reply.
	 * This Reply requires all parameter.
	 *
	 * @param activePlayer is the current player turn Id like in {@link Reply} Message
	 * @param result the result is the string result like in {@link Reply} Message
	 * @param player is a list of all player in a current game.
	 */
	public SendFullPlayerReply(int activePlayer, String result, List<Player> player) 
	{
		super(activePlayer, result);
		this.player = player;
	}
	
	/**
	 * Gets the all player.
	 *
	 * @return the player
	 */
	public/*@pure@*/ List<Player> getPlayer() 
	{
		return Costants.clone(player);
	}
	
	/**
	 * this method is used for implements a pattern visitor to read a message
	 * and create an appropriate action on a client
	 */
	@Override
	public ClientUpdate display(ReplyVisitor replyvisitor) 
	{
		return replyvisitor.display(this);
	}

}
