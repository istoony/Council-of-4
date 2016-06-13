package it.polimi.ingsw.PS19.message;

import java.io.Serializable;

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
