package it.polimi.ingsw.PS19.client.clientaction;

public class MainAction extends ClientActionChooser 
{	
	public MainAction() 
	{
		actions.add(new BuildEmporiumInputs());
		actions.add(new BuildWithKingInputs());
		actions.add(new ElectCouncillorInputs(true));
		actions.add(new GetBusinessPermitInput());
	}

	@Override
	public String toString() 
	{
		return "Main Action";
	}
}
