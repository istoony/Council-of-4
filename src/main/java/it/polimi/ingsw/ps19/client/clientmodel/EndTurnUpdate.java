package it.polimi.ingsw.ps19.client.clientmodel;

import java.util.List;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.model.Player;

/**
 * Class that updates model to show new turn end
 */
public class EndTurnUpdate extends ClientUpdate 
{
	private int playerTurnId;
	private List<Player> player;
	
	/**
	 * Constructor
	 * @param id: new active player
	 * @param p: list of players;
	 */
	public EndTurnUpdate(int id, List<Player> p) 
	{
		playerTurnId = id;
		player = p;
	}

	@Override
	public void update(ClientModel model, ClientUI userInterface) 
	{
		model.setActiveplayer(playerTurnId);
		model.setPlayer(player);
		userInterface.drawModel(model);
	}

}
