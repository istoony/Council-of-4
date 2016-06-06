package it.polimi.ingsw.PS19.Client;

import it.polimi.ingsw.PS19.message.replies.SendFullGameReply;

public interface ReplyVisitor 
{
	public void display(SendFullGameReply message);
}
