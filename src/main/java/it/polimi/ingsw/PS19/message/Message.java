package it.polimi.ingsw.PS19.message;

import java.io.Serializable;

/**
 * Generic class that rappresents a message that will be sent between client and server
 */
public abstract class Message implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	protected int id;
	
	public Message(){}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
}
