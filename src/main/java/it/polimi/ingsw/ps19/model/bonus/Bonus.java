package it.polimi.ingsw.ps19.model.bonus;

import java.io.Serializable;

import it.polimi.ingsw.ps19.model.Player;

@FunctionalInterface
public interface Bonus extends Serializable{
	
	void giveBonus(Player p);
	
}
