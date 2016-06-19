package it.polimi.ingsw.PS19.controller.action;

import java.awt.Color;
import java.util.List;

import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.bonus.Bonus;
import it.polimi.ingsw.PS19.model.card.PoliticsCard;
import it.polimi.ingsw.PS19.model.map.City;
import it.polimi.ingsw.PS19.model.map.Region;
import it.polimi.ingsw.PS19.model.parameter.Costants;

public class SupportMethod 
{
	private static final int MONEY_1_CARDS = 10;
	private static final int MONEY_2_CARDS = 7;
	private static final int MONEY_3_CARDS = 4;
	
	/**
	 * Calculate the Money need to satisfy a balcony.
	 * This method don't check if cards are correct.
	 * @param politicsCard a list of Politic card used to satify balcony.
	 * @return a number of money.
	 */
	protected int numberOfNeedMoney(List<Color> politicsCard)
	{
		if(politicsCard.size() == 1)
			return MONEY_1_CARDS;
		if(politicsCard.size() == 2)
			return MONEY_2_CARDS;
		if(politicsCard.size() == 3)
			return MONEY_3_CARDS;
		return 0;
	}
	
	/**
	 * count a number of joker in the politicsCard
	 * @param politicsCard a list of politics card
	 * @return number of jocker
	 */
	protected int numberOfJoker(List<Color> politicsCard)
	{
		int count = 0;
		for (Color color : politicsCard) 
		{
			if(color.equals(Color.decode(Costants.JOKERCOLOR)))
				count ++;
		}
		return count;
	}
		
	protected boolean findPoliticCard(List<Color> politicsCard, Player player)
	{
		for (Color color : politicsCard) 
		{
			PoliticsCard tempCard = new PoliticsCard(color);
			if(!player.findPoliticsCard(tempCard))
				return false;
		}
		return true;
	}

	protected City getRealCity(Model m, City city)
	{
		for(Region r : m.getMap().getListaRegioni())
			for(City c : r.getCities())
				if(c.getId() == city.getId())
					return c;
		return null;
	}
	
	protected static void checkNobilityPathBonus(Model model, Player player) {
		if(model.getMap().getNobilityPath().getBonusByPosition(player.getNobilityPoints())!=null)
			for (Bonus nobilityBonus : model.getMap().getNobilityPath().getBonusByPosition(
					player.getNobilityPoints()))
				nobilityBonus.giveBonus(player);
	}
}
