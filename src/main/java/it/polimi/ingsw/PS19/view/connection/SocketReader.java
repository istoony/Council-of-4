/*
 * @author Andrea Milanta
 */
package it.polimi.ingsw.PS19.view.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import it.polimi.ingsw.PS19.message.Message;


/*
 * Implements Reader for Socket Communication
 */
public class SocketReader extends Reader 
{
	private ObjectInputStream reader;
	
	public SocketReader(Socket cs)
	{
		try {
			reader = new ObjectInputStream(cs.getInputStream());
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	protected Message read() throws Exception 
	{
		Object obj = reader.readObject();
		if(!(obj instanceof Message))
			return null;
		Message message = (Message)obj;
		return message;
	}
}
