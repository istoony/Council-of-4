package it.polimi.ingsw.PS19.client.clientaction;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polimi.ingsw.PS19.client.ClientUI;
import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.message.requests.ChangeKingPositionMessage;
import it.polimi.ingsw.PS19.message.requests.Request;
import it.polimi.ingsw.PS19.model.map.City;
import it.polimi.ingsw.PS19.model.parameter.Costants;

/**
 * Class that creates a BuilWithKingMessage from user inputs and local model
 */
public class BuildWithKingInputs extends SatisfyCouncilInput 
{
	City city;
	List<Color> colors;
	
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
		if(!getCities().isEmpty() && kingAvailable())
			return true;
		return false;
	}
	
	@Override
	public Request execute(ClientUI userInterface) throws InvalidInsertionException 
	{
		colors = satisfyCouncil(model.getKing().getBalcony(), userInterface);
		city = userInterface.getCity(getCities());
		return buildMessage();
	}

	@Override
	protected Request buildMessage() 
	{
		return new ChangeKingPositionMessage(city, colors);
	}
	
	private Map<City, Integer> getCities()
	{
		List<City> allCities = model.getAllCities();
		Map<City, Integer> availableCities = new HashMap<>();
		for(City city: allCities)
		{
			if(!(model.getMyPlayer().getMyEmporia().contains(city) || city.getEmporia().size() > model.getMyPlayer().getHelpers() || Costants.JUMPCOST * (model.calculateShorterPath(model.getKing().getCurrentcity(), city).size() - 1) > model.getMyPlayer().getMoney()))
				availableCities.put(city, model.calculateShorterPath(model.getKing().getCurrentcity(), city).size() -1);
		}
		return availableCities;
	}
}
