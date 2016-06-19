package it.polimi.ingsw.PS19.model.card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polimi.ingsw.PS19.model.parameter.Costants;

public class BusinessDeck implements Deck, Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3125331114395001758L;
	private static final int FIRST_CARD = 0;
	private List<BusinessCard> card;
	
	public BusinessDeck() {
		card = new ArrayList<>();
	}
	
	@Override
	public BusinessCard getFirstCard()
	{
		BusinessCard singlecard = card.get(FIRST_CARD);
		card.remove(FIRST_CARD);
		return singlecard;
	}
	@Override
	public void addToDeck(Card card) 
	{
		this.card.add((BusinessCard) card);
	}
	public void addToDeckRandom(BusinessCard c)
	{
		int number; 
		if(card.size() > 3)
		{
			number = Costants.RANDOM_NUMBER.nextInt(card.size() -3) + 3;
			addToDeck(c, number);
		}
		else
		{
			card.add(c);
			return;
		}
	}
	public List<BusinessCard> getCard() {
		return card;
	}
	
	@Override
	public String toString() {
		String s = "\n+++++++++++++\n";
		
		for (Card c : card) 
		{
			s = s + c.toString() + "  ";
		}
		return s;
	}

	@Override
	public void shuffle() 
	{
		Collections.shuffle(card);
	}
	
	protected void setCardsId()
	{
		int i = 0;
		for (BusinessCard businessCard : card) 
		{
			businessCard.setId(i);
			i++;
		}
	}

	@Override
	public void addToDeck(Card c, int position) 
	{
		BusinessCard temp = card.get(position);
		BusinessCard temp2 = (BusinessCard) c;
		card.set(position, temp2);
		
		for(int i = position + 1 ; i<card.size() - 1; i++)
		{
			temp2 = card.get(i);
			card.set(i, temp);
			temp = temp2;
		}
		card.add(temp);
	}

}
