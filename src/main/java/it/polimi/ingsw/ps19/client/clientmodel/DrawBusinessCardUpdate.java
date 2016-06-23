package it.polimi.ingsw.ps19.client.clientmodel;

import java.util.List;

import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.map.Region;

/**
 * Class to update Business Cards in model
 */
public class DrawBusinessCardUpdate extends ClientUpdate 
{
	private List<Player> player;
	private List<Region> regions;
	private int activePlayerId;

	/**
	 * Constructtor
	 * @param p: List of players
	 * @param r: List of regions
	 */
	public DrawBusinessCardUpdate(List<Player> p, List<Region> r, int activeId) 
	{
		player = p;
		regions = r;
		activePlayerId = activeId;
	}
	
	@Override
	public void update(ClientModel model) 
	{
		model.setRegions(regions);
		model.setPlayer(player);
		model.setActiveplayer(activePlayerId);
	}

}
