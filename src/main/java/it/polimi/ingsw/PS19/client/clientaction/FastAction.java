package it.polimi.ingsw.PS19.client.clientaction;

import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;

public class FastAction extends ClientActionChooser 
{
	
	public FastAction(ClientModel m) 
	{
		super(m);
		actions.add(new BuildEmporiumInputs(model));
		actions.add(new BuildWithKingInputs(model));
		actions.add(new ElectCouncillorInputs(model, false));
		actions.add(new GetBusinessPermitInput(model));	
	}
	
	@Override
	public String toString() 
	{
		return "Fast Action";
	}
}
