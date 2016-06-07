package it.polimi.ingsw.PS19.client;

import it.polimi.ingsw.PS19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.PS19.client.clientmodel.ElectCouncillorUpdate;
import it.polimi.ingsw.PS19.client.clientmodel.SendFullGameUpdate;
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
		return new SendFullGameUpdate(message.getResult(), message.getKing() ,message.getRegions(), message.getPlayer(), message.getActivePlayer());
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
	public ClientUpdate display(ElectCouncillorReply message) 
	{
		if(message.getKing())
			return new ElectCouncillorUpdate(message.getResult(), message.getBalcony(), message.getActivePlayer());
		return new ElectCouncillorUpdate(message.getResult(), message.getBalcony(), message.getRegion(), message.getActivePlayer());
		
	}

	@Override
	public ClientUpdate display(GameStartedMessage message) {
		// TODO Auto-generated method stub
		return null;
	}

}
