package it.polimi.ingsw.PS19.Client;

import it.polimi.ingsw.PS19.message.replies.BuyHelperReply;
import it.polimi.ingsw.PS19.message.replies.DrawBusinessCardReply;
import it.polimi.ingsw.PS19.message.replies.GetPoliticsCardReply;
import it.polimi.ingsw.PS19.message.replies.SendFullGameReply;

public interface ReplyVisitor 
{
	public void display(SendFullGameReply message);
	
	public void display(DrawBusinessCardReply message);
	
	public void display(BuyHelperReply message);
	
	public void display(GetPoliticsCardReply message);
	

}
