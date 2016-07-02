package it.polimi.ingsw.ps19.client;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.logging.*;

import it.polimi.ingsw.ps19.client.clientinput.ClientAction;
import it.polimi.ingsw.ps19.client.clientinput.ClientActionChooser;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.model.Market;
import it.polimi.ingsw.ps19.model.Order;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.card.PoliticsCard;
import it.polimi.ingsw.ps19.model.map.City;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

/**
 * user interface abstract class
 */
public abstract class ClientUI
{
	protected Language language;
	protected static final Logger log = Logger.getLogger("CLIENT_LOGGER");
	
	/**
	 * Makes the user choose which type of action to perform
	 * @param actions: list of action types (Main or Fast)
	 * @return chosen action type
	 */
	public abstract ClientActionChooser requestActionType(List<ClientActionChooser> actions);
	
	/**
	 * Makes the user choose an action to perform from a list
	 * @param actions: list of possible actions
	 * @return chose action
	 * @throws InvalidInsertionException 
	 */
	public abstract ClientAction getAction(List<ClientAction> actions) throws InvalidInsertionException;
	
	/**
	 * Makes the user choose a region from all the possible regions
	 * @return chosen region
	 * @throws InvalidInsertionException
	 */
	public RegionType getRegion() throws InvalidInsertionException
	{
		return getRegion(RegionType.getValues());
	}
	
	/**
	 * Makes the user choose a region from a list
	 * @param regions: list of available regions
	 * @return chosen region
	 * @throws InvalidInsertionException
	 */
	public abstract RegionType getRegion(List<RegionType> regions) throws InvalidInsertionException; 
	
	/**
	 * Makes the user choose a color from a list
	 * @param validColors: list of available colors
	 * @return chosen color
	 * @throws InvalidInsertionException
	 */
	public abstract Color getColor(List<Color> validColors) throws InvalidInsertionException;
	
	public RegionType getRegionAndKing() throws InvalidInsertionException
	{
		return getRegionAndKing(RegionType.getValues());
	}

	/**
	 * Makes the user choose a region or the king from a list of regions + king
	 * @param regions: available regions (king must not be included)
	 * @return chosen region, NULL is king
	 * @throws InvalidInsertionException
	 */
	public abstract RegionType getRegionAndKing(List<RegionType> regions) throws InvalidInsertionException;

	/**
	 * Draws a notification for the user to see. no feedback expected
	 * @param s: String to show
	 */
	public abstract void showNotification(String s);
	
	/**
	 * Draws full model
	 * @param model: model to show
	 */
	public abstract void drawModel(ClientModel model);
	
	/**
	 * Makes the user choose a business card from a list
	 * @param cards: Available cards
	 * @return chosen card
	 * @throws InvalidInsertionException
	 */
	public abstract BusinessCard getBusiness(List<BusinessCard> cards) throws InvalidInsertionException;
	
	/**
	 * Makes the user choose a politic card from a list
	 * @param cards: available cards
	 * @return chosen card
	 * @throws InvalidInsertionException
	 */
	public abstract PoliticsCard getPolitic(List<PoliticsCard> cards) throws InvalidInsertionException;

	/**
	 * Makes the user choose a order from a list
	 * @param orders
	 * @return
	 */
	public abstract Order getOrder(List<Order> orders) throws InvalidInsertionException ;
	
	/**
	 * Makes the user choose a city from a list
	 * @param cities: available cities
	 * @return chosen city
	 * @throws InvalidInsertionException
	 */
	public abstract City getCity(List<City> cities) throws InvalidInsertionException;
	
	/**
	 * Makes the player choose how many helpers to sell
	 * @param n: number of helpers available
	 * @return
	 */
	public abstract int getNumberOfHelpers(int n) throws InvalidInsertionException;
	
	/**
	 * MAkes the user choose a city from a list with respective cost for moving the king to it
	 * @param citiesECost: available cities with cost;
	 * @return chosen city
	 * @throws InvalidInsertionException
	 */
	public abstract City getCity(Map<City,Integer> citiesECost) throws InvalidInsertionException;

	/**
	 * Makes the user set a price for an order
	 * @return price
	 * @throws InvalidInsertionException 
	 */
	public abstract int getPrice() throws InvalidInsertionException;
	
	public abstract String getUserString(String title) throws InvalidInsertionException;
	
	public abstract void showMarket(Market market);
	
	public abstract void showWinner(List<Player> players, String result);
	
	public Language getLanguage()
	{
		return language;
	}
} 
