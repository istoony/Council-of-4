package it.polimi.ingsw.PS19.client.clientaction;

import it.polimi.ingsw.PS19.client.ClientUI;
import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.message.requests.RedrawBusinessCardMessage;
import it.polimi.ingsw.PS19.message.requests.Request;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

/**
 * Class that creates a new ReDraweBusinessCardMessage from user inputs and local model
 */
public class RedrawBusinessCardInput extends ClientAction 
{
	RegionType region;
	
	/**
	 * Constructor
	 * @param m
	 */
	public RedrawBusinessCardInput(ClientModel m)
	{
		model = m;
	}

	@Override
	public boolean isPossible() 
	{
		if(model.getMyPlayer().getHelpers() >= 1)
			return true;
		return false;
	}

	@Override
	public Request execute(ClientUI userInterface) throws InvalidInsertionException 
	{
		region = userInterface.getRegion();
		return buildMessage();
	}

	@Override
	protected Request buildMessage() 
	{
		return new RedrawBusinessCardMessage(region);
	}

	@Override
	public String toString() 
	{
		return "Redraw Business Card";
	}
}

