package it.polimi.ingsw.PS19.model.card;

//TODO: WRITE COMMENTS PLEASE, HELP ME!
/**
 * The Interface Deck.
 */
public interface Deck 
{
	
	/**
	 * remove and return first card of the deck.
	 *
	 * @return the card of the deck
	 */
	public abstract Card getFirstCard();
	
	/**
	 * Adds the to deck.
	 *
	 * @param card the card
	 */
	public abstract void addToDeck(Card card);
	
	/**
	 * Shuffle.
	 */
	public abstract void shuffle();

	/**
	 * Adds the to deck.
	 *
	 * @param c the c
	 * @param position the position
	 */
	void addToDeck(Card c, int position);
	
	
	
	
}
