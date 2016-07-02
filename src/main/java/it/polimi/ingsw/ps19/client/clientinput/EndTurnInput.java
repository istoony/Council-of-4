package it.polimi.ingsw.ps19.client.clientinput;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.message.requests.EndTurnMessage;
import it.polimi.ingsw.ps19.message.requests.Request;

/**
 * Class that creates a new EndTurnMessage
 */
public class EndTurnInput extends ClientAction 
{
	/**
	 * Constructor
	 * @param m
	 */
	public EndTurnInput(ClientModel m) 
	{
		model = m;
	}

	@Override
	public boolean isPossible() 
	{
		if(model.getMyPlayer().getMainActionCounter() <= 0)
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
		return new EndTurnMessage();
	}

	@Override
	public String toString(Language l) 
	{
		return l.getString(this);
	}

}
