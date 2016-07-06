package it.polimi.ingsw.ps19.model.bonus;
import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.model.Player;

/**
 * Bonus to draw politic cards
 */
public class DrawPoliticCard implements Bonus
{
	private static final long serialVersionUID = -597116955305207082L;
	int howMany;
	
	/**
	 * Constructor for a bonus that allows to draw n politic cards
	 * @param n
	 */
	public DrawPoliticCard(int n) {
		howMany=n;

	}
	
	@Override
	public void giveBonus(Player p) {
		p.setPoliticCardToDraw(p.getPoliticCardToDraw()+howMany);

	}
	
	@Override
	public String toString(Language l) {
		return l.getString(this, howMany);
	}
	
}
