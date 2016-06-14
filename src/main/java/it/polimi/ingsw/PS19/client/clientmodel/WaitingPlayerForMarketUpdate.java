package it.polimi.ingsw.PS19.client.clientmodel;


import it.polimi.ingsw.PS19.client.ClientUI;
import it.polimi.ingsw.PS19.client.clientaction.MarketSell;
import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.message.requests.Request;

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
	public void update(ClientModel model) 
	{
		model.setResult(result);
	}

	@Override
	public Request execute(ClientUI userInterface, ClientModel model)
	{
		boolean valid = false;
		do
		{
			try
			{
				MarketSell market = new MarketSell();
				return market.execute(userInterface);
			}
			catch(InvalidInsertionException e)
			{
				valid = false;
			}
		}while(!valid);
		return null;
	}
}
