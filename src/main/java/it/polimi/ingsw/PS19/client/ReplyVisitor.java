package it.polimi.ingsw.PS19.client;

import it.polimi.ingsw.PS19.message.replies.SendFullPlayerReply;
import it.polimi.ingsw.PS19.message.replies.DrawBusinessCardReply;
import it.polimi.ingsw.PS19.message.replies.ElectCouncillorReply;
import it.polimi.ingsw.PS19.message.replies.SendFullGameReply;

public interface ReplyVisitor 
{
	public void display(SendFullGameReply message);
	
	public void display(DrawBusinessCardReply message);
	
	public void display(SendFullPlayerReply message);
		
	public void display(ElectCouncillorReply message);
	

}
