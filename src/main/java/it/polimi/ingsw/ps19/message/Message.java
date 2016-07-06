package it.polimi.ingsw.ps19.message;

import java.io.Serializable;

/**
 * Father of all messages between client and server
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
