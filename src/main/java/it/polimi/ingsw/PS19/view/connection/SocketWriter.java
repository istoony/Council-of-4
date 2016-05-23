/*
 * @author Andrea Milanta
 */
package it.polimi.ingsw.PS19.view.connection;
import java.io.PrintWriter;

/*
 * Implements Writer for Socket Communication
 */
public class SocketWriter extends Writer 
{
	private PrintWriter out;
	
	public SocketWriter(PrintWriter pw)
	{
		out = pw;
	}
	
	/*
	 * Writes Message
	 * @see connection.Writer#write()
	 */
	@Override
	protected void write() throws Exception
	{
		if(message == null) return;
		out.println(message.getString());
	}

}
