package it.polimi.ingsw.PS19.client;

import it.polimi.ingsw.PS19.message.replies.SendFullPlayerReply;
import it.polimi.ingsw.PS19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.PS19.message.replies.DrawBusinessCardReply;
import it.polimi.ingsw.PS19.message.replies.ElectCouncillorReply;
import it.polimi.ingsw.PS19.message.replies.GameStartedMessage;
import it.polimi.ingsw.PS19.message.replies.SendFullGameReply;

public interface ReplyVisitor 
{
	public ClientUpdate display(SendFullGameReply message);
	
	public ClientUpdate display(DrawBusinessCardReply message);
	
	public ClientUpdate display(SendFullPlayerReply message);
		
	public ClientUpdate display(ElectCouncillorReply message);
	
	public ClientUpdate display(GameStartedMessage message);
	

}
