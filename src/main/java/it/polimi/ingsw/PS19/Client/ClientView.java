/*
 * @author Andrea Milanta 
 */
package it.polimi.ingsw.PS19.Client;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import it.polimi.ingsw.PS19.message.Message;
import it.polimi.ingsw.PS19.message.requests.PlayerDisconnectedMessage;
import it.polimi.ingsw.PS19.message.requests.SendFullGameMessage;
import it.polimi.ingsw.PS19.view.connection.Connection;

/*
 * 
 */
public class ClientView extends Observable implements Observer, Runnable
{
	private boolean stop = false;
	Connection connection = null;
	
	public ClientView(Connection conn)
	{
		connection = conn;
	}

	@Override
	public void run() 
	{
		notifyObservers(new SendFullGameMessage(-1));
		while(!stop)
		{
			Future<Message> waitMex = connection.read();
			try 
			{
				Message recMex = waitMex.get();
				notifyObservers(recMex);
			} 
			//General Error. Exit
			catch (InterruptedException | ExecutionException e) 
			{
				e.printStackTrace();
				System.exit(0);
			} 
		}
	}

	@Override
	public void update(Observable o, Object arg)
	{
		//Checks whether the object passed is a message or not
		if(!(arg instanceof Message))
			return;
		Message mex = (Message) arg;

		//The message is forwarded to the clients
		forwardMessage(mex);
	}
	
	/*Forwards message on connection
	 * 
	 */
	public void forwardMessage(Message mex)
	{
		Future<Integer> writeFeedback = connection.write(mex);
		
		//Verify writing success
		try {
			writeFeedback.get();
		} catch (InterruptedException | ExecutionException e) 
		{
			e.printStackTrace();
			notifyObservers(new PlayerDisconnectedMessage(-1));
		}
	}
}
