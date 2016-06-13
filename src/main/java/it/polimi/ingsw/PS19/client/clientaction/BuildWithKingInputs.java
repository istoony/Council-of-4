package it.polimi.ingsw.PS19.client.clientaction;

import it.polimi.ingsw.PS19.client.ClientUI;
import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.message.requests.Request;

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
}
