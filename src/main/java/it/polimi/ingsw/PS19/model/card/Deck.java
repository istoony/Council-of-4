package it.polimi.ingsw.PS19.model.card;

import java.util.ArrayList;

public abstract class Deck 
{
	ArrayList<Card> card;
	
	public Card getFirstCard()
	{
		Card card = card.get(card.size()-1);
		return card;
	}
}
