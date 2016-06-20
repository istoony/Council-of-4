package it.polimi.ingsw.ps19.client.clientaction;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.message.requests.Request;
import it.polimi.ingsw.ps19.message.requests.SendOrderMessage;
import it.polimi.ingsw.ps19.model.Order;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.card.PoliticsCard;

/**
 * Classe per creare un ordine
 */
public class MarketSell extends ClientAction 
{
	int numberOfHelpers;
	int price;
	List<BusinessCard> businessToSell = new ArrayList<>();
	List<Color> politicToSell = new ArrayList<>();
	
	public MarketSell(ClientModel m) 
	{
		model = m;
	}

	@Override
	public boolean isPossible() 
	{
		return true;
	}

	@Override
	public Request execute(ClientUI userInterface) throws InvalidInsertionException 
	{
		numberOfHelpers = userInterface.getNumberOfHelpers(model.getMyPlayer().getHelpers());
		List<BusinessCard> sellableBusiness = model.getMyPlayer().getFreebusinesscard();
		sellableBusiness.add(null);
		BusinessCard card;
		do
		{
			card = userInterface.getBusiness(sellableBusiness);
			if(card != null)
			{
				businessToSell.add(card);
				sellableBusiness.remove(card);
			}
		}while(card != null);
		List<PoliticsCard> sellablePolitics = model.getMyPlayer().getPoliticcard();
		sellablePolitics.add(null);
		PoliticsCard chosenCard;
		do
		{
			chosenCard = userInterface.getPolitic(sellablePolitics);
			if(chosenCard != null)
			{
				politicToSell.add(chosenCard.getColor());
				sellablePolitics.remove(chosenCard);
			}
		}while(chosenCard != null);
		price = userInterface.getPrice();
		return buildMessage();
	}

	@Override
	protected Request buildMessage() 
	{
		return new SendOrderMessage(new Order(businessToSell, politicToSell, numberOfHelpers, price));
	}

	@Override
	public String toString() 
	{
		return "Sellin:";
	}

}
