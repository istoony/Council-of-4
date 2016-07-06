package it.polimi.ingsw.ps19.model.bonus;

import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.model.Player;

/**
 * Bonus for more victory points
 */
public class MoreVictoryPoints implements Bonus {
	

	private static final long serialVersionUID = -3492529668103512175L;
	
	int howMany;
	
	/**
	 * Constructor for a bonus adding n victory points
	 * @param n
	 */
	public MoreVictoryPoints(int n) 
	{
		howMany=n;
	}
	
	@Override
	public void giveBonus(Player p) {
		p.setVictoryPoints(p.getVictoryPoints()+howMany);

	}
	
	@Override
	public String toString(Language l) {
		return l.getString(this, howMany);
	}	
}
