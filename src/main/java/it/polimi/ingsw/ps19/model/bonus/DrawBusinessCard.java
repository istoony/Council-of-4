package it.polimi.ingsw.ps19.model.bonus;

import it.polimi.ingsw.ps19.model.Player;

//not done
public class DrawBusinessCard implements Bonus {


	private static final long serialVersionUID = -2837340240553560706L;
	
	int howmany;
	
	public DrawBusinessCard(int n){
		howmany=n;
	}
	
	@Override
	public void giveBonus(Player p) 
	{
		p.setBusinessCardRequest(true);
	}
	
	@Override
	public String toString(){
		return "draw "+howmany+" Business Card";
	}

}
