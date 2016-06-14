package it.polimi.ingsw.PS19.client.clientmodel;


import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;

public class WaitingPlayerForMarketUpdate extends ClientUpdate 
{
	private String result;
	
	public WaitingPlayerForMarketUpdate(String res) 
	{
		result = res;
	}
	
	@Override
	public void update(ClientModel model) 
	{
		model.setResult(result);
	}

}
