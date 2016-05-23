/*
 * @author Andrea Milanta
 */
package it.polimi.ingsw.PS19.message;

/*
 * Simple message that only contains an ID
 */
public class SimpleMessage extends Message 
{
	private static final long serialVersionUID = -5700330213823620192L;

	public SimpleMessage()
	{
		type = MessageType.SIMPLE;
	}
	
	public String getString()
	{
		return senderID.toString();
	}
}
