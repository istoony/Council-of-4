package it.polimi.ingsw.ps19.client.clientinput;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.message.requests.BuildEmporiumMessage;
import it.polimi.ingsw.ps19.message.requests.Request;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.map.City;

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
		return new BuildEmporiumMessage(city, card);
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

	@Override
	public String toString(Language l) 
	{
		return l.getString(this);
	}
}
