/*
 * @author Andrea Milanta
 */
package it.polimi.ingsw.PS19.view.message;

/*
 * Simple message that only contains an ID
 */
public class SimpleMessage extends Message 
{
	public SimpleMessage()
	{
		type = MessageType.SIMPLE;
	}
	
	public String getString()
	{
		return ID.toString();
	}
}
