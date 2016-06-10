package it.polimi.ingsw.ps19.model.bonus;

import it.polimi.ingsw.ps19.model.Player;

public class MoreVictoryPoints implements Bonus {
	
	int howMany;
	
	public MoreVictoryPoints(int n) {
		howMany=n;
	}
	
	
	public void giveBonus(Player p) {
		p.setVictoryPoints(p.getVictoryPoints()+howMany);

	}
	
	

}
