package it.polimi.ingsw.PS19.client.clientmodel;

import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;

/**
 * Class that updates model to show new turn end
 */
public class EndTurnUpdate implements ClientUpdate 
{
	int playerTurnId;
	
	/**
	 * Constructor
	 * @param id: new active player
	 */
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
