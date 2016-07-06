package it.polimi.ingsw.ps19.message.replies;

import java.util.List;

import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.map.AvailableCouncillor;
import it.polimi.ingsw.ps19.model.map.King;
import it.polimi.ingsw.ps19.model.map.Region;

/**
 * Message to let the player execute the get Business bonus
 */
public class GetBusinessToDrawReply extends ElectCouncillorReply 
{
	private static final long serialVersionUID = -1444373649730140460L;
	private int howMany;
	
	/**
	 *	Message with all the updated informations about the game
	 * @param activePlayer
	 * @param result
	 * @param player
	 * @param region
	 * @param king
	 * @param availableCouncillor
	 * @param howMany
	 */
	public GetBusinessToDrawReply(int activePlayer, String result, List<Player> player, List<Region> region, King king,
			AvailableCouncillor availableCouncillor, int howMany)
	{
		super(activePlayer, result, player, region, king, availableCouncillor);
		this.howMany = howMany;
	}
	public int getHowMany() 
	{
		return howMany;
	}

}
