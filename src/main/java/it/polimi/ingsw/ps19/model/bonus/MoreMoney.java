package it.polimi.ingsw.ps19.model.bonus;

import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.model.Player;

public class MoreMoney implements Bonus {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3640073227737356797L;
	int howMany; //how many money to give to the player

	public MoreMoney(int n) {
		howMany=n;
		
	}
	
	@Override
	public void giveBonus(Player p) {
		p.setMoney(p.getMoney()+howMany);
		
	}

	@Override
	public String toString(Language l) {
		return l.getString(this, howMany);
	}
}