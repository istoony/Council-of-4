package it.polimi.ingsw.ps19.model.bonus;

import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.model.Player;

public class ReuseBusinessCardBonus implements Bonus {
	
	//TODO SCRIVIMI
	
	private static final long serialVersionUID = 8181282753752840254L;
	int howMany;
	public ReuseBusinessCardBonus(int howMany)
	{
		this.howMany = howMany;
	}

	@Override
	public void giveBonus(Player p) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public String toString(Language l) {
		return l.getString(this);
	}

}
