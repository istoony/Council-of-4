package it.polimi.ingsw.PS19.client.clientaction;

import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;

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
	
	public boolean isPossible() 
	{
		if(model.getMyPlayer().getFastActionCounter() > 0)
			return true;
		else return false;
	}
	
	@Override
	public String toString() 
	{
		return "Fast Action";
	}
}
