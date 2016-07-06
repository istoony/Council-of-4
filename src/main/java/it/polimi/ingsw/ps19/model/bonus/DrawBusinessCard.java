package it.polimi.ingsw.ps19.model.bonus;

import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.model.Player;

public class DrawBusinessCard implements Bonus 
{
	private static final long serialVersionUID = -2837340240553560706L;
	
	int howMany;
	
	/**
	 * Constructor for a bonus that allows to draw n business cards
	 * @param n
	 */
	public DrawBusinessCard(int n){
		howMany=n;
	}
	
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
