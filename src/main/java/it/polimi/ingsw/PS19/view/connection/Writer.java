/*
 *@Author Andrea Milanta 
 */
package it.polimi.ingsw.PS19.view.connection;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.PS19.exceptions.viewexceptions.SocketWritingException;
import it.polimi.ingsw.PS19.message.Message;
import it.polimi.ingsw.PS19.server.Constants;

/**
 * Abstract class to write a message. 
 * Runs on a different Thread.
 */
public abstract class Writer implements Callable<Integer>
{
	protected static final Logger log = Logger.getLogger("CONNECTION_LOGGER");
	
	protected Message message;
	
	/**
	 * Implementation of call to write
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public Integer call() throws SocketWritingException
	{
		int numOfTries = 0;
		boolean success = false;
		do
		{
			try
			{
				write();
				success = true;
			}
			catch(Exception e)
			{
				log.log(Level.SEVERE, e.toString(), e);
				success = false;
			}
			finally
			{
				numOfTries++;
			}
		}while(!success && numOfTries < Constants.MAX_WRITING_TRIES);
		if(!success) 
			throw new SocketWritingException();
		return numOfTries;
	}
	
	/**
	 * Actual writing method which will be different for different type of communication
	 */
	protected abstract void write() throws IOException;
	
	/**
	 * set Message to be written
	 * @param mex: message
	 */
	public void setMessage(Message mex)
	{
		message = mex;
	}

}
