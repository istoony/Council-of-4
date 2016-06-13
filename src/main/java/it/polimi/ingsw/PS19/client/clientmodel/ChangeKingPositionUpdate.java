package it.polimi.ingsw.PS19.client.clientmodel;

import java.util.List;

import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.map.King;

/**
 * Class to update king position in model
 */
public class ChangeKingPositionUpdate implements ClientUpdate 
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
	public void update(ClientModel model) 
	{
		model.setActiveplayer(activePlayer);
		model.setPlayer(player);
		model.setKing(king);
		model.setResult(result);
	}

}
