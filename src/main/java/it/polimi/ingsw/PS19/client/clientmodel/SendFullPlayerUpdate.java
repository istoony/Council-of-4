package it.polimi.ingsw.PS19.client.clientmodel;

import java.util.List;

import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.model.Player;

/**
 * Class that updates all the players
 */
public class SendFullPlayerUpdate implements ClientUpdate {

	private List<Player> player;
	private String result;
	private int activePlayer;
	
	/**
	 * Constructor
	 * @param p: List of players
	 * @param res: result
	 * @param ap: active player id
	 */
	public SendFullPlayerUpdate(List<Player> p, String res, int ap) 
	{
		player = p;
		result = res;
		activePlayer = ap;
	}
	@Override
	public void update(ClientModel model) 
	{
		model.setPlayer(player);
		model.setResult(result);
		model.setActiveplayer(activePlayer);

	}

}
