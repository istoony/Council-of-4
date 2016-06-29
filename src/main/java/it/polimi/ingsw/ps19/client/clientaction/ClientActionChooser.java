package it.polimi.ingsw.ps19.client.clientaction;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;

/**
 * Class that rapresent a type of action
 */
public abstract class ClientActionChooser
{
	int avail = 1;
	ClientModel model;
	List<ClientAction> actions = new ArrayList<>();
	
	/**
	 * Constructor
	 * @param m
	 */
	public ClientActionChooser(ClientModel m) 
	{
		model = m;
	}
	
	public abstract boolean isPossible();
		
	/**
	 * Makes the user choose one action from the ones available in the class
	 * @param userInterface
	 * @return clientaction
	 * @throws InvalidInsertionException 
	 */
	public ClientAction getAction(ClientUI userInterface) throws InvalidInsertionException
	{
		return userInterface.getAction(this.possibleActions());
	}
	
	/**
	 * Calculate which actions are available of the one included in the class
	 * @return List of available actions
	 */
	public List<ClientAction> possibleActions() 
	{
		ArrayList<ClientAction> availableActions = new ArrayList<>();
		for(ClientAction a : actions)
		{
			if(a.isPossible())
				availableActions.add(a);
		}
		return availableActions;
	}

	/**
	 * gets to string in the proper language
	 * @param l: language
	 * @return
	 */
	public abstract String toString(Language l);
}
