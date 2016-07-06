package it.polimi.ingsw.ps19.model.card;

/**
 * Interface for a generic card
 */
@FunctionalInterface
public interface Card 
{
	/**
	 * Comparing method for two cards
	 * @param card
	 * @return if the cards are equals
	 */
	public boolean compare(Card card);
}