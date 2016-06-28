package it.polimi.ingsw.ps19.model.bonus;

import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.model.Player;

public class ReuseBusinessCardBonus implements Bonus 
{
	
	private static final long serialVersionUID = 8181282753752840254L;
	/**
	 * This costuctor is empity beacause there isn't attribute to initialize
	 * the bonus.
	 */

	@Override
	public void giveBonus(Player p) 
	{
		p.setBusinessCardRequest(true);
	}
	
	@Override
	public String toString(Language l) {
		return l.getString(this);
	}

}
