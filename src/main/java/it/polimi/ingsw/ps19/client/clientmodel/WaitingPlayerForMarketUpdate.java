package it.polimi.ingsw.ps19.client.clientmodel;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.message.requests.Request;

/**
 * Update for beginning of market
 */
public class WaitingPlayerForMarketUpdate extends ClientUpdate 
{
	private String result;
	
	/**
	 * Constructor
	 * @param res
	 */
	public WaitingPlayerForMarketUpdate(String res) 
	{
		result = res;	
	}
	
	@Override
	public void update(ClientModel model, ClientUI userInterface) 
	{
		model.setResult(result);
		userInterface.showNotification(result);
	}

	@Override
	public Request execute(ClientUI userInterface, ClientModel model)
	{
		return null;
	}
}
