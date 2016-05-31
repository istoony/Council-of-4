/*
 * @author Andrea Milanta
 */
package it.polimi.ingsw.PS19.view.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import it.polimi.ingsw.PS19.message.requests.Request;


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
	protected Request read() throws Exception 
	{
		Object obj = (Object)reader.readObject();
		if(!(obj instanceof Request))
			return null;
		Request request = (Request)obj;
		return request;
	}
}
