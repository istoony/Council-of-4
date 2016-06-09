package it.polimi.ingsw.PS19.client.clientaction;

import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;

public class MainAction extends ClientActionChooser 
{	
	public MainAction(ClientModel m) 
	{
		super(m);
		actions.add(new BuildEmporiumInputs(model));
		actions.add(new BuildWithKingInputs(model));
		actions.add(new ElectCouncillorInputs(model, true));
		actions.add(new GetBusinessPermitInput(model));
	}

	@Override
	public String toString() 
	{
		return "Main Action";
	}
}
