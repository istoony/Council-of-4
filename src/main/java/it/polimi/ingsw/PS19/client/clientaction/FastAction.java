package it.polimi.ingsw.PS19.client.clientaction;

import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;

public class FastAction extends ClientActionChooser 
{
	
	public FastAction(ClientModel m) 
	{
		super(m);
		actions.add(new RedrawBusinessCardInput(model));
		actions.add(new BuyHelperInputs(model));
		actions.add(new ElectCouncillorInputs(model, false));
		actions.add(new NewMainActionInput(model));	
	}
	
	@Override
	public String toString() 
	{
		return "Fast Action";
	}
}
