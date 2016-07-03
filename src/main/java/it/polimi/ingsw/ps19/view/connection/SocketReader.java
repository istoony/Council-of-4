/*
 * @author Andrea Milanta
 */
package it.polimi.ingsw.ps19.view.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.Callable;

import it.polimi.ingsw.ps19.exceptions.viewexceptions.ReaderException;
import it.polimi.ingsw.ps19.message.Message;


/**
 * Implements Reader for Socket Communication
 */
public class SocketReader implements Callable<Message>
{	
	private ObjectInputStream reader;
	
	/**
	 * Constructor
	 * @param cs: client socket
	 */
	public SocketReader(Socket cs)
	{
		try {
			reader = new ObjectInputStream(cs.getInputStream());
		} catch (IOException e) 
		{
			ConnectionLogger.log.log(e);
		}
	}
	
	protected Message read() throws ClassNotFoundException, IOException 
	{
		Object obj = reader.readObject();
		if(!(obj instanceof Message))
			return null;
		return (Message)obj;
	}
	
	@Override
	public Message call() throws ReaderException
	{
		try
		{
			return read();
		}
		catch(Exception e)
		{
			ConnectionLogger.log.log(e);
			throw new ReaderException();
		}
	}
}
