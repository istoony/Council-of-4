package it.polimi.ingsw.ps19.controller.support;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.controller.action.DrawPoliticsCard;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.bonus.Bonus;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.card.PoliticsCard;
import it.polimi.ingsw.ps19.model.map.City;
import it.polimi.ingsw.ps19.model.map.Region;
import it.polimi.ingsw.ps19.model.parameter.Costants;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

//TODO: questa classe va commentata tutta bene che contiene i 3/4 della logica del GAME
public class SupportMethod 
{
	private static final int MONEY_1_CARDS = 10;
	private static final int MONEY_2_CARDS = 7;
	private static final int MONEY_3_CARDS = 4;
	
	public static final int N_OF_ACTION_TO_ADD = 1;
	public static final String MAIN_ACTION = "MAIN_ACTION";
	public static final String FAST_ACTION = "FAST_ACTION";
	
	/**
	 * Calculate the Money need to satisfy a balcony.
	 * This method don't check if cards are correct.
	 * @param politicsCard a list of Politic card used to satify balcony.
	 * @return a number of money.
	 */
	public static int numberOfNeedMoney(List<Color> politicsCard)
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
	public static int numberOfJoker(List<Color> politicsCard)
	{
		int count = 0;
		for (Color color : politicsCard) 
		{
			if(color.equals(Color.decode(Costants.JOKERCOLOR)))
				count ++;
		}
		return count;
	}
		
	public static boolean findPoliticCard(List<Color> politicsCard, Player player)
	{
		for (Color color : politicsCard) 
		{
			PoliticsCard tempCard = new PoliticsCard(color);
			if(!player.findPoliticsCard(tempCard))
				return false;
		}
		return true;
	}

	public static City getRealCity(Model m, City city)
	{
		for(Region r : m.getMap().getRegionList())
			for(City c : r.getCities())
				if(c.getId() == city.getId())
					return c;
		return null;
	}
	public static RegionType findRegion(Model model, City city)
	{
		for(Region r : model.getMap().getRegionList())
			for(City c : r.getCities())
				if(c.getId() == city.getId())
					return r.getType();
		return null;
	}
	
	public static void giveBonusToPlayer(Model model, RegionType region, Player player, int cityid)
	{
		List<City> myCity = model.getMap().getRegionByType(region).getCityById(cityid).applyNetBonus(player, new ArrayList<City>());
		for (City c : myCity) 
			for (Bonus b : c.getBonus())
			{
				b.giveBonus(player);
				checkNobilityPathBonus(model, player);
			}
		politicCardToDrawToCurrentPlayer(model);
		model.getMap().getKingBonus().giveBonus(player);
	}
	
	public static void politicCardToDrawToCurrentPlayer(Model model) 
	{
		int numberOfPoliticCards = model.getPlayerById(model.getCurrentState().getPlayerTurnId()).getPoliticCardToDraw();
		if( numberOfPoliticCards !=0)
		{
			DrawPoliticsCard drawPoliticsCard = new DrawPoliticsCard(model.getCurrentState().getPlayerTurnId(), numberOfPoliticCards);
			drawPoliticsCard.execute(model);
		}
	}
	
	public static void checkNobilityPathBonus(Model model, Player player)
	{
		if(model.getMap().getNobilityPath().getBonusByPosition(player.getNobilityPoints())!=null)
			for (Bonus nobilityBonus : model.getMap().getNobilityPath().getBonusByPosition(
					player.getNobilityPoints()))
				nobilityBonus.giveBonus(player);
	}
	
	public static void removePoliticCardToHand(Model model, Player player, List<Color> politicCard){
		for(int i = 0; i < politicCard.size(); i++)
		{
			PoliticsCard p = new PoliticsCard(politicCard.get(i));
			player.removeCardToHand(p);
			model.getMap().getPoliticdeck().addToDeck(p);
		}
	}
	
	public static String checkPlayerVictory(Model model, Player player)
	{
		if(player.getMaxemporia() == 0 && model.getCurrentState().getLastTurn() != Costants.INVALID_ID)
		{
			model.getCurrentState().setLastTurn(player.getId());
			for (Player p : model.getPlayer())
				if(p.getId() != player.getId())
					p.setStartingAction();
			
			return ActionMessages.playerWinGameResultBuilder(player.getId());
		}
		return ActionMessages.EVERYTHING_IS_OK;
	}

	public static void calculateLastPoints(Model model)
	{
		List<Player> players = model.getPlayer();
		Player temp;
		
		//Guardo chi ha più punti nobiltà, sort mettendo in prima posizione chi ha
		//più punti nobiltà
		for (int i = 0; i< players.size() - 1; i++)
			for (int j = i+1; j< players.size(); j++)
				if(players.get(i).getNobilityPoints() < players.get(j).getNobilityPoints())
				{
					temp = players.get(i);
					players.set(i, players.get(j));
					players.set(j, temp);
				}
		players = sendPoints(players, 5);
		if(players.size() == model.getPlayer().size() - 1)
			sendPoints(players, 2);
	}

	private static List<Player> sendPoints(List<Player> players, int numberOfPoints) 
	{
		int max = players.get(0).getNobilityPoints();		
		for (int i = 0; i< players.size(); i++)
		{
			if(players.get(0).getNobilityPoints() == max)
			{
				players.get(0).setVictoryPoints(players.get(i).getVictoryPoints() + numberOfPoints);
				players.remove(0);
			}
		}
		return players;
	}
	public static List<Player> sortByVictoryPoints(List<Player> players)
	{
		Player temp;
		for (int i = 0; i< players.size() - 1; i++)
			for (int j = i+1; j< players.size(); j++)
				if(players.get(i).getVictoryPoints() < players.get(j).getVictoryPoints())
				{
					temp = players.get(i);
					players.set(i, players.get(j));
					players.set(j, temp);
				}
		return players;
	}

	public static boolean checkPlayerTurnAndAction(Model model, int id, String result, String type) 
	{
		if(checkPlayerTurn(id, model))
		{
			result = ActionMessages.NOT_YOUR_TURN;
			return false;
		}
		
		if(type.equals(MAIN_ACTION))
		{
			if(model.getPlayerById(id).getMainActionCounter() < N_OF_ACTION_TO_ADD)
			{
				result = ActionMessages.NO_ACTION_TO_DO_IT;
				return false;
			}
		}
		else if(type.equals(FAST_ACTION) && model.getPlayerById(id).getFastActionCounter() < N_OF_ACTION_TO_ADD)
		{
			result = ActionMessages.NO_ACTION_TO_DO_IT;
			return false;
		}
	
		return true;
	}
	
	public static void giveListOfBonus(Model model, Player player, List<Bonus> bonus) 
	{
		for (Bonus b : bonus) 
		{
			b.giveBonus(player);
			checkNobilityPathBonus(model, player);	
		}
	}
	

	/**
	 * Check player turn.
	 *
	 * @param id the id
	 * @param m the m
	 * @return the boolean
	 */
	public static Boolean checkPlayerTurn(int id, Model m)
	{
		return id != m.getCurrentState().getPlayerTurnId();
	}
	
	public static boolean findFirstSecondCard(Model model, RegionType region, BusinessCard card) 
	{
		BusinessCard firstCard = model.getMap().getRegionByType(region).getFirstcard();
		
		if(firstCard.getId() == card.getId())
			return true;
		return false;
	}

	public static boolean findExistBusinessCard(Model model, RegionType region, BusinessCard card) 
	{
		BusinessCard firstCard = model.getMap().getRegionByType(region).getFirstcard();
		BusinessCard secondCard = model.getMap().getRegionByType(region).getSecondcard();
		
		if(firstCard.getId() == card.getId() || secondCard.getId() == card.getId())
			return true;
		return false;
	}
	
	public static BusinessCard removeCardFromRegionAndAddToPlayer(Model model, Player player, BusinessCard card, RegionType region)
	{
		BusinessCard selectedcard;
		if(findFirstSecondCard(model, region, card))
		{
			selectedcard = model.getMap().getRegionByType(region).getFirstcard();
			model.getMap().getRegionByType(region).drawFirstCard();
		}
		else
		{
			selectedcard = model.getMap().getRegionByType(region).getSecondcard();
			model.getMap().getRegionByType(region).drawSecondCard();
		}
		player.addCardToHand(selectedcard);
		return selectedcard;
	}
	
}
