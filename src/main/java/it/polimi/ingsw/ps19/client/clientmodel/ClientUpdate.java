package it.polimi.ingsw.ps19.client.clientmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientaction.ClientAction;
import it.polimi.ingsw.ps19.client.clientaction.ClientActionChooser;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.message.requests.EndTurnMessage;
import it.polimi.ingsw.ps19.message.requests.Request;

/**
 * Class to update local model
 */
public abstract class ClientUpdate
{
	protected static List<ClientActionChooser> typesOfAction = new ArrayList<>();
	protected static final Logger log = Logger.getLogger("CLIENT_LOGGER");
	
	/**
	 * Updates model
	 * @param model: model to be updated
	 */
	public abstract void update(ClientModel model);
	
	/**
	 * Class to get a new message
	 * @param userInterface
	 * @param model: reference to model
	 * @return
	 * @throws InvalidInsertionException 
	 */
	public Request execute(ClientUI userInterface, ClientModel model) throws InvalidInsertionException
	{
		Request mex = null;
		userInterface.showNotification(model.getResult());
		ClientActionChooser actionType = getActionType(userInterface);
		if(actionType == null)
		{
			mex = new EndTurnMessage(); 
		}
		else
		{
			ClientAction action = actionType.getAction(userInterface);
			mex = action.execute(userInterface);
		} 
		return mex;
	}
	
	private ClientActionChooser getActionType(ClientUI userInterface)
	{
		boolean choose = true;
		
		for(ClientActionChooser c : typesOfAction) 
			choose &= c.isPossible();
		if(choose)
			return userInterface.requestActionType(typesOfAction);
		else if(typesOfAction.get(0).isPossible())
			return typesOfAction.get(0);
		else if(typesOfAction.get(1).isPossible())
			return typesOfAction.get(1);
		else
			return null;
	}
	
	/**
	 * Loads the array with the available actions
	 * @param typeOfAction: action to be loaded
	 */
	public static void loadTypeOfAction(ClientActionChooser typeOfAction)
	{
		typesOfAction.add(typeOfAction);
	}
}
