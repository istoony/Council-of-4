package it.polimi.ingsw.PS19.Client.clientAction;

public class FastAction extends ClientActionChooser 
{
	
	public FastAction() 
	{
		actions.add(new BuildEmporiumInputs());
		actions.add(new BuildWithKingInputs());
		actions.add(new ElectCouncillorInputs());
		actions.add(new GetBusinessPermitInput());	
	}
	
	@Override
	public String toString() 
	{
		return "Fast Action";
	}
}
