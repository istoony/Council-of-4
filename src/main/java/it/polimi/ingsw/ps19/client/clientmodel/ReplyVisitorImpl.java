package it.polimi.ingsw.ps19.client.clientmodel;

import it.polimi.ingsw.ps19.message.replies.CompleteMarketReply;
import it.polimi.ingsw.ps19.message.replies.DrawBusinessCardReply;
import it.polimi.ingsw.ps19.message.replies.ElectCouncillorReply;
import it.polimi.ingsw.ps19.message.replies.EndGameReply;
import it.polimi.ingsw.ps19.message.replies.EndTurnReply;
import it.polimi.ingsw.ps19.message.replies.GameStartedMessage;
import it.polimi.ingsw.ps19.message.replies.GetBusinessCardOrCityBonusReply;
import it.polimi.ingsw.ps19.message.replies.PlayerDisconnectedReply;
import it.polimi.ingsw.ps19.message.replies.SendFullGameReply;
import it.polimi.ingsw.ps19.message.replies.SendFullPlayerReply;
import it.polimi.ingsw.ps19.message.replies.TimeToMarketReply;
import it.polimi.ingsw.ps19.message.replies.WaitingPlayerForMarketReply;

/**
 * Implementation of the reply visitor
 */
public class ReplyVisitorImpl implements ReplyVisitor 
{

	@Override
	public ClientUpdate display(SendFullGameReply message) 
	{
		return new SendFullGameUpdate(message.getResult(), message.getKing(), message.getRegion(), message.getPlayer(), message.getActivePlayer(), message.getAvailableCouncillor(), message.getNobilityPath());
	}
	@Override
	public ClientUpdate display(DrawBusinessCardReply message) 
	{
		return new DrawBusinessCardUpdate(message.getPlayer(), message.getRegion(), message.getActivePlayer());
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
	public ClientUpdate display(GameStartedMessage message) 
	{
		return null;
	}
	
	@Override
	public ClientUpdate display(PlayerDisconnectedReply message) {
		return new PlayerDisconnectedUpdate(message.getResult(), message.getPlayer() ,message.getActivePlayer(), message.isNewTurn());
	}
	
	@Override
	public ClientUpdate display(EndTurnReply message) 
	{
		return new EndTurnUpdate(message.getActivePlayer(), message.getPlayer());
	}
	
	@Override
	public ClientUpdate display(TimeToMarketReply message) 
	{
		return new TimeToMarketUpdate();
	}
	@Override
	public ClientUpdate display(WaitingPlayerForMarketReply message) 
	{
		return new WaitingPlayerForMarketUpdate(message.getResult());
	}
	@Override
	public ClientUpdate display(GetBusinessCardOrCityBonusReply message) 
	{
		return new GetBusinessCardOrBonusUpdate(message.getResult(), 
				message.getRegion(), message.getKing(), 
				message.getAvailableCouncillor(),message.getPlayer(), 
				message.getActivePlayer(), message.getBusinessCardBonus(), 
				message.getCityBonus());
	}
	@Override
	public ClientUpdate display(CompleteMarketReply message) 
	{
		return new CompleteMarketUpdate(message.getResult(),
				message.getMarket(),
				message.getActivePlayer());
	}
	@Override
	public ClientUpdate display(EndGameReply message) 
	{
		return new EndGameUpdate(message.getPlayer(), message.getResult());
	}
	
	
}
