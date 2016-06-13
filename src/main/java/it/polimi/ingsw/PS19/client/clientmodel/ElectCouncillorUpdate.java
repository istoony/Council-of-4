package it.polimi.ingsw.PS19.client.clientmodel;


import java.util.List;

import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.map.AvailableCouncillor;
import it.polimi.ingsw.PS19.model.map.King;
import it.polimi.ingsw.PS19.model.map.Region;

public class ElectCouncillorUpdate implements ClientUpdate
{
	
	private List<Region> regions;
	private King king;
	private AvailableCouncillor availablecouncillor;
	private int activeplayer;
	private List<Player> players;
	private String result;

	public ElectCouncillorUpdate(String res,List<Region> r, King k, AvailableCouncillor ac, List<Player> p, int active) 
	{
		result = res;
		activeplayer = active;
		regions = r;
		availablecouncillor = ac;
		players = p;
		king = k;
	}
	@Override
	public void update(ClientModel model) 
	{
		model.setActiveplayer(activeplayer);
		model.setResult(result);
		model.setKing(king);
		model.setAvailablecouncillor(availablecouncillor);
		model.setRegions(regions);
		model.setPlayer(players);
	}

}
