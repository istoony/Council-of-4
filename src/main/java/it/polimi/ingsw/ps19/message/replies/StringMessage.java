package it.polimi.ingsw.ps19.message.replies;

import it.polimi.ingsw.ps19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.ps19.client.clientmodel.ReplyVisitor;

/**
 * The Class StringMessage is used to send only a string message.
 * 
 */
public class StringMessage extends Reply 
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1555049360643133272L;
	
	/** The text of a message. */
	String text;
	
	/**
	 * Instantiates a new string message.
	 *
	 * @param text is the string message of a Reply
	 */
	public StringMessage(String text) 
	{
		super(-1, "");
		this.text = text;
	}

	/**
	 * This method return a current string text
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		return text;
	}

	/**
	 * this method is used for implements a pattern visitor to read a message
	 * and create an appropriate action on a client.
	 * In this case a string message don't have a specific acton.
	 */
	@Override
	public ClientUpdate display(ReplyVisitor replyvisitor) {
		
		return null;
		
	}

}