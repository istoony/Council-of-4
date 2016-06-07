/*
 * @author Andrea Milanta
 */
package it.polimi.ingsw.PS19.view.connection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*
 * Implements Writer for Socket Communication
 */
public class SocketWriter extends Writer 
{
	private ObjectOutputStream out;
	
	public SocketWriter(Socket cs)
	{
	try {
			out = new ObjectOutputStream(cs.getOutputStream());
			out.flush();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * Writes Message
	 * @see connection.Writer#write()
	 */
	@Override
	protected void write()
	{
		if(message == null) return;
		try {
			out.writeObject(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
