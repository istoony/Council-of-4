package it.polimi.ingsw.ps19.client.clientmodel;

import java.util.List;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.model.Player;

/**
 * Class that updates all the players
 */
public class SendFullPlayerUpdate extends ClientUpdate {

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
	public void update(ClientModel model, ClientUI userInterface) 
	{
		model.setPlayer(player);
		model.setResult(result);
		model.setActiveplayer(activePlayer);
		userInterface.drawModel(model);
	}

}
