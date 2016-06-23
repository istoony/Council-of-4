/*
 * @author Andrea Milanta 
 */
package it.polimi.ingsw.ps19.client;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;

import it.polimi.ingsw.ps19.exceptions.PlayerDisconnectedException;
import it.polimi.ingsw.ps19.exceptions.viewexceptions.ReaderException;
import it.polimi.ingsw.ps19.exceptions.viewexceptions.WriterException;
import it.polimi.ingsw.ps19.message.Message;
import it.polimi.ingsw.ps19.message.requests.Request;
import it.polimi.ingsw.ps19.view.connection.Connection;

/**
 * Manages connection to the server
 */
public class ClientView extends Observable implements Observer, Runnable
{	
	private boolean stop = false;
	Connection connection = null;
		
	/**
	 * @param conn: Connection (Socket or RMI)
	 */
	public ClientView(Connection conn)
	{
		connection = conn;
	}

	@Override
	public void run() 
	{
		while(!stop)
		{
			try
			{
				Message recMex = connection.read(-1);
				setChanged();
				notifyObservers(recMex);
			} 
			catch (TimeoutException | InterruptedException | ReaderException e) 
			{
				setChanged();
				notifyObservers(null);
				ClientLogger.log.log(Level.SEVERE, e.toString(), e);
			} 
		}
		setChanged();
		notifyObservers(null);
	}

	@Override
	public void update(Observable o, Object arg)
	{
		//Checks whether the object passed is a message or not
		if(!(arg instanceof Request))
			return;
		Request mex = (Request) arg;

		//The message is forwarded to the clients
		try {
			forwardMessage(mex);
		} catch (PlayerDisconnectedException e) 
		{
			setChanged();
			notifyObservers(null);
		}
	}
	
	/**
	 * Forwards message on connection
	 * @param mex: message to be forwarded
	 * @throws PlayerDisconnectedException 
	 */
	public void forwardMessage(Request mex) throws PlayerDisconnectedException
	{
		try {
			connection.write(mex);
		} catch (WriterException e) 
		{
			ClientLogger.log.log(Level.SEVERE, e.toString(), e);
			throw new PlayerDisconnectedException(-1);
		}
	}

}
