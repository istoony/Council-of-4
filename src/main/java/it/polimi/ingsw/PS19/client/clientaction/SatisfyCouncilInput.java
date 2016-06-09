package it.polimi.ingsw.PS19.client.clientaction;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.PS19.client.ClientUI;
import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.message.requests.Request;

public class SatisfyCouncilInput extends ClientAction 
{
	public SatisfyCouncilInput(ClientModel m) 
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
