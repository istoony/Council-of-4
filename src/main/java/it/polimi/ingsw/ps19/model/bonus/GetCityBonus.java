
package it.polimi.ingsw.PS19.model.bonus;
import it.polimi.ingsw.PS19.model.Player;



public class GetCityBonus implements Bonus {

	private static final long serialVersionUID = 6314285377196367956L;
	

	public GetCityBonus(int parameter) 
	{	}

	@Override
	public void giveBonus(Player p) 
	{
		p.setCityBonusRequest(true);
	}
	
	@Override
	public String toString(){
		return "Get the bonus of a city";
	}

}
