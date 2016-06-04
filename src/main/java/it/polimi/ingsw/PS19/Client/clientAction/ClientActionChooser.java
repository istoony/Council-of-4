package it.polimi.ingsw.PS19.Client.clientAction;

import java.util.ArrayList;

import it.polimi.ingsw.PS19.Client.ClientUI;
import it.polimi.ingsw.PS19.Client.clientModel.ClientModel;

public abstract class ClientActionChooser
{
	int avail = 0;
	
	ArrayList<ClientAction> actions = new ArrayList<ClientAction>();
	
	public ClientAction getAction(ClientUI userInterface, ClientModel model)
	{
		return userInterface.getAction(this.isPossible(model));
	}
	
	public ArrayList<ClientAction> isPossible(ClientModel model) 
	{
		ArrayList<ClientAction> availableActions = new ArrayList<ClientAction>();
		for(ClientAction a : actions)
		{
			if(a.isPossible(model))
				availableActions.add(a);
		}
		return availableActions;
	}
	
	public void addAvail()
	{
		avail++;
	}
	
	public void subAvail()
	{
		if(avail > 0)
			avail--;
		else
			avail = 0;
	}
	
	public boolean available()
	{
		if(avail > 0)
			return true;
		else
			return false;
	}

	public void resetAvail()
	{
		avail = 1;
	}
}
