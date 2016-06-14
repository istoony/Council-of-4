package it.polimi.ingsw.PS19.client.clientmodel;

import java.util.logging.Level;

import it.polimi.ingsw.PS19.client.ClientUI;
import it.polimi.ingsw.PS19.client.clientaction.ClientAction;
import it.polimi.ingsw.PS19.client.clientaction.ClientActionChooser;
import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.message.requests.EndTurnMessage;
import it.polimi.ingsw.PS19.message.requests.Request;

/**
 * Interface to update local model
 */
public abstract class ClientUpdate
{
	/**
	 * Updates model
	 * @param model: model to be updated
	 */
	public abstract void update(ClientModel model);
	
	public Request execute(ClientModel model, ClientUI userInterface)
	{
		Request mex = null;
		boolean valid;
		do
		{
			try
			{
				ClientActionChooser actionType = getActionType(userInterface);
				if(actionType == null)
				{
					mex = new EndTurnMessage(); 
					valid = true;
				}
				else
				{
					ClientAction action = actionType.getAction(userInterface);
					mex = action.execute(userInterface);
					mex.setId(playerId);
					valid = true;
				} 
			}
			catch (InvalidInsertionException e) 
			{
				log.log(Level.OFF, e.toString(), e);
				valid = false;
			}
		}while(!valid);
		return mex;
	}
	
	private ClientActionChooser getActionType(ClientUI userInterface)
	{
		boolean choose = true;
		
		for(ClientActionChooser c : typesOfAction) 
			choose &= c.isPossible();
		if(choose)
			return userInterface.requestActionType(typesOfAction);
		else if(mainAction.isPossible())
			return mainAction;
		else if(fastAction.isPossible())
			return fastAction;
		else
			return null;
	}
}
