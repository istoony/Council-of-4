package it.polimi.ingsw.PS19.model.bonus;

import it.polimi.ingsw.PS19.model.Player;

public class MoreMoney implements Bonus {
	int howMany; //how many money to give to the player

	public MoreMoney(int n) {
		howMany=n;
		
	}
	public void giveBonus(Player p) {
		p.setMoney(p.getMoney()+howMany);
		
	}

}