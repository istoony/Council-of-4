package it.polimi.ingsw.PS19.client.clientaction;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.message.requests.Request;

public class BuyHelperInputs extends ClientAction 
{

	public BuyHelperInputs(ClientModel m) 
	{
		model = m;
	}
	
	@Override
	public boolean isPossible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Request Execute(ClientUI userInterface) throws InvalidInsertionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Request buildMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
