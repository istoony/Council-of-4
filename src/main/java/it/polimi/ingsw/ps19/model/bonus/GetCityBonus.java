package it.polimi.ingsw.ps19.model.bonus;
import it.polimi.ingsw.ps19.model.Player;

public class GetCityBonus implements Bonus {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6314285377196367956L;
	
	private int howmany;

	public GetCityBonus(int parameter) {
		howmany = parameter;
	}

	@Override
	public void giveBonus(Player p) {
		// TODO Auto-generated method stub
		
	}

}
