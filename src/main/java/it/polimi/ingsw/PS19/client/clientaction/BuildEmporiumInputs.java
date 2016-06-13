package it.polimi.ingsw.PS19.client.clientaction;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.PS19.client.ClientUI;
import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.message.requests.Request;
import it.polimi.ingsw.PS19.model.card.BusinessCard;
import it.polimi.ingsw.PS19.model.map.City;

/**
 * Class that creates a new BuildEmporiumMessage from user inputs and local model
 */
public class BuildEmporiumInputs extends ClientAction 
{
	BusinessCard card;
	City city;
	
	/**
	 * Constructor
	 * @param m: model
	 */
	public BuildEmporiumInputs(ClientModel m) 
	{
		model = m;
	}
	
	@Override
	public boolean isPossible() 
	{
		if(!getAvailableCards(model.getMyPlayer().getFreebusinesscard()).isEmpty())
			return true;
		return false;
	}

	@Override
	public Request execute(ClientUI userInterface) throws InvalidInsertionException 
	{
		card = userInterface.getBusiness(getAvailableCards(model.getMyPlayer().getFreebusinesscard()));
		city = userInterface.getCity(getAvailableCities(card.getCity()));
		return buildMessage();
	}

	@Override
	protected Request buildMessage() 
	{
		//TODO create build emporium message
		return null;
	}
	
	private List<BusinessCard> getAvailableCards(List<BusinessCard> cards)
	{
		List<BusinessCard> availableCards = new ArrayList<>();
		if(cards.isEmpty())
			return availableCards;
		for(BusinessCard cd : cards)
			if(!getAvailableCities(cd.getCity()).isEmpty())
				availableCards.add(cd);
		return availableCards;
	}

	private List<City> getAvailableCities(List<City> cities)
	{
		List<City> availableCities = new ArrayList<>();
		if(cities.isEmpty())
			return availableCities;
		for(City ct : cities)
			if(!model.getMyPlayer().getMyEmporia().contains(ct))
				availableCities.add(ct);
		return availableCities;
	}
}
