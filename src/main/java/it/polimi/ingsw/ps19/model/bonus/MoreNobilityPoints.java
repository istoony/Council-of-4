package it.polimi.ingsw.ps19.model.bonus;

import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.model.Player;

/**
 * Bonus to add nobility points
 */
public class MoreNobilityPoints implements Bonus {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8411391817026245017L;
	
	int howMany; 
	
	/**
	 * Constructor for a bonus adding n nobility points
	 * @param n
	 */
	public MoreNobilityPoints(int n) {
		howMany=n;
	}
	
	@Override
	public void giveBonus(Player p) {
		p.setNobilityPoints(p.getNobilityPoints()+howMany);
	}
	
	@Override
	public String toString(Language l) {
		return l.getString(this, howMany);
	}

}
