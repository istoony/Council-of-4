package it.polimi.ingsw.PS19.Client.clientAction;

import java.util.ArrayList;

import it.polimi.ingsw.PS19.Client.ClientUI;
import it.polimi.ingsw.PS19.Client.clientModel.ClientModel;
import it.polimi.ingsw.PS19.message.Message;

public abstract class ClientActionChooser
{
	public abstract ArrayList<ClientAction> isPossible(ClientModel model);
}
