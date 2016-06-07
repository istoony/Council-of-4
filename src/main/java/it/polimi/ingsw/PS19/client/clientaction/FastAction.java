package it.polimi.ingsw.PS19.client.clientaction;

public class FastAction extends ClientActionChooser 
{
	
	public FastAction() 
	{
		actions.add(new BuildEmporiumInputs());
		actions.add(new BuildWithKingInputs());
		actions.add(new ElectCouncillorInputs(false));
		actions.add(new GetBusinessPermitInput());	
	}
	
	@Override
	public String toString() 
	{
		return "Fast Action";
	}
}
