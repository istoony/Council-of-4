package it.polimi.ingsw.ps19.client.clientinput;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.message.requests.BuyHelperMessage;
import it.polimi.ingsw.ps19.message.requests.Request;

/**
 * Class that creates a new BuyHelperMessage from user inputs and local model
 */
public class BuyHelperInputs extends ClientAction 
{

	/**
	 * Constructor
	 * @param m
	 */
	public BuyHelperInputs(ClientModel m) 
	{
		model = m;
	}
	
	@Override
	public boolean isPossible() 
	{
		if(model.getMyPlayer().getMoney() >= 3)
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
		return new BuyHelperMessage();
	}

	@Override
	public String toString(Language l) 
	{
		return l.getString(this);
	}

}
