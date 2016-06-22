package it.polimi.ingsw.ps19.message.replies;

import it.polimi.ingsw.ps19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.ps19.client.clientmodel.ReplyVisitor;

public class ConnectionReply extends Reply 
{
	private static final long serialVersionUID = 1L;
	private boolean successful;
	private int password;
	
	/**
	 * Connection succesful
	 * @param s
	 * @param k
	 */
	public ConnectionReply(boolean s, int k) 
	{
		super(0, null);
		successful = s;
		password = k;
	}

	@Override
	public ClientUpdate display(ReplyVisitor replyvisitor) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getPassword() 
	{
		return password;
	}

	public boolean getSuccessful()
	{
		return successful;
	}
}
