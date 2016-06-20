package it.polimi.ingsw.ps19.client.clientmodel;

import java.util.List;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.message.requests.Request;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.map.AvailableCouncillor;
import it.polimi.ingsw.ps19.model.map.King;
import it.polimi.ingsw.ps19.model.map.Region;

public class GetBusinessCardOrBonusUpdate extends ElectCouncillorUpdate 
{
	

	private boolean getBusinessCardBonus;
	private boolean getCityCardBonus;
	
	public GetBusinessCardOrBonusUpdate(String res, List<Region> r, King k, AvailableCouncillor ac, 
			List<Player> p, int active, boolean getBusinessCardBonus, boolean getCityCardBonus) 
	{
		super(res, r, k, ac, p, active);
		this.getBusinessCardBonus = getBusinessCardBonus;
		this.getCityCardBonus = getCityCardBonus;
	}
	
	@Override
	public Request execute(ClientUI userInterface, ClientModel model) throws InvalidInsertionException 
	{
		// TODO BENE ANDREA. SCRIVI PURE QUESTO METODO
		return null;
	}
}
