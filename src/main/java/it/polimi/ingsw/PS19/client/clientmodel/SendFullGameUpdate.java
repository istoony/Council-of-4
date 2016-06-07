package it.polimi.ingsw.PS19.client.clientmodel;

import java.util.List;

import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.map.King;
import it.polimi.ingsw.PS19.model.map.Region;
import it.polimi.ingsw.PS19.model.parameter.ColorManager;

public class SendFullGameUpdate implements ClientUpdate
{
	private King king;
	private List<Region> regions;
	private ColorManager councilcolors;
	private Player player;
	private int activeplayer;
	
	
	public SendFullGameUpdate(King k, List<Region> r, ColorManager color, Player p, int active) 
	{
		king = k;
		regions =r;
		councilcolors = color;
		player = p;
		activeplayer = active;
		
	}

	@Override
	public void update(ClientModel model) 
	{
		model.setActiveplayer(activeplayer);
		model.setCouncilcolors(councilcolors);
		model.setKing(king);
		model.setPlayer(player);
		model.setRegions(regions);
				
	}
	
}
