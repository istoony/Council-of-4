/*
 * @author Andrea Milanta 
 */
package it.polimi.ingsw.PS19.Client.clientAction;

import it.polimi.ingsw.PS19.Client.ClientUI;
import it.polimi.ingsw.PS19.Client.clientModel.ClientModel;
import it.polimi.ingsw.PS19.message.Message;

/*
 * 
 */
public abstract class ClientAction 
{	
	public abstract boolean isPossible(ClientModel model);
	
	public abstract Message Execute(ClientUI userInterface);
}
