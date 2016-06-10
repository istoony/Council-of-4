package it.polimi.ingsw.ps19.model.card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BusinessDeck implements Deck, Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3125331114395001758L;
	private static final int FIRST_CARD = 0;
	List<BusinessCard> card;
	
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
	@Override
	public Card addToDeck(int position) 
	{
		// TODO Auto-generated method stub
		return null;
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
			businessCard.setId(i);
	}

}
