package it.polimi.ingsw.PS19.model.bonus;

import it.polimi.ingsw.PS19.model.Player;

public class MoreMainAction implements Bonus {

	public void giveBonus(Player p) {
		p.setMainActionCounter(p.getMainActionCounter()+1);

	}

}
