package it.polimi.ingsw.PS19.model.bonus;

import it.polimi.ingsw.PS19.model.Player;

public class MoreNobilityPoints implements Bonus {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8411391817026245017L;
	
	int howMany; 
	
	public MoreNobilityPoints(int n) {
		howMany=n;
	}
	
	@Override
	public void giveBonus(Player p) {
		p.setNobilityPoints(p.getNobilityPoints()+howMany);
	}

}
