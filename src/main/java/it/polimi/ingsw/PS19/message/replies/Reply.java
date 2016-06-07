package it.polimi.ingsw.PS19.message.replies;

import it.polimi.ingsw.PS19.client.ReplyVisitor;
import it.polimi.ingsw.PS19.message.Message;

public abstract class Reply extends Message
{
	private String result;
	
	private static final long serialVersionUID = -1984682021445434304L;
	
	public abstract void display(ReplyVisitor replyvisitor);
	
	public String getResult() 
	{
		return result;
	}
	public void setResult(String result) 
	{
		this.result = result;
	}
}
