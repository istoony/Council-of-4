package it.polimi.ingsw.PS19.model.bonus;


import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.card.PoliticDeck;
import it.polimi.ingsw.PS19.model.card.PoliticsCard;

public class DrawPoliticCard implements Bonus{
	
	PoliticDeck deck;
	int howmany;
	
	public DrawPoliticCard(PoliticDeck p, int n) {
		howmany=n;
		deck=p;
	}

	public void giveBonus(Player p) {
		PoliticsCard c;
		for(int i=0; i<howmany; i++){
			c = (PoliticsCard) deck.getFirstCard();
			p.addCardToHand(c);
		}
	}	
	
}
