/*
 * @author Andrea Milanta 
 */
package it.polimi.ingsw.PS19.Client.clientAction;

import java.util.ArrayList;

import it.polimi.ingsw.PS19.Client.ClientUI;
import it.polimi.ingsw.PS19.Client.clientModel.ClientModel;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.message.requests.Request;

/*
 * 
 */
public abstract class ClientAction 
{	
	public abstract boolean isPossible(ClientModel model);
	
	public abstract ArrayList<Object> getUsefulInfo(ClientModel model);
	
	public abstract Request Execute(ClientUI userInterface) throws InvalidInsertionException;
	
	protected abstract Request buildMessage();
}
