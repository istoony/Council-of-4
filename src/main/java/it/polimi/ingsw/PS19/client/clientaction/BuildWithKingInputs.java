package it.polimi.ingsw.PS19.client.clientaction;

import it.polimi.ingsw.PS19.client.ClientUI;
import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.message.requests.Request;

public class BuildWithKingInputs extends SatisfyCouncilInput 
{
	public BuildWithKingInputs(ClientModel m) 
	{
		model = m;
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

	@Override
	public boolean isPossible() {
		// TODO Auto-generated method stub
		return false;
	}

}
