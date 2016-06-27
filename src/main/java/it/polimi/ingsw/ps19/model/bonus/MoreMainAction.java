package it.polimi.ingsw.ps19.model.bonus;

import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.model.Player;

public class MoreMainAction implements Bonus {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6021222002046817299L;
	
	int howMany;
	
	public MoreMainAction(int n) {
		howMany = n;
	}

	@Override
	public void giveBonus(Player p) {
		p.setMainActionCounter(p.getMainActionCounter()+1);

	}
	
	@Override
	public String toString(Language l) {
		return l.getString(this, howMany);
	}

}
