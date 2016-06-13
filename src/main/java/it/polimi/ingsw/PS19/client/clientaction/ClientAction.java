/*
 * @author Andrea Milanta e ha la mamma puttana
 */
package it.polimi.ingsw.PS19.client.clientaction;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.message.requests.Request;

/*
 * 
 */
public abstract class ClientAction 
{	
	ClientModel model;
	
	public abstract boolean isPossible();
	
	public abstract Request Execute(ClientUI userInterface) throws InvalidInsertionException;
	
	protected abstract Request buildMessage();
}
