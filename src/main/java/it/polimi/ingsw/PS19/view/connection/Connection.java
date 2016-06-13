/*
 * @Author Andrea Milanta
 */
package it.polimi.ingsw.PS19.view.connection;

import java.util.concurrent.*;
import java.util.logging.Logger;

import it.polimi.ingsw.PS19.exceptions.viewexceptions.WriterException;
import it.polimi.ingsw.PS19.message.Message;

/**
 * Abstract class that covers communication with one user
 */
public abstract class Connection
{
	protected static final Logger log = Logger.getLogger("CONNECTION_LOGGER");
	
	protected ConnectionStatus status;
	protected ExecutorService executor;
	Writer writer;
	Reader reader;
	
	public void setExecutor(ExecutorService exec)
	{
		executor = exec;
	}
	
	/**
	 * set connection as active
	 */
	public abstract void setActive();
	
	/**
	 * set connection as inactive
	 */
	public abstract void setInactive();
	
	/**
	 * set connection ad disconnected
	 */
	public abstract void setDisconnected();
	
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
	 * reads message from client
	 * @return Future reference to the message read
	 */
	public abstract Future<Message> read();
}
