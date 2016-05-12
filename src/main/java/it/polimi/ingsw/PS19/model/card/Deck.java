package it.polimi.ingsw.PS19.model.card;

import java.util.ArrayList;

public abstract class Deck 
{
	private static final int FIRST_CARD = 0;
	ArrayList<Card> card;
	
	/**
	 * remove and return first card of the deck
	 * @return the card of the deck
	 */
	public Card getFirstCard()
	{
		Card singlecard = card.get(FIRST_CARD);
		card.remove(FIRST_CARD);
		return singlecard;
	}
}
