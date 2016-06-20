package it.polimi.ingsw.PS19.client.clientmodel;

import it.polimi.ingsw.PS19.client.ClientUI;
import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.message.requests.Request;
import it.polimi.ingsw.PS19.model.Market;

public class CompleteMarketUpdate extends ClientUpdate {

	private String result;
	private Market market;
	private int activePlayerId;
	
	public CompleteMarketUpdate(String result, Market market, int activerPlayerId) 
	{
		this.result = result;
		this.market = market;
		this.activePlayerId = activerPlayerId;
	}
	
	public void update(ClientModel model) 
	{
		model.setActiveplayer(activePlayerId);
		model.setResult(result);
		model.setMarket(market);

	}
	@Override
	public Request execute(ClientUI userInterface, ClientModel model) throws InvalidInsertionException {
		// TODO ANDREA SCRIVI L'EXECUTE DEL MARKET
		return super.execute(userInterface, model);
	}

}
