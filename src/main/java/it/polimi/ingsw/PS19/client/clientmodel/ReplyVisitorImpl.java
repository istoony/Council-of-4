package it.polimi.ingsw.PS19.client.clientmodel;

import it.polimi.ingsw.PS19.message.replies.DrawBusinessCardReply;
import it.polimi.ingsw.PS19.message.replies.ElectCouncillorReply;
import it.polimi.ingsw.PS19.message.replies.EndTurnReply;
import it.polimi.ingsw.PS19.message.replies.GameStartedMessage;
import it.polimi.ingsw.PS19.message.replies.PlayerDisconnectedReply;
import it.polimi.ingsw.PS19.message.replies.SendFullGameReply;
import it.polimi.ingsw.PS19.message.replies.SendFullPlayerReply;

public class ReplyVisitorImpl implements ReplyVisitor 
{

	@Override
	public ClientUpdate display(SendFullGameReply message) 
	{
		return new SendFullGameUpdate(message.getResult(), message.getKing(), message.getRegions(), message.getPlayer(), message.getActivePlayer(), message.getAvailableCouncillor(), message.getNobilitypath());
	}
	@Override
	public ClientUpdate display(DrawBusinessCardReply message) 
	{
		return new DrawBusinessCardUpdate(message.getPlayer(), message.getRegion());
	}

	@Override
	public ClientUpdate display(SendFullPlayerReply message) 
	{
		return new SendFullPlayerUpdate(message.getPlayer(), message.getResult(), message.getActivePlayer());		
	}

	@Override
	public ClientUpdate display(ElectCouncillorReply message) 
	{
		return new ElectCouncillorUpdate(message.getResult(), message.getRegion(), message.getKing(), message.getAvailableCouncillor(),message.getPlayer(), message.getActivePlayer());
	}

	@Override
	public ClientUpdate display(GameStartedMessage message) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ClientUpdate display(PlayerDisconnectedReply message) {
		return new PlayerDisconnectedUpdate(message.getResult());
	}
	@Override
	public ClientUpdate display(EndTurnReply message) 
	{
		return new EndTurnUpdate(message.getActivePlayer());
	}

}
