package it.polimi.ingsw.PS19.client.clientmodel;

import java.util.List;

import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.map.Region;

public class DrawBusinessCardUpdate implements ClientUpdate 
{
	private List<Player> player;
	private List<Region> regions;
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
