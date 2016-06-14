package it.polimi.ingsw.PS19.client.clientmodel;

import java.util.List;

import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.map.Region;

/**
 * Class to update Business Cards in model
 */
public class DrawBusinessCardUpdate extends ClientUpdate 
{
	private List<Player> player;
	private List<Region> regions;
	
	/**
	 * Constructtor
	 * @param p: List of players
	 * @param r: List of regions
	 */
	public DrawBusinessCardUpdate(List<Player> p, List<Region> r) 
	{
		player = p;
		regions = r;
	}
	
	@Override
	public void update(ClientModel model) 
	{
		model.setRegions(regions);
		model.setPlayer(player);
	}

}
