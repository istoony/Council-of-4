package it.polimi.ingsw.PS19.model.bonus;

public class moreMoney implements Bonus {
	int howMany; //how many money to give to the player

	public moreMoney(int n) {
		this.howMany=n;
		
	}
	public void giveBonus(Player p) {
		
		p.setMoney(p.getMoney+this.howMany);
		
	}

}
