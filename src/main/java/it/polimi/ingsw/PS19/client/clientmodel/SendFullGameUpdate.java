package it.polimi.ingsw.PS19.client.clientmodel;

import java.util.List;

import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.map.AvailableCouncillor;
import it.polimi.ingsw.PS19.model.map.King;
import it.polimi.ingsw.PS19.model.map.NobilityPath;
import it.polimi.ingsw.PS19.model.map.Region;

/**
 * Class that update to a completely new model
 */
public class SendFullGameUpdate implements ClientUpdate
{
	private King king;
	private List<Region> regions;
	private List<Player> player;
	private int activeplayer;
	private NobilityPath nobilitypath;
	private AvailableCouncillor availablecouncillor;
	private String result;
	
	
	
	/**
	 * Constructor
	 * @param res: result
	 * @param k: king
	 * @param r: list of regions
	 * @param p: List of players
	 * @param active: active player id
	 * @param ac: available councillors
	 * @param nob: nobility path
	 */
	public SendFullGameUpdate(String res, King k, List<Region> r, List<Player> p, int active, AvailableCouncillor ac ,NobilityPath nob) 
	{
		king = k;
		regions =r;
		player = p;
		activeplayer = active;
		result = res;
		nobilitypath = nob;
		availablecouncillor =ac;
	}

	@Override
	public void update(ClientModel model) 
	{
		model.setActiveplayer(activeplayer);
		model.setKing(king);
		model.setPlayer(player);
		model.setRegions(regions);
		model.setResult(result);
		model.setAvailablecouncillor(availablecouncillor);
		model.setNobilitypath(nobilitypath);
		
	}
	
}
