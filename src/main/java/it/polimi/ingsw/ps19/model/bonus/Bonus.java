package it.polimi.ingsw.ps19.model.bonus;

import java.io.Serializable;

import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.model.Player;

/**
 * Bonus
 */
public interface Bonus extends Serializable
{	
	public void giveBonus(Player p);
	
	/**
	 * to String in language
	 * @param l: language
	 * @return
	 */
	public String toString(Language l);
}
