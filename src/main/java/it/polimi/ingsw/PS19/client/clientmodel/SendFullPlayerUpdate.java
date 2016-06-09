package it.polimi.ingsw.PS19.client.clientmodel;

import java.util.List;

import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.model.Player;

public class SendFullPlayerUpdate implements ClientUpdate {

	private List<Player> player;
	private String result;
	private int activePlayer;
	
	public SendFullPlayerUpdate(List<Player> p, String res, int ap) 
	{
		player = p;
		result = res;
		activePlayer = ap;
	}
	@Override
	public void update(ClientModel model) 
	{
		model.setPlayer(player);
		model.setResult(result);
		model.setActiveplayer(activePlayer);

	}

}
