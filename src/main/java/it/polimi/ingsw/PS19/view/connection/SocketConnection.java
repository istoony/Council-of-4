/*
 * @Author Andrea Milanta
 */
package it.polimi.ingsw.PS19.view.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.*;

import it.polimi.ingsw.PS19.exceptions.viewexceptions.SocketWritingException;
import it.polimi.ingsw.PS19.exceptions.viewexceptions.WriterException;
import it.polimi.ingsw.PS19.message.Message;

/**
 * Class for socket connection
 */
public class SocketConnection extends Connection
{

	private Socket clientSocket;
	private SocketWriter writer;
	private SocketReader reader;
	
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
	
	/*
	 * Runs a callable that writes the message
	 * @see connection.Connection#write(view.Message)
	 */
	@Override
	public Integer write(Message message) throws WriterException
	{
		writer.setMessage(message);
		Integer result = null;
		try {
			result = writer.call();
		} catch (SocketWritingException e) {
			throw new WriterException();
		}
		//Future<Integer> result;
		//result = executor.submit(writer);
		return result;
	}
	
	/*
	 * Runs a callable that reads a message
	 * @see connection.Connection#read()
	 */
	@Override
	public Future<Message> read()
	{
		Future<Message> result = executor.submit(reader);
		return result;
	}
}
