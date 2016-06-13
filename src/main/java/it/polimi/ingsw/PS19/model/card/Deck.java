package it.polimi.ingsw.PS19.model.card;

public interface Deck 
{
	/**
	 * remove and return first card of the deck
	 * @return the card of the deck
	 */
	public abstract Card getFirstCard();
	
	public abstract void addToDeck(Card card);
	
	public abstract Card addToDeck(int position);
	
	public abstract void shuffle();
	
	
	
	
}
