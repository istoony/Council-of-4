package it.polimi.ingsw.ps19.client.clientmodel;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.message.requests.Request;

/**
 * Class that updates the model if a new player has disconnected
 */
public class PlayerDisconnectedUpdate extends ClientUpdate {

	private String result;
	private int activePlayer;
	
	/**
	 * Constructor
	 * @param activePlayer 
	 * @param res: result
	 */
	public PlayerDisconnectedUpdate(String res, int activeP) 
	{
		result = res;
		activePlayer = activeP;
	}
	
	@Override
	public void update(ClientModel model) 
	{
		model.setResult(result);
		model.setActiveplayer(activePlayer);
	}
	
	@Override
	public Request execute(ClientUI userInterface, ClientModel model)
	{
		return null;
	}

}
