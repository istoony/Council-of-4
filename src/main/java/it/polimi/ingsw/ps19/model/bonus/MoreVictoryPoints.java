package it.polimi.ingsw.ps19.model.bonus;

import it.polimi.ingsw.ps19.model.Player;

public class MoreVictoryPoints implements Bonus {
	

	private static final long serialVersionUID = -3492529668103512175L;
	
	int howMany;
	
	public MoreVictoryPoints(int n) {
		howMany=n;
	}
	
	@Override
	public void giveBonus(Player p) {
		p.setVictoryPoints(p.getVictoryPoints()+howMany);

	}
	
	@Override
	public String toString(){
		return "gain "+howMany+" Victory Points";
	}
	

}
