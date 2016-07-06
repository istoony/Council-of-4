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

/**
 * Class that contains methods vastly used by controller in different situations
 */
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
		
	/**
	 * Checks whether the player has a set of politic cards
	 * @param politicsCard
	 * @param player
	 * @return true iff the player has all the passed cards
	 */
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

	/**
	 * Returns reference to the city actually contained in the model
	 * @param m: model
	 * @param city: city of caller
	 * @return city in model
	 */
	public static City getRealCity(Model m, City city)
	{
		for(Region r : m.getMap().getRegionList())
			for(City c : r.getCities())
				if(c.getId() == city.getId())
					return c;
		return null;
	}
	/**
	 * Returns the region of the city
	 * @param model
	 * @param city
	 * @return region containing the city
	 */
	public static RegionType findRegion(Model model, City city)
	{
		for(Region r : model.getMap().getRegionList())
			for(City c : r.getCities())
				if(c.getId() == city.getId())
					return r.getType();
		return null;
	}
	
	/**
	 * Give all the bonus due to the player
	 * @param model
	 * @param region
	 * @param player
	 * @param cityid
	 */
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
	
	/**
	 * gives a politic card to the current player (new turn)
	 * @param model
	 */
	public static void politicCardToDrawToCurrentPlayer(Model model) 
	{
		int numberOfPoliticCards = model.getPlayerById(model.getCurrentState().getPlayerTurnId()).getPoliticCardToDraw();
		if( numberOfPoliticCards !=0)
		{
			DrawPoliticsCard drawPoliticsCard = new DrawPoliticsCard(model.getCurrentState().getPlayerTurnId(), numberOfPoliticCards);
			drawPoliticsCard.execute(model);
		}
	}
	
	/**
	 * Checks if the player deserves a nobility bonus and if so assigns it
	 * @param model
	 * @param player
	 */
	public static void checkNobilityPathBonus(Model model, Player player)
	{
		if(model.getMap().getNobilityPath().getBonusByPosition(player.getNobilityPoints())!=null)
			for (Bonus nobilityBonus : model.getMap().getNobilityPath().getBonusByPosition(player.getNobilityPoints()))
				nobilityBonus.giveBonus(player);
	}
	
	/**
	 * Removes a set of politic cards from the player hand
	 * @param model
	 * @param player
	 * @param politicCard
	 */
	public static void removePoliticCardToHand(Model model, Player player, List<Color> politicCard){
		for(int i = 0; i < politicCard.size(); i++)
		{
			PoliticsCard p = new PoliticsCard(politicCard.get(i));
			player.removeCardToHand(p);
			model.getMap().getPoliticdeck().addToDeck(p);
		}
	}
	
	/**
	 * Checks if the player has finished the emporia and if so starts the last turn
	 * @param model
	 * @param player
	 * @return result
	 */
	public static String checkPlayerVictory(Model model, Player player)
	{
		if(player.getMaxemporia() == 0 && model.getCurrentState().getLastTurn() == Costants.INVALID_ID)
		{
			model.getCurrentState().setLastTurn(player.getId());
			for (Player p : model.getPlayer())
				if(p.getId() != player.getId())
					p.setStartingAction();
			
			return ActionMessages.playerWinGameResultBuilder(player.getId());
		}
		return ActionMessages.EVERYTHING_IS_OK;
	}

	/**
	 * Calculates points deserved for having finished first and second in the nobility path and assign it to the players
	 * @param model
	 */
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
				players.get(0).setVictoryPoints(players.get(0).getVictoryPoints() + numberOfPoints);
				players.remove(0);
			}
		}
		return players;
	}
	
	/**
	 * Sorts the array ordered by the victory points
	 * @param players
	 * @return sorted player list
	 */
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
	
	/**
	 * Checks whether it is the turn of the player and if he has at least 1 action of the type requested
	 * @param model
	 * @param id: player id
	 * @param type: type of action to request
	 * @return: if available or not
	 */
	public static boolean checkPlayerTurnAndAction(Model model, int id, String type) 
	{
		if(checkPlayerTurn(id, model))
			return false;
		
		if((type.equals(MAIN_ACTION) && model.getPlayerById(id).getMainActionCounter() < N_OF_ACTION_TO_ADD) || 
				(type.equals(FAST_ACTION) && model.getPlayerById(id).getFastActionCounter() < N_OF_ACTION_TO_ADD))
			return false;
		return true;
	}
	
	/**
	 * Applies to the player a list of bonus and calculates consequent bonuses from nobility path
	 * @param model
	 * @param player
	 * @param bonus: list of bonus
	 */
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
	
	/**
	 * Checks whether the passed card is the first of the passed region.
	 * @param model
	 * @param region
	 * @param card
	 * @return
	 */
	public static boolean findFirstSecondCard(Model model, RegionType region, BusinessCard card) 
	{
		BusinessCard firstCard = model.getMap().getRegionByType(region).getFirstcard();
		if(firstCard.getId() == card.getId())
			return true;
		return false;
	}

	/**
	 * Checks whether the passed card is one of the shown cards in the passed region
	 * @param model
	 * @param region
	 * @param card
	 * @return
	 */
	public static boolean findExistBusinessCard(Model model, RegionType region, BusinessCard card) 
	{
		BusinessCard firstCard = model.getMap().getRegionByType(region).getFirstcard();
		BusinessCard secondCard = model.getMap().getRegionByType(region).getSecondcard();
		if(firstCard.getId() == card.getId() || secondCard.getId() == card.getId())
			return true;
		return false;
	}
	
	/**
	 * Removes card from the region and gives it to the player
	 * The region and the player are updated
	 * @param model
	 * @param player
	 * @param card
	 * @param region
	 * @return card moves
	 */
	public static BusinessCard removeCardFromRegionAndAddToPlayer(Model model, Player player, BusinessCard card, RegionType region)
	{
		BusinessCard selectedcard;
		if(findFirstSecondCard(model, region, card))
		{
			selectedcard = model.getMap().getRegionByType(region).getFirstcard();
			if(model.getMap().getRegionByType(region).getBusinessdeck().size() > 0)
				model.getMap().getRegionByType(region).drawFirstCard();
			else 
				model.getMap().getRegionByType(region).setEmptyFirstCard();
		}
		else
		{
			selectedcard = model.getMap().getRegionByType(region).getSecondcard();
			if(model.getMap().getRegionByType(region).getBusinessdeck().size() > 0)
				model.getMap().getRegionByType(region).drawSecondCard();
			else 
				model.getMap().getRegionByType(region).setEmptySecondCard();
		}
		player.addCardToHand(selectedcard);
		return selectedcard;
	}
	
}
