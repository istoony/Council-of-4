package it.polimi.ingsw.PS19.client;

import it.polimi.ingsw.PS19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.PS19.message.replies.DrawBusinessCardReply;
import it.polimi.ingsw.PS19.message.replies.ElectCouncillorReply;
import it.polimi.ingsw.PS19.message.replies.GameStartedMessage;
import it.polimi.ingsw.PS19.message.replies.SendFullGameReply;
import it.polimi.ingsw.PS19.message.replies.SendFullPlayerReply;

public class ReplyVisitorImpl implements ReplyVisitor 
{

	@Override
	public ClientUpdate display(SendFullGameReply message) 
	{
		message.getKing();
		message.getPlayer();
		message.getActivePlayer();
		message.getRegions();
		message.getResult();
		return 
	}

	@Override
	public ClientUpdate display(DrawBusinessCardReply message) 
	{
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public ClientUpdate display(SendFullPlayerReply message) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public ClientUpdate display(ElectCouncillorReply message) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public ClientUpdate display(GameStartedMessage message) {
		// TODO Auto-generated method stub
		return null;
	}

}
