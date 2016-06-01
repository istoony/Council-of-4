package it.polimi.ingsw.PS19.Client.clientAction;

import java.util.ArrayList;

import it.polimi.ingsw.PS19.Client.clientModel.ClientModel;

public class MainAction extends ClientActionChooser 
{
	
	@Override
	public ArrayList<ClientAction> isPossible(ClientModel model) 
	{
		return null;
	}

	@Override
	public String toString() 
	{
		return "Main Action";
	}
}
