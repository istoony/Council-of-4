package it.polimi.ingsw.PS19.controller.action;

import java.util.Random;

import it.polimi.ingsw.PS19.message.replies.CompleteMarketReply;
import it.polimi.ingsw.PS19.message.replies.Reply;
import it.polimi.ingsw.PS19.message.replies.WaitingPlayerForMarketReply;
import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.Order;

public class AddOrder implements Action 
{

	private Order order;
	private int playerId;
	private String result;
	
	public AddOrder(Order o, int id) 
	{
		order = o;
		playerId = id;
	}
	@Override
	public Boolean execute(Model model) 
	{
		model.getMarket().addOrder(order, playerId);
		result = ActionMessages.EVERYTHING_IS_OK;
		return true;
	}

	@Override
	public Boolean isPossible(Model model) 
	{
		result = ActionMessages.EVERYTHING_IS_OK;
		if(model.getCurrentState().getTimeToMarket())
			return true;
		result = ActionMessages.NO_MARKET_TIME;
		return false;
	}

	@Override
	public String getStringResult() 
	{
		return result;
	}

	@Override
	public Reply createReplyMessage(Model model) 
	{
		if(model.getCurrentState().getTimeToMarket())
			return new WaitingPlayerForMarketReply(result);
		Random r = new Random();
		int idTurn = model.getPlayerById(r.nextInt(model.getNumberofplayer())).getId();
		if(model.getMarket().getSize() == model.getNumberofplayer())
			return new CompleteMarketReply(model.getMarket().getSize(), result + ". Is Time to Buy", idTurn);
		return new WaitingPlayerForMarketReply(result + ". Waiting For Player");
	}

}
