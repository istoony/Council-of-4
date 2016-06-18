package it.polimi.ingsw.PS19.model.bonus;

import it.polimi.ingsw.PS19.model.Player;

public class MoreHelpers implements Bonus {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7916908846822869862L;
	
	int howMany; //how many helpers to give
	
	public MoreHelpers(int n) {
		howMany=n;
	}
	
	@Override
	public void giveBonus(Player p) {
		p.setHelpers(p.getHelpers()+howMany);
	}
	
	@Override
	public String toString(){
		return "gain "+howMany+" Helpers";
	}
}
