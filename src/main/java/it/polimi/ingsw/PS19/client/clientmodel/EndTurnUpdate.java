package it.polimi.ingsw.PS19.client.clientmodel;

import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;

public class EndTurnUpdate implements ClientUpdate 
{
	int playerTurnId;
	
	public EndTurnUpdate(int id) 
	{
		playerTurnId = id;
	}

	@Override
	public void update(ClientModel model) 
	{
		model.setActiveplayer(playerTurnId);
	}

}
