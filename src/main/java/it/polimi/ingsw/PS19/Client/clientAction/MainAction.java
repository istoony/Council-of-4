package it.polimi.ingsw.PS19.Client.clientAction;

public class MainAction extends ClientActionChooser 
{	
	public MainAction() 
	{
		actions.add(new BuildEmporiumInputs());
		actions.add(new BuildWithKingInputs());
		actions.add(new ElectCouncillorInputs());
		actions.add(new GetBusinessPermitInput());
	}

	@Override
	public String toString() 
	{
		return "Main Action";
	}
}
