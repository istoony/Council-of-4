package it.polimi.ingsw.ps19.client.clientmodel;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.message.requests.GetBusinessCardOrBonusMessage;
import it.polimi.ingsw.ps19.message.requests.Request;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.map.AvailableCouncillor;
import it.polimi.ingsw.ps19.model.map.City;
import it.polimi.ingsw.ps19.model.map.King;
import it.polimi.ingsw.ps19.model.map.Region;

/**
 * update to choose an extra bonus from cards or/and cities
 */
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
		BusinessCard card = null;
		City city = null;
		
		if(getBusinessCardBonus)
		{	
			if(model.getMyPlayer().getFreebusinesscard().isEmpty() && model.getMyPlayer().getUsedbusinesscard().isEmpty())
				userInterface.showNotification(userInterface.getLanguage().getYouNoBusiness());
			else
			{
				List<BusinessCard> availableCards = new ArrayList<>();
				if(!model.getMyPlayer().getFreebusinesscard().isEmpty())
					availableCards.addAll(model.getMyPlayer().getFreebusinesscard());
				if(!model.getMyPlayer().getUsedbusinesscard().isEmpty())
					availableCards.addAll(model.getMyPlayer().getUsedbusinesscard());
				card = userInterface.getBusiness(availableCards);
			}
		}
		if(getCityCardBonus)
		{
			if(model.getMyPlayer().getMyEmporia().isEmpty())
				userInterface.showNotification(userInterface.getLanguage().getYouNoEmporia());
			else
			{
				List<City> availableCities = new ArrayList<>();
				for(City c : model.getMyPlayer().getMyEmporia())
					if(!c.getBonus().isEmpty())
						availableCities.add(c);
				city = userInterface.getCity(availableCities);
			}
		}
		return new GetBusinessCardOrBonusMessage(city, card);
	}
}
