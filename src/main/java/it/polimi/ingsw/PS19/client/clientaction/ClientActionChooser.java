package it.polimi.ingsw.PS19.client.clientaction;

import java.util.ArrayList;

import it.polimi.ingsw.PS19.client.ClientUI;
import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;

public abstract class ClientActionChooser
{
	int avail = 1;
	ClientModel model;
	
	public ClientActionChooser(ClientModel m) 
	{
		model = m;
	}
	
	ArrayList<ClientAction> actions = new ArrayList<ClientAction>();
	
	public ClientAction getAction(ClientUI userInterface)
	{
		return userInterface.getAction(this.isPossible());
	}
	
	public ArrayList<ClientAction> isPossible() 
	{
		ArrayList<ClientAction> availableActions = new ArrayList<ClientAction>();
		for(ClientAction a : actions)
		{
			if(a.isPossible())
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
