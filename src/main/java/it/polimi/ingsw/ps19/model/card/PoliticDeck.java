package it.polimi.ingsw.ps19.model.card;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Deck of politic cards
 */
public class PoliticDeck implements Deck
{
	private static final int FIRST_CARD = 0;
	private ArrayList<PoliticsCard> card;
	private int toDraw;
	
	/**
	 * Constructor
	 */
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
	
	public int getSize()
	{
		return card.size();
	}
	
	@Override
	public void shuffle()
	{
		Collections.shuffle(card);
	}

}
