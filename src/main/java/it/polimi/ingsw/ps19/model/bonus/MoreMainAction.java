package it.polimi.ingsw.ps19.model.bonus;

import it.polimi.ingsw.ps19.model.Player;

public class MoreMainAction implements Bonus {

	public void giveBonus(Player p) {
		p.setMainActionCounter(p.getMainActionCounter()+1);

	}

}
