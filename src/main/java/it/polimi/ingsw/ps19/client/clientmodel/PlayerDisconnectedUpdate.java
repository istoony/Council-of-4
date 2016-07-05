package it.polimi.ingsw.ps19.client.clientmodel;

import java.util.List;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.message.requests.Request;
import it.polimi.ingsw.ps19.model.Player;

/**
 * Class that updates the model if a new player has disconnected
 */
public class PlayerDisconnectedUpdate extends ClientUpdate {

	private String result;
	private int activePlayer;
	private List<Player> player;
	private boolean newTurn;
	
	/**
	 * Constructor
	 * @param res
	 * @param player
	 * @param activeP
	 * @param newT: turn has changed
	 */
	public PlayerDisconnectedUpdate(String res, List<Player> player, int activeP, boolean newT) 
	{
		result = res;
		activePlayer = activeP;
		newTurn = newT;
		this.player = player;
	}
	
	@Override
	public void update(ClientModel model, ClientUI userInterface) 
	{
		model.setResult(result);
		model.setActiveplayer(activePlayer);
		model.setPlayer(player);
		if(model.getActiveplayer() == model.getMyPlayer().getId())
			userInterface.drawModel(model);
		userInterface.showNotification(result);
	}
	
	@Override
	public Request execute(ClientUI userInterface, ClientModel model) throws InvalidInsertionException
	{
		if(model.getActiveplayer() == model.getMyPlayer().getId() && newTurn)
			return super.execute(userInterface, model);
		return null;
	}

}
