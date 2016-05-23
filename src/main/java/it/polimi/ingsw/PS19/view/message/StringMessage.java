/*
 * @author Andrea Milanta
 */
package it.polimi.ingsw.PS19.view.message;

/*
 * Simple message that only contains one String
 */
public class StringMessage extends Message 
{
	private String line;
	
	public StringMessage(String s)
	{
		line = s;
		type = MessageType.STRING;
	}
	public String getString()
	{
		return line;
	}
}
