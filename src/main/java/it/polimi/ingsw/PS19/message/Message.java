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
	
	protected Integer id;
	protected MessageType type;
	
	public Message(){}
	
	public int getID()
	{
		return id;
	}
	
	public void setID(int iD)
	{
		id = iD;
	}
	
	public MessageType getType()
	{
		return type;
	}
	
	public abstract String getString();
}
