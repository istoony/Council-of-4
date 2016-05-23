/*
 * @author Andrea Milanta
 */
package it.polimi.ingsw.PS19.view.connection;

import java.io.BufferedReader;
import it.polimi.ingsw.PS19.view.message.Message;
import it.polimi.ingsw.PS19.view.message.SimpleMessage;


/*
 * Implements Reader for Socket Communication
 */
public class SocketReader extends Reader 
{
	private BufferedReader reader;
	
	public SocketReader(BufferedReader br)
	{
		reader = br;
	}
	
	@Override
	protected Message read() throws Exception 
	{
		Message message = new SimpleMessage();
		String idString = reader.readLine();
		message.setID(Integer.parseInt(idString));
		return message;
	}
}
