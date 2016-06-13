package it.polimi.ingsw.PS19.client.clientmodel;

import java.util.List;

import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.model.Player;

/**
 * Class that updates model to show new turn end
 */
public class EndTurnUpdate implements ClientUpdate 
{
	private int playerTurnId;
	private List<Player> player;
	
	/**
	 * Constructor
	 * @param id: new active player
	 */
	public EndTurnUpdate(int id, List<Player> p) 
	{
		playerTurnId = id;
		player = p;
	}

	@Override
	public void update(ClientModel model) 
	{
		model.setActiveplayer(playerTurnId);
		model.setPlayer(player);
	}

}
