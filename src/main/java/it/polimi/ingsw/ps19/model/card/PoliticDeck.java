package it.polimi.ingsw.ps19.model.card;

import java.util.ArrayList;
import java.util.Collections;

public class PoliticDeck implements Deck
{
	private static final int FIRST_CARD = 0;
	private ArrayList<PoliticsCard> card;
	private int toDraw;
	
	//TODO: creare che non si eliminano ma va avanti il first card
	//ogni volta che pesco e aggiungo nel vettore ogni volta che inserisco una card
	public PoliticDeck() 
	{
		card = new ArrayList<>();
		toDraw = FIRST_CARD;
	}
	
	@Override
	public PoliticsCard getFirstCard()
	{
		if(toDraw == card.size())
		{
			toDraw = FIRST_CARD;
			shuffle();
		}
		PoliticsCard singlecard = card.get(toDraw);
		toDraw++;
		return singlecard;
	}
	
	@Override
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
	
	@Override
	public void shuffle()
	{
		Collections.shuffle(card);
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
	/**
	 * This method is Unused in this case
	 */
	public void addToDeck(Card c, int position)
	{
		return;
	}
}
