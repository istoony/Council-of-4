/*
 * @author Andrea Milanta e ha la mamma puttana
 */
package it.polimi.ingsw.PS19.client.clientaction;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.PS19.client.ClientUI;
import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.message.requests.Request;

/*
 * 
 */
public abstract class ClientAction 
{	
	public abstract boolean isPossible(ClientModel model);
	
	public abstract List<Object> getUsefulInfo(ClientModel model);
	
	public abstract Request Execute(ClientUI userInterface) throws InvalidInsertionException;
	
	protected abstract Request buildMessage();
}
