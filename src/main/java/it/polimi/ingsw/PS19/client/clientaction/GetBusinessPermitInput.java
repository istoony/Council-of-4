package it.polimi.ingsw.PS19.client.clientaction;

import it.polimi.ingsw.PS19.client.ClientUI;
import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.message.requests.Request;

public class GetBusinessPermitInput extends SatisfyCouncilInput 
{

	public GetBusinessPermitInput(ClientModel m) 
	{
		model = m;
	}
	
	@Override
	public boolean isPossible() 
	{
		
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
