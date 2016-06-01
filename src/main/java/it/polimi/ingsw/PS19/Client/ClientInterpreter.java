package it.polimi.ingsw.PS19.Client;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.PS19.Client.clientAction.ClientAction;
import it.polimi.ingsw.PS19.Client.clientModel.ClientModel;
import it.polimi.ingsw.PS19.message.Message;
import it.polimi.ingsw.PS19.message.NewTurnMessage;
import it.polimi.ingsw.PS19.message.StringMessage;
import it.polimi.ingsw.PS19.message.requests.GameStartedMessage;
import it.polimi.ingsw.PS19.message.requests.RequestActionMessage;

public class ClientInterpreter extends Observable implements Observer
{
	ClientUI userInterface;
	ClientModel model;
	Integer playerId;
	boolean mainDone = false;
	boolean fastDone = false;

	public ClientInterpreter(ClientUI ui) 
	{
		userInterface = ui;
	}
	
	public void setModel(ClientModel model) 
	{
		this.model = model;
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		if(arg instanceof GameStartedMessage)
		{
			playerId = ((GameStartedMessage) arg).getPlayerNumber();
			userInterface.showNotification(((GameStartedMessage) arg).getString());
			setChanged();
			notifyObservers(new StringMessage("Hello this is player " + playerId));
		}
		else if(arg instanceof NewTurnMessage)
		{
			NewTurnMessage mex = (NewTurnMessage) arg;
			if(mex.getActivePlayer() == playerId)
				activatePlayer(mex);
			else
				userInterface.showNotification(mex.getString());
		}
		else if(arg instanceof Message)
			userInterface.showNotification(((Message) arg).getString());
		else
			userInterface.showNotification("Invalid Object received");
			
	}

	private void activatePlayer(NewTurnMessage mex) 
	{
		mainDone = false;
		fastDone = false;
		requestAction();
	}
	
	private Message requestAction()
	{
		ClientAction act;
		if(!mainDone && !fastDone)
			act = userInterface.requestActionType();
		return null;
	}

}
