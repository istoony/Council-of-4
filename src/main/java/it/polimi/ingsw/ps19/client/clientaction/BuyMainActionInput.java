package it.polimi.ingsw.ps19.client.clientaction;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.message.requests.BuyMainActionMessage;
import it.polimi.ingsw.ps19.message.requests.Request;

/**
 * Class that creates a new NewMainActionMessage from user inputs and local model
 */
public class BuyMainActionInput extends ClientAction 
{
	/**
	 * Constructor
	 * @param m
	 */
	public BuyMainActionInput(ClientModel m) 
	{
		model = m;
	}

	@Override
	public boolean isPossible() 
	{
		if(model.getMyPlayer().getHelpers() >= 3)
			return true;
		return false;
	}

	@Override
	public Request execute(ClientUI userInterface) throws InvalidInsertionException 
	{
		return buildMessage();
	}

	@Override
	protected Request buildMessage() 
	{
		return new BuyMainActionMessage();
	}

	@Override
	public String toString() 
	{
		return "Buy Main Action";
	}

}
