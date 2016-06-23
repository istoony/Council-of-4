package it.polimi.ingsw.ps19.client.clientmodel;

import java.util.List;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.map.King;

/**
 * Class to update king position in model
 */
public class ChangeKingPositionUpdate extends ClientUpdate 
{
	private List<Player> player;
	private King king;
	private String result;
	private int activePlayer;

	/**
	 * Constructor
	 * @param p: List of players
	 * @param k: King
	 * @param s: Result
	 * @param id: activePlayer id
	 */
	public ChangeKingPositionUpdate(List<Player> p, King k, String s, int id) 
	{
		player = p;
		king = k;
		result = s;
		activePlayer = id;
	}
	@Override
	public void update(ClientModel model, ClientUI userInterface) 
	{
		model.setActiveplayer(activePlayer);
		model.setPlayer(player);
		model.setKing(king);
		model.setResult(result);
		userInterface.drawModel(model);
	}

}
