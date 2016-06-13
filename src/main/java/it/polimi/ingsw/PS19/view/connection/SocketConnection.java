/*
 * @Author Andrea Milanta
 */
package it.polimi.ingsw.PS19.view.connection;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.*;
import java.util.logging.Level;

import it.polimi.ingsw.PS19.exceptions.viewexceptions.SocketWritingException;
import it.polimi.ingsw.PS19.exceptions.viewexceptions.WriterException;
import it.polimi.ingsw.PS19.message.Message;

/**
 * Class for socket connection
 */
public class SocketConnection extends Connection
{
	private Socket clientSocket;
	
	/**
	 * Constructor
	 * @param client: socket
	 * @param exec: executor service to read
	 * @throws IOException
	 */
	public SocketConnection(Socket client, ExecutorService exec) throws IOException
	{
		clientSocket = client;
		writer = new SocketWriter(clientSocket); 
		reader = new SocketReader(clientSocket);
		setExecutor(exec);
	}

	@Override
	public void setActive()
	{
		status = ConnectionStatus.ACTIVE;
	}
	@Override
	public void setInactive()
	{
		status = ConnectionStatus.INACTIVE;
	}
	@Override
	public void setDisconnected()
	{
		status = ConnectionStatus.DISCONNECTED;
	}
	
	@Override
	public Integer write(Message message) throws WriterException
	{
		writer.setMessage(message);
		Integer result = null;
		try {
			result = writer.call();
		} catch (SocketWritingException e) 
		{
			log.log(Level.SEVERE, e.toString(), e);
			throw new WriterException();
		}
		return result;
	}
	
	/**
	 * Runs a callable that reads a message
	 * @see connection.Connection#read()
	 */
	@Override
	public Future<Message> read()
	{
		return executor.submit(reader);
	}
}
