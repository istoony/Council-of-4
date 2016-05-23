/*
 * @author Andrea Milanta
 */
package it.polimi.ingsw.PS19.message;

/*
 * Simple message that only contains one String
 */
public class StringMessage extends Message 
{
	private static final long serialVersionUID = 2743693051133944097L;
	
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
