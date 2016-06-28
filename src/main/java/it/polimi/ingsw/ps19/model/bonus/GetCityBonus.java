
package it.polimi.ingsw.ps19.model.bonus;
import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.model.Player;



public class GetCityBonus implements Bonus {

	private static final long serialVersionUID = 6314285377196367956L;

	@Override
	public void giveBonus(Player p) 
	{
		p.setCityBonusRequest(true);
	}

	@Override
	public String toString(Language l) {
		return l.getString(this);
	}
}
