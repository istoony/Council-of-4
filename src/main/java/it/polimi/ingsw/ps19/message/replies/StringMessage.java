package it.polimi.ingsw.ps19.message.replies;

import it.polimi.ingsw.ps19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.ps19.client.clientmodel.ReplyVisitor;

public class StringMessage extends Reply 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1555049360643133272L;
	String text;

	public StringMessage(String s) 
	{
		text = s;
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
