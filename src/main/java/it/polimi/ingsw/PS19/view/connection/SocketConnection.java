/*
 * @Author Andrea Milanta
 */
package it.polimi.ingsw.PS19.view.connection;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.*;
import java.util.logging.Level;

import it.polimi.ingsw.PS19.exceptions.viewexceptions.WriterException;
import it.polimi.ingsw.PS19.message.Message;
import it.polimi.ingsw.PS19.server.Constants;

/**
 * Class for socket connection
 */
public class SocketConnection extends Connection
{
	private Socket clientSocket;
	private SocketReader reader;
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
	
	/**
	 * Constructor
	 * @param client: socket
	 * @throws IOException
	 */
	public SocketConnection(Socket client) throws IOException
	{
		clientSocket = client;
		writer = new SocketWriter(clientSocket); 
		reader = new SocketReader(clientSocket);
	}
	
	@Override
	public Integer write(Message message) throws WriterException
	{
		if(status == ConnectionStatus.DISCONNECTED)
			return -1;
		writer.setMessage(message);
		Integer result = null;
		try {
			result = writer.call();
		} catch (WriterException e) 
		{
			log.log(Level.SEVERE, e.toString(), e);
			throw new WriterException();
		}
		return result;
	}

	@Override
	public Message read(long timeOut) throws TimeoutException 
	{
		Future<Message> waitMex = executor.submit(reader);
		Message mex;
		try 
		{
			if(timeOut < 0)
				mex = waitMex.get();
			else
				mex = waitMex.get(Constants.PLAYER_TIMEOUT_TIME_S, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException e) 
		{
			ConnectionLogger.log.log(Level.SEVERE, e.toString(), e);
			throw new TimeoutException();
		}
		return mex;
	}
}
