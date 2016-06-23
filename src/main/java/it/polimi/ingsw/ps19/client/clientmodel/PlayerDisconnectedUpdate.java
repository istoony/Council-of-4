package it.polimi.ingsw.ps19.client.clientmodel;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
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
	public void update(ClientModel model, ClientUI userInterface) 
	{
		model.setResult(result);
		model.setActiveplayer(activePlayer);
		if(model.getActiveplayer() == model.getMyPlayer().getId())
			userInterface.drawModel(model);
		userInterface.showNotification(result);
	}
	
	@Override
	public Request execute(ClientUI userInterface, ClientModel model) throws InvalidInsertionException
	{
	//	if(model.getActiveplayer() == model.getMyPlayer().getId())
	//		return super.execute(userInterface, model);
		return null;
	}

}
