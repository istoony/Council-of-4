/*
 * @author Andrea Milanta
 */
package it.polimi.ingsw.PS19.view.message;

/*
 * Abstract class that implements a message
 */
public abstract class Message 
{
	Integer ID;
	MessageType type;
	
	public Message(){}
	
	public int getID()
	{
		return ID;
	}
	
	public void setID(int id)
	{
		ID = id;
	}
	
	public abstract String getString();
}
