package it.polimi.ingsw.ps19.controller.action;

import it.polimi.ingsw.ps19.message.replies.Reply;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.Order;

public class BuyOrder implements Action 
{
	private int playerId;
	private int sellerId;
	private Order order;
	
	public BuyOrder(int playerId, int sellerId, Order order) 
	{
		this.playerId = playerId;
		this.sellerId = sellerId;
		this.order = order;
	}

	@Override
	public Boolean execute(Model model) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isPossible(Model model) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reply createReplyMessage(Model model) 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
