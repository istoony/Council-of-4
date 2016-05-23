/*
 * @author Andrea Milanta
 */
package it.polimi.ingsw.PS19.message;

import java.io.Serializable;

/*
 * Abstract class that implements a message
 */
public abstract class Message implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	Integer senderID;
	MessageType type;
	
	public Message(){}
	
	public int getID()
	{
		return senderID;
	}
	
	public void setID(int id)
	{
		senderID = id;
	}
	
	public abstract String getString();
}
