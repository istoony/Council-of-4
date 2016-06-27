package it.polimi.ingsw.ps19.model.card;

/**
 * deck interface
 */
public interface Deck 
{
	
	/**
	 * remove and return first card of the deck.
	 * @return the firstr card of the deck
	 */
	public abstract Card getFirstCard();
	
	/**
	 * Adds a card to deck
	 * @param card the card
	 */
	public abstract void addToDeck(Card card);
	
	/**
	 * Shuffles the deck
	 */
	public abstract void shuffle();
}
