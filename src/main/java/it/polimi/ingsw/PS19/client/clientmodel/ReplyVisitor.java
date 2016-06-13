package it.polimi.ingsw.PS19.client.clientmodel;

import it.polimi.ingsw.PS19.message.replies.ChangeKingPositionReply;
import it.polimi.ingsw.PS19.message.replies.DrawBusinessCardReply;
import it.polimi.ingsw.PS19.message.replies.ElectCouncillorReply;
import it.polimi.ingsw.PS19.message.replies.EndTurnReply;
import it.polimi.ingsw.PS19.message.replies.GameStartedMessage;
import it.polimi.ingsw.PS19.message.replies.PlayerDisconnectedReply;
import it.polimi.ingsw.PS19.message.replies.SendFullGameReply;
import it.polimi.ingsw.PS19.message.replies.SendFullPlayerReply;

public interface ReplyVisitor 
{
	public ClientUpdate display(SendFullGameReply message);
	
	public ClientUpdate display(DrawBusinessCardReply message);
	
	public ClientUpdate display(SendFullPlayerReply message);
		
	public ClientUpdate display(ElectCouncillorReply message);
	
	public ClientUpdate display(GameStartedMessage message);

	public ClientUpdate display(PlayerDisconnectedReply message);
	
	public ClientUpdate display(EndTurnReply message);
	
	public ClientUpdate display(ChangeKingPositionReply message);
	

}
