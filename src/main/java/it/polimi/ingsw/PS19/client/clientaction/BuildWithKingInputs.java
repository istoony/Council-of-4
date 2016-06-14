package it.polimi.ingsw.PS19.client.clientaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polimi.ingsw.PS19.client.ClientUI;
import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.message.requests.Request;
import it.polimi.ingsw.PS19.model.map.City;

/**
 * Class that creates a BuilWithKingMessage from user inputs and local model
 */
public class BuildWithKingInputs extends SatisfyCouncilInput 
{
	/**
	 * Constructor
	 * @param m
	 */
	public BuildWithKingInputs(ClientModel m) 
	{
		model = m;
	}

	@Override
	public boolean isPossible() 
	{
		return false;
	}
	
	@Override
	public Request execute(ClientUI userInterface) throws InvalidInsertionException 
	{
		return null;
	}

	@Override
	protected Request buildMessage() 
	{
		return null;
	}
	
	private Map<City, Integer> getCities()
	{
		List<City> allCities = model.getAllCities();
		Map<City, Integer> availableCities = new HashMap<>();
		for(City city: allCities)
		{
			if(!(model.getMyPlayer().getMyEmporia().contains(city) || city.getEmporia().size() > model.getMyPlayer().getHelpers()))
			{
				
			}
		}
		return availableCities;
	}
}
