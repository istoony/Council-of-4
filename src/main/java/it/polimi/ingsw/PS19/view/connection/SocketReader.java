/*
 * @author Andrea Milanta
 */
package it.polimi.ingsw.PS19.view.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;

import it.polimi.ingsw.PS19.message.Message;


/**
 * Implements Reader for Socket Communication
 */
public class SocketReader extends Reader 
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
			log.log(Level.SEVERE, e.toString(), e);
		}
	}
	
	@Override
	protected Message read() throws ClassNotFoundException, IOException 
	{
		Object obj = reader.readObject();
		if(!(obj instanceof Message))
			return null;
		return (Message)obj;
	}
}
