package it.polimi.ingsw.PS19.client;

import it.polimi.ingsw.PS19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.PS19.message.replies.DrawBusinessCardReply;
import it.polimi.ingsw.PS19.message.replies.ElectCouncillorReply;
import it.polimi.ingsw.PS19.message.replies.SendFullGameReply;
import it.polimi.ingsw.PS19.message.replies.SendFullPlayerReply;

public class ReplyVisitorImpl implements ReplyVisitor 
{

	@Override
	public ClientUpdate display(SendFullGameReply message) 
	{
		message.getKing()
		message.getPlayer();
		message.getActivePlayer();
		message.getRegions();
		message.getResult();
	}

	@Override
	public ClientUpdate display(DrawBusinessCardReply message) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public ClientUpdate display(SendFullPlayerReply message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ClientUpdate display(ElectCouncillorReply message) {
		// TODO Auto-generated method stub
		
	}

}
