package it.polimi.ingsw.ps19.client.clientmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.message.requests.BuyOrderMessage;
import it.polimi.ingsw.ps19.message.requests.Request;
import it.polimi.ingsw.ps19.model.Market;
import it.polimi.ingsw.ps19.model.Order;

/**
 * Client updato to buy an order from the market
 */
public class CompleteMarketUpdate extends ClientUpdate {

	private String result;
	private Market market;
	private Order chosenOrder = null;
	private int activePlayerId;
	
	/**
	 * Constructor
	 * @param result: result of previous operation
	 * @param market: market situation
	 * @param activerPlayerId: id of active player
	 */
	public CompleteMarketUpdate(String result, Market market, int activerPlayerId) 
	{
		this.result = result;
		this.market = market;
		this.activePlayerId = activerPlayerId;
	}
	
	@Override
	public void update(ClientModel model, ClientUI userInterface) 
	{
		model.setActiveplayer(activePlayerId);
		model.setResult(result);
		model.setMarket(market);
	}
	
	@Override
	public Request execute(ClientUI userInterface, ClientModel model) throws InvalidInsertionException 
	{
		if(activePlayerId != model.getActiveplayer())
			return null;
		userInterface.showMarket(market);
		List<Order> availableOrders = new ArrayList<>();
		for(Entry<Integer,Order> entry : market.getListoforder().entrySet())
			if(entry.getKey() != model.getMyPlayer().getId() && affordable(entry.getValue(), model))
				availableOrders.add(entry.getValue());
		availableOrders.add(null);
		chosenOrder = userInterface.getOrder(availableOrders);
		if(chosenOrder != null)
			for(Entry<Integer,Order> entry : market.getListoforder().entrySet())
				if(entry.getValue() == chosenOrder)
					return new BuyOrderMessage(chosenOrder, entry.getKey());
		return new BuyOrderMessage(null, 0);
	}
	
	private boolean affordable(Order order, ClientModel model)
	{
		if(order != null && order.getPrice() <= model.getMyPlayer().getMoney())
			return true;
		return false;
	}

}
