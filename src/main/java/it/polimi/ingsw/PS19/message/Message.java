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
