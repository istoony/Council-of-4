/*
 * @author Andrea Milanta
 */
package it.polimi.ingsw.PS19.view.connection;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
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
	protected void write() throws Exception
	{
		if(message == null) return;
		out.writeObject(message);
		out.flush();
	}

}
