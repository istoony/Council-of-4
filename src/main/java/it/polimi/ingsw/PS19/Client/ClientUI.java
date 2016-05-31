package it.polimi.ingsw.PS19.Client;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.PS19.message.Message;
import it.polimi.ingsw.PS19.message.StringMessage;
import it.polimi.ingsw.PS19.message.requests.GameStartedMessage;

public abstract class ClientUI extends Observable implements Observer
{
	Integer playerId;
	@Override
	public void update(Observable o, Object arg) 
	{
		if(arg instanceof GameStartedMessage)
		{
			System.out.println(((GameStartedMessage) arg).getString());
			playerId = ((GameStartedMessage) arg).getPlayerNumber();
			setChanged();
			notifyObservers(new StringMessage("Hello this is player " + playerId));
		}
		else if(arg == null)
		{
			System.out.println("Null message arrived");
			requestAction();
		}
		else if(arg instanceof Message)
		{
			System.out.println(((Message) arg).getString());
			
		}
		else
			System.out.println("Invalid Object received");
			
	}
	
	protected abstract void requestAction();
}
