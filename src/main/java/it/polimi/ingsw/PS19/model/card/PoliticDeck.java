package it.polimi.ingsw.PS19.model.card;

import java.util.ArrayList;
import java.util.Collections;

import it.polimi.ingsw.PS19.model.card.PoliticsCard;

public class PoliticDeck implements Deck
{
	private static final int FIRST_CARD = 0;
	private ArrayList<PoliticsCard> card;
	
	public PoliticDeck() 
	{
		card = new ArrayList<PoliticsCard>();
	}
	

	public PoliticsCard getFirstCard()
	{
		PoliticsCard singlecard = card.get(FIRST_CARD);
		card.remove(FIRST_CARD);
		return singlecard;
	}
	
	public void addToDeck(Card card) 
	{
		this.card.add((PoliticsCard) card);
	}
	
	public void addToDeck(PoliticsCard card) 
	{
		this.card.add(card);
	}
	
	public int getSize()
	{
		return card.size();
	}
	
	public void shuffle()
	{
		Collections.shuffle(card);
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
