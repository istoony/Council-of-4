/*
 * @Author Andrea Milanta
 */
package it.polimi.ingsw.ps19.view.connection;

import java.util.concurrent.*;
import java.util.logging.Logger;

import it.polimi.ingsw.ps19.exceptions.viewexceptions.ReaderException;
import it.polimi.ingsw.ps19.exceptions.viewexceptions.WriterException;
import it.polimi.ingsw.ps19.message.Message;

/**
 * Abstract class that covers communication with one user
 */
public abstract class Connection
{
	protected static final Logger log = Logger.getLogger("CONNECTION_LOGGER");
	
	protected ConnectionStatus status;
	protected ExecutorService executor;
	Writer writer;
	
	public void setExecutor(ExecutorService exec)
	{
		executor = exec;
	}
	
	/**
	 * set connection as active
	 */
	public void setActive()
	{
		if(status != ConnectionStatus.DISCONNECTED)
			status = ConnectionStatus.ACTIVE;
	}
	
	/**
	 * set connection as inactive
	 */
	public void setInactive()
	{
		if(status != ConnectionStatus.DISCONNECTED)
			status = ConnectionStatus.INACTIVE;	
	}
	
	/**
	 * set connection ad disconnected
	 */
	public void setDisconnected()
	{
		status = ConnectionStatus.DISCONNECTED;
	}
	
	/**
	 * Reconnect the connection
	 */
	public void reconnect()
	{
		status = ConnectionStatus.INACTIVE;
	}
	
	public ConnectionStatus getStatus()
	{
		return status;
	}
	
	/**
	 * writes message on the network
	 * @param message: message to write
	 * @return Number of tries required to send the message
	 * @throws WriterException
	 */
	public abstract Integer write(Message message) throws WriterException;
	
	/**
	 * Closes connection
	 */
	public abstract void close();
	
	/**
	 * Runs a callable that reads a message
	 * @return
	 * @throws ReaderException 
	 */
	public abstract Message read(long timeOut) throws TimeoutException, InterruptedException, ReaderException;
}
