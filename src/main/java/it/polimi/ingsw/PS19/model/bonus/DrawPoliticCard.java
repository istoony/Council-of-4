package it.polimi.ingsw.PS19.model.bonus;
import it.polimi.ingsw.PS19.model.Player;

public class DrawPoliticCard implements Bonus{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -597116955305207082L;
	int howmany;
	
	public DrawPoliticCard(int n) {
		howmany=n;

	}
	
	@Override
	public void giveBonus(Player p) {
		p.setPoliticCardToDraw(p.getPoliticCardToDraw()+howmany);

	}
	
}
