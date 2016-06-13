package it.polimi.ingsw.PS19.client.clientmodel;

import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;

/**
 * Class that updates the model if a new player has disconnected
 */
public class PlayerDisconnectedUpdate implements ClientUpdate {

	private String result;
	
	
	/**
	 * Constructor
	 * @param res: result
	 */
	public PlayerDisconnectedUpdate(String res) 
	{
		result = res;
	}
	
	@Override
	public void update(ClientModel model) 
	{
		model.setResult(result);
	}

}
