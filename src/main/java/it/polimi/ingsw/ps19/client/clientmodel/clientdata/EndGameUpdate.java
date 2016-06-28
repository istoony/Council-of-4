package it.polimi.ingsw.ps19.client.clientmodel.clientdata;

import java.util.List;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.message.requests.EndGameRequest;
import it.polimi.ingsw.ps19.message.requests.Request;
import it.polimi.ingsw.ps19.model.Player;

/**
 * End game update 
 */
public class EndGameUpdate extends ClientUpdate 
{
	List<Player> players;
	String result;
	
 	/**
 	 * Constructor
 	 * @param ps: list of players in winning order
 	 * @param res: result
 	 */
 	public EndGameUpdate(List<Player> ps, String res) 
	{
		players = ps;
		result = res;
	}
	
	@Override
	public void update(ClientModel model, ClientUI userInterface) 
	{
		userInterface.showWinner(players, result);
	}
	
	public Request execute(ClientUI userInterface, ClientModel model) throws InvalidInsertionException
	{
		return new EndGameRequest();
	}

}
