/*
 * @author Andrea Milanta e ha la mamma puttana
 */
package it.polimi.ingsw.PS19.client.clientaction;

import it.polimi.ingsw.PS19.client.ClientUI;
import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.message.requests.Request;

/**
 * Abstract class that will return a message for an action from user inputs and local model
 */
public abstract class ClientAction 
{	
	ClientModel model;
	
	public abstract boolean isPossible();
	
	/**
	 * Creates the message
	 * @param userInterface
	 * @return message
	 * @throws InvalidInsertionException
	 */
	public abstract Request execute(ClientUI userInterface) throws InvalidInsertionException;
	
	protected abstract Request buildMessage();
}
