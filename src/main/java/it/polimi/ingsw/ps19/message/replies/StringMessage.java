package it.polimi.ingsw.PS19.message.replies;

import it.polimi.ingsw.PS19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.PS19.client.clientmodel.ReplyVisitor;

public class StringMessage extends Reply 
{
	/**
	 * 
	 */
	
	//TODO REMOVE ME CHE TANTO NON SERVO
	private static final long serialVersionUID = -1555049360643133272L;
	String text;
	
	public StringMessage(String text) 
	{
		super(-1, "");
		this.text = text;
	}


	public String toString() 
	{
		return text;
	}

	@Override
	public ClientUpdate display(ReplyVisitor replyvisitor) {
		
		return null;
		
	}

}