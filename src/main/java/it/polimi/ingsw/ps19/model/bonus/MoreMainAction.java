package it.polimi.ingsw.PS19.model.bonus;

import it.polimi.ingsw.PS19.model.Player;

public class MoreMainAction implements Bonus {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6021222002046817299L;
	
	int howmany;
	
	public MoreMainAction(int n) {
		howmany = n;
	}

	@Override
	public void giveBonus(Player p) {
		p.setMainActionCounter(p.getMainActionCounter()+1);

	}
	
	@Override
	public String toString(){
		return "gain "+howmany+" Main Actions";
	}

}
