package it.polimi.ingsw.ps19.client.clientmodel;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientinput.MarketSell;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.message.requests.Request;

/**
 * Market selling fase update
 */
public class TimeToMarketUpdate extends ClientUpdate {

	@Override
	public void update(ClientModel model, ClientUI userInterface) 
	{
		return;
	}
	
	@Override
	public Request execute(ClientUI userInterface, ClientModel model) throws InvalidInsertionException
	{
		userInterface.drawModel(model);
		MarketSell marketSell = new MarketSell(model);
		return marketSell.execute(userInterface);
	}
}
