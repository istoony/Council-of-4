package it.polimi.ingsw.ps19.controller.action;

import java.awt.Color;

import it.polimi.ingsw.ps19.controller.support.ActionMessages;
import it.polimi.ingsw.ps19.controller.support.SupportMethod;
import it.polimi.ingsw.ps19.message.replies.CompleteMarketReply;
import it.polimi.ingsw.ps19.message.replies.Reply;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.Order;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.card.PoliticsCard;

public class BuyOrder extends SupportMethod implements Action
{
	private int playerId;
	private int sellerId;
	private Order order;
	private String result;
	
	public BuyOrder(int playerId, int sellerId, Order order) 
	{
		this.playerId = playerId;
		this.sellerId = sellerId;
		this.order = order;
	}

	@Override
	public Boolean execute(Model model) 
	{
		if(order!= null)
		{
			Player seller = model.getPlayerById(sellerId);
			Player buyer = model.getPlayerById(playerId);
			
			seller.setHelpers(seller.getHelpers() - order.getHelper());
			buyer.setHelpers(buyer.getHelpers() + order.getHelper());
			
			removePoliticCardToHand(model, seller, order.getPoliticscard());
			for (Color color : order.getPoliticscard())
				buyer.addCardToHand(new PoliticsCard(color));
			
			for(int i = 0; i < order.getBusinesscard().size(); i++)
			{
				seller.removeFreebusinesscardById(order.getBusinesscard().get(i).getId());
				buyer.addCardToHand(order.getBusinesscard().get(i));
			}
			
			seller.setMoney(seller.getMoney() + order.getPrice());
			buyer.setMoney(buyer.getMoney() - order.getPrice());
			
			//rimuovo l'ordine dal market
			//l'ordine esiste solo se order è diverso da null
			model.getMarket().removeOrderById(sellerId);
			
		}
		
		//imposto nel CurrentState che il player ha acquistato
		model.getCurrentState().playerBought(playerId);
		
		//cambio l'id del player attivo
		model.getCurrentState().setPlayerTurnId(model.getCurrentState().giveNextCorrectId(playerId));
		result = ActionMessages.PLAYER_HAS_BOUGHT;
		return true;
	}

	@Override
	public Boolean isPossible(Model model) 
	{
		if(!model.getCurrentState().isTimeToMarket())
		{
			result = ActionMessages.NO_MARKET_TIME;
			return false;
		}
		if(model.getCurrentState().isPlayerBought(playerId))	//controllo se il player ha già acquistato
		{
			result = ActionMessages.YOU_ALREADY_BOUGHT;
			return false;
		}
		return true;
	}
	
	@Override
	public Reply createReplyMessage(Model model) 
	{
		return new CompleteMarketReply(model.getMarket(), result, model.getCurrentState().getPlayerTurnId());
	}

}
