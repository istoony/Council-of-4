package it.polimi.ingsw.ps19.client.clientinput;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.client.language.Language;
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
	boolean empty = false;
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
		if(model.getMyPlayer().getHelpers() > 0)
			numberOfHelpers = userInterface.getNumberOfHelpers(model.getMyPlayer().getHelpers());
		else
			numberOfHelpers = 0;
		List<BusinessCard> sellableBusiness = model.getMyPlayer().getFreebusinesscard();
		if(!sellableBusiness.isEmpty())	
		{
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
			}while(card != null && sellableBusiness.size() > 1);
		}
		List<PoliticsCard> sellablePolitics = model.getMyPlayer().getPoliticcard();
		if(!sellablePolitics.isEmpty())
		{
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
			}while(chosenCard != null && sellablePolitics.size() > 1);
		}
		if(!(numberOfHelpers == 0 && businessToSell.isEmpty() && politicToSell.isEmpty()))
		{
			price = userInterface.getPrice();
			if(price < 0)
				throw new InvalidInsertionException();
		}
		else 
			empty = true;
		return buildMessage();
	}

	@Override
	protected Request buildMessage() 
	{
		if(!empty)
			return new SendOrderMessage(new Order(businessToSell, politicToSell, numberOfHelpers, price));
		return new SendOrderMessage(null);
	}

	@Override
	public String toString(Language l) 
	{
		return l.getString(this);
	}

}
