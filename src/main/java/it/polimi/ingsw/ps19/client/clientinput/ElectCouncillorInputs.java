package it.polimi.ingsw.ps19.client.clientinput;

import java.awt.Color;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.message.requests.ElectCouncillorMessage;
import it.polimi.ingsw.ps19.message.requests.Request;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

/**
 * Class that creates a new ElectCouncillorMessage from user inputs and local model
 */
public class ElectCouncillorInputs extends ClientAction 
{
	RegionType location;
	boolean mainAction = false;
	Color color;
	
	/**
	 * Constructor
	 * @param m
	 * @param main: true if mainAction
	 */
	public ElectCouncillorInputs(ClientModel m, boolean main)
	{
		model = m;
		mainAction = main;
	}
	
	@Override
	public boolean isPossible() 
	{
		if(model.getMyPlayer().getHelpers() <= 0 && !mainAction)
			return false;
		return true;
	}

	@Override
	public Request execute(ClientUI userInterface) throws InvalidInsertionException 
	{
		location = userInterface.getRegionAndKing();
		color = userInterface.getColor(model.getAvailablecouncillor().getAvailableColors());
		
		return buildMessage();
	}

	@Override
	protected Request buildMessage() 
	{
		ElectCouncillorMessage request;
		if(location == null)
			request =  new ElectCouncillorMessage(color, model.getKing());
		else
			request =  new ElectCouncillorMessage(color, location);
		request.setMainAction(mainAction);
		return request;
	}
	
	@Override
	public String toString(Language l) 
	{
		return l.getString(this);
	}

}
