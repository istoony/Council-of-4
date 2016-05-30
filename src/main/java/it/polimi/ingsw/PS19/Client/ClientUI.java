package it.polimi.ingsw.PS19.Client;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.PS19.message.Message;
import it.polimi.ingsw.PS19.message.requests.GameStartedMessage;

public abstract class ClientUI extends Observable implements Observer
{
	Integer playerId;
	@Override
	public void update(Observable o, Object arg) 
	{
		if(arg instanceof GameStartedMessage)
			playerId = ((GameStartedMessage) arg).getPlayerNumber();
		else if(arg == null)
			requestAction();
		else if(arg instanceof Message)
			((Message) arg).getString();
		else
			System.out.println("Invalid Object received");
			
	}
	
	protected abstract void requestAction();
}
