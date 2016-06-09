package it.polimi.ingsw.PS19.client.clientaction;

import it.polimi.ingsw.PS19.client.ClientUI;
import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.message.requests.Request;

public class BuildEmporiumInputs extends ClientAction 
{
	public BuildEmporiumInputs(ClientModel m) 
	{
		model = m;
	}
	@Override
	public boolean isPossible() 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Request Execute(ClientUI userInterface) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Request buildMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
