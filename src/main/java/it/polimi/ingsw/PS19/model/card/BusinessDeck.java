package it.polimi.ingsw.PS19.model.card;

import java.util.ArrayList;

public class BusinessDeck implements Deck 
{
	private static final int FIRST_CARD = 0;
	ArrayList<BusinessCard> card;
	
	public BusinessDeck() {
		card = new ArrayList<BusinessCard>();
	}
		
	public BusinessCard getFirstCard()
	{
		BusinessCard singlecard = card.get(FIRST_CARD);
		card.remove(FIRST_CARD);
		return singlecard;
	}

	public void addToDeck(Card card) 
	{
		this.card.add((BusinessCard) card);
	}

	public Card addToDeck(int position) {
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

}
