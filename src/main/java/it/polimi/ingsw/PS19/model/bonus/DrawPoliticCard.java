package it.polimi.ingsw.PS19.model.bonus;


import it.polimi.ingsw.PS19.model.Player;


public class DrawPoliticCard implements Bonus{
	
	int howmany;
	
	public DrawPoliticCard(int n) {
		howmany=n;

	}
	
	@Override
	public void giveBonus(Player p) {

	}	
	
}
