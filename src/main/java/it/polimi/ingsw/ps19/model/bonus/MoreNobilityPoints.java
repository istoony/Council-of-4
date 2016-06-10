package it.polimi.ingsw.ps19.model.bonus;

import it.polimi.ingsw.ps19.model.Player;

public class MoreNobilityPoints implements Bonus {
	int howMany; 
	
	public MoreNobilityPoints(int n) {
		howMany=n;
	}
	
	public void giveBonus(Player p) {
		p.setNobilityPoints(p.getNobilityPoints()+howMany);
	}

}
