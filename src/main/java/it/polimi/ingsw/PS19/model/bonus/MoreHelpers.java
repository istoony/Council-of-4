package it.polimi.ingsw.PS19.model.bonus;

import it.polimi.ingsw.PS19.model.Player;

public class MoreHelpers implements Bonus {
	int howMany; //how many helpers to give
	
	public MoreHelpers(int n) {
		howMany=n;
	}
	
	public void giveBonus(Player p) {
		p.setHelpers(p.getHelpers()+howMany);
	}
}
