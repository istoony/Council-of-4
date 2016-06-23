package it.polimi.ingsw.ps19.client.clientmodel;


import java.util.List;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.map.AvailableCouncillor;
import it.polimi.ingsw.ps19.model.map.King;
import it.polimi.ingsw.ps19.model.map.Region;

/**
 * Class that updates the balconies in model
 */
public class ElectCouncillorUpdate extends ClientUpdate
{
	
	private List<Region> regions;
	private King king;
	private AvailableCouncillor availablecouncillor;
	private int activeplayer;
	private List<Player> players;
	private String result;

	/**
	 * Constructor
	 * @param res: result
	 * @param r: List of regions
	 * @param k: King
	 * @param ac: available councillors
	 * @param p: List of players
	 * @param active: active player id
	 */
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
	public void update(ClientModel model, ClientUI userInterface) 
	{
		model.setActiveplayer(activeplayer);
		model.setResult(result);
		model.setKing(king);
		model.setAvailablecouncillor(availablecouncillor);
		model.setRegions(regions);
		model.setPlayer(players);
		userInterface.drawModel(model);
	}

}
