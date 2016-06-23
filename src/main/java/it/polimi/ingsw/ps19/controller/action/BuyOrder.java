package it.polimi.ingsw.ps19.controller.action;

import it.polimi.ingsw.ps19.message.replies.CompleteMarketReply;
import it.polimi.ingsw.ps19.message.replies.Reply;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.Order;

public class BuyOrder implements Action 
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
		//Assegno le cose al player
		
		//rimuovo l'ordine dal market
		model.getMarket().removeOrderById(sellerId);
		
		//cambio l'id del player attivo
		model.getCurrentState().setPlayerTurnId(model.getCurrentState().giveNextCorrectId(playerId));
		//imposto nel CurrentState che il player ha acquistato
		model.getCurrentState().playerBought(playerId);
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
		Reply r = new CompleteMarketReply(model.getMarket(), result, model.getCurrentState().getPlayerTurnId());
		//r.setId(model.getCurrentState().getPlayerTurnId());
		return r;
	}

}
