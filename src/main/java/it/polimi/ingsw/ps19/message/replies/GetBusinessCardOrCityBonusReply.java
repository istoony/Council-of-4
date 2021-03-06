package it.polimi.ingsw.ps19.message.replies;

import java.util.List;

import it.polimi.ingsw.ps19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.ps19.client.clientmodel.ReplyVisitor;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.map.AvailableCouncillor;
import it.polimi.ingsw.ps19.model.map.King;
import it.polimi.ingsw.ps19.model.map.Region;

/**
 * Message to have the player execute the get business or city bonus
 */
public class GetBusinessCardOrCityBonusReply extends ElectCouncillorReply 
{
	private static final long serialVersionUID = -1538547307429331488L;
	private Boolean businessCardBonus;
	private Boolean cityBonus;
	
	/**
	 * Creates a message with all the update informations about the game
	 * @param activePlayer
	 * @param result
	 * @param player
	 * @param region
	 * @param king
	 * @param availableCouncillor
	 * @param businessCardBonus <=!=> @param cityBonus
	 */
	public GetBusinessCardOrCityBonusReply(int activePlayer, String result, List<Player> player, 
			List<Region> region, King king, AvailableCouncillor availableCouncillor, 
			boolean businessCardBonus, boolean cityBonus) 
	{
		super(activePlayer, result, player, region, king, availableCouncillor);
		this.businessCardBonus = businessCardBonus;
		this.cityBonus = cityBonus;
	}
	public Boolean getBusinessCardBonus() {
		return businessCardBonus;
	}
	public Boolean getCityBonus() {
		return cityBonus;
	}
	
	@Override
	public ClientUpdate display(ReplyVisitor replyvisitor) 
	{
		return replyvisitor.display(this);
	}

}
