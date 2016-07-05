package it.polimi.ingsw.ps19.client.language;

import java.awt.Color;
import java.io.Serializable;

import it.polimi.ingsw.ps19.client.clientinput.BuildEmporiumInputs;
import it.polimi.ingsw.ps19.client.clientinput.BuildWithKingInputs;
import it.polimi.ingsw.ps19.client.clientinput.BuyHelperInputs;
import it.polimi.ingsw.ps19.client.clientinput.BuyMainActionInput;
import it.polimi.ingsw.ps19.client.clientinput.ClientAction;
import it.polimi.ingsw.ps19.client.clientinput.ElectCouncillorInputs;
import it.polimi.ingsw.ps19.client.clientinput.EndTurnInput;
import it.polimi.ingsw.ps19.client.clientinput.FastAction;
import it.polimi.ingsw.ps19.client.clientinput.GetBusinessPermitInput;
import it.polimi.ingsw.ps19.client.clientinput.MainAction;
import it.polimi.ingsw.ps19.client.clientinput.MarketSell;
import it.polimi.ingsw.ps19.client.clientinput.RedrawBusinessCardInput;
import it.polimi.ingsw.ps19.model.Order;
import it.polimi.ingsw.ps19.model.bonus.Bonus;
import it.polimi.ingsw.ps19.model.bonus.DrawBusinessCard;
import it.polimi.ingsw.ps19.model.bonus.DrawPoliticCard;
import it.polimi.ingsw.ps19.model.bonus.GeneralBonus;
import it.polimi.ingsw.ps19.model.bonus.GetCityBonus;
import it.polimi.ingsw.ps19.model.bonus.MoreHelpers;
import it.polimi.ingsw.ps19.model.bonus.MoreMainAction;
import it.polimi.ingsw.ps19.model.bonus.MoreMoney;
import it.polimi.ingsw.ps19.model.bonus.MoreNobilityPoints;
import it.polimi.ingsw.ps19.model.bonus.MoreVictoryPoints;
import it.polimi.ingsw.ps19.model.bonus.ReuseBusinessCardBonus;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.card.PoliticsCard;
import it.polimi.ingsw.ps19.model.map.Balcony;
import it.polimi.ingsw.ps19.model.map.City;
import it.polimi.ingsw.ps19.model.parameter.Costants;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

/**
 * Class with string constants for language
 */
/*
 * 
 */
public abstract class Language implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	//Server constants
	public static final String ENGLISH = "English";
	public static final String ITALIANO = "Italiano";
	
	public static final String START = "started";
	public static final String SET_MAX_P = "set the maximum number of players per game";
	public static final String REG_CREATED = "New Registry created at: localhost:";
	public static final String REG_ACCESSED = "Accessing Registry at: localhost:";
	public static final String RMI_SUCCESS = "The RMI server creation has been successful";
	public static final String RMI_INSUCCESS = "Something went wrong in creating a RMI Server";
	public static final String SOCKET_SUCCESS = "The server socket creation has been successful";
	public static final String SOCKET_INSUCCESS = "Something went wrong in creating a serversocket";
	public static final String NEW_CLIENT_CONN = "New Client connected";
	public static final String SERVER_QUIT = "Server quits";
	public static final String SET_MAX_P_TO = "set the player timeout time (in s)";
	
	//General
	public static final String SOCKET = "Socket";
	public static final String RMI = "RMI";
	public static final String CLI = "CLI";
	public static final String GUI = "GUI";
	
	//setup
	protected String insertIp;
	protected String insertPort;
	protected String tryConn;
	protected String useStdIp;
	protected String useStdPort;
	protected String newGame;
	protected String reconnect;
	protected String insertPass;
	protected String connSuccess;
	protected String connInsucces;
	protected String killClient;
	protected String waiting;
	protected String invalidInsertion;
	protected String socketCreated;
	protected String serverQuits;
	protected String reconnected;
	protected String connPass;
	protected String invalidObj;
	
	//general
	protected String nothing;
	protected String numberOf;
	protected String available;
	
	//map
	protected String cities;
	protected String currentCity;
	protected String bonuses;
	protected String balcony;
	protected String region;
	protected String firstCard;
	protected String secondCard;
	protected String king;
	protected String politicCards;
	protected String businessCards;
	protected String noEmporia;
	protected String emporiaOfPlayers;
	protected String map;
	protected String nobilityPath;
	
	//players
	protected String player;
	protected String numEmporiaLeft;
	protected String numOfHelpers;
	protected String money;
	protected String victoryPoints;
	protected String nobilityPoints;
	protected String main;
	protected String freeBusiness;
	protected String usedBusiness;
	protected String quick;
	protected String result;
	protected String activePlayerId;
	protected String youArePlayer;
	
	//choosing title
	protected String chooseActionTypeTitle;
	protected String chooseActionTitle;
	protected String chooseRegionTitle;
	protected String chooseBalconyTitle;
	protected String choosePoliticCardTitle;
	protected String chooseBusinessCardTitle;
	protected String chooseCityTitle;
	protected String chooseColor;
	
	//market
	protected String howManyHelpersToSell;
	protected String setPrice;
	protected String chooseOrder;
	protected String market;
	protected String noOrders;
	protected String orders;
	protected String price;
	protected String helpers;
	
	protected String mountain;
	protected String hill;
	protected String plain;
	
	protected String winner;
	
	protected String info;
	protected String infoCity;
	protected String infoGame;
	protected String infoYou;
	protected String infoOthers;
	protected String infoPlayer;
	protected String position;
	
	protected String youNoEmporia;
	protected String youNoBusiness;
	
	//Colors
	protected String cFF0000;
	protected String c0000FF;
	protected String cFF7F00;
	protected String c000000;
	protected String cFFFFFF;
	protected String cFFC0CB;
	protected String cJoker;
	
	/**
	 * Return string notifying of a player disconnected
	 * @param id
	 * @return
	 */
	public static final String playerDisconnected(int id)
	{
		return "Player " + id + " has disconnected";
	}
	
	//Actions
	/**
	 * return the name of the action
	 * @param input: action
	 * @return
	 */
	public abstract String getString(BuildWithKingInputs input);
	
	/**
	 * return the name of the action
	 * @param input: action
	 * @return
	 */
	public abstract String getString(BuildEmporiumInputs input);
	
	/**
	 * return the name of the action
	 * @param input: action
	 * @return
	 */
	public abstract String getString(ElectCouncillorInputs input);
	
	/**
	 * return the name of the action
	 * @param input: action
	 * @return
	 */
	public abstract String getString(EndTurnInput input);
	
	/**
	 * return the name of the action
	 * @param input: action
	 * @return
	 */
	public abstract String getString(GetBusinessPermitInput input);
	
	/**
	 * return the name of the action
	 * @param input: action
	 * @return
	 */
	public abstract String getString(RedrawBusinessCardInput input);
	
	/**
	 * return the name of the action
	 * @param input: action
	 * @return
	 */
	public abstract String getString(BuyHelperInputs input);
	
	/**
	 * return the name of the action
	 * @param input: action
	 * @return
	 */
	public abstract String getString(BuyMainActionInput input);
	
	/**
	 * return the name of the action
	 * @param input: action
	 * @return
	 */
	public abstract String getString(MarketSell input);
	
	/**
	 * return the name of the action
	 * @param input: action
	 * @return
	 */
	public abstract String getString(FastAction input);
	
	/**
	 * return the name of the action
	 * @param input: action
	 * @return
	 */
	public abstract String getString(MainAction input);
	
	/**
	 * return the name of the action
	 * @param input: action
	 * @return
	 */
	public abstract String getString(DrawBusinessCard input);
	
	/**
	 * toString of bonus
	 * @param input: bonus
	 * @param howMany: howMany more <E>
	 * @return
	 */
	public abstract String getString(DrawPoliticCard input, int howMany);
	
	/**
	 * toString of bonus
	 * @param input: bonus
	 * @return
	 */
	public abstract String getString(GeneralBonus input);
	
	/**
	 * toString of bonus
	 * @param input: bonus
	 * @return
	 */
	public abstract String getString(GetCityBonus input);
	
	/**
	 * toString of bonus
	 * @param input: bonus
	 * @param howMany: howMany more <E>
	 * @return
	 */
	public abstract String getString(MoreHelpers input, int howMany);
	
	/**
	 * toString of bonus
	 * @param input: bonus
	 * @param howMany: howMany more <E>
	 * @return
	 */
	public abstract String getString(MoreMainAction input, int howMany);
	
	/**
	 * toString of bonus
	 * @param input: bonus
	 * @param howMany: howMany more <E>
	 * @return
	 */
	public abstract String getString(MoreMoney input, int howMany);
	
	/**
	 * toString of bonus
	 * @param input: bonus
	 * @param howMany: howMany more <E>
	 * @return
	 */
	public abstract String getString(MoreNobilityPoints input, int howMany);
	
	/**
	 * toString of bonus
	 * @param input: bonus
	 * @param howMany: howMany more <E>
	 * @return
	 */
	public abstract String getString(MoreVictoryPoints input, int howMany);
	
	/**
	 * toString of bonus
	 * @param input: bonus
	 * @return
	 */
	public abstract String getString(ReuseBusinessCardBonus input);

	/**
	 * return the name of a politic card
	 * @param card: card
	 * @return
	 */
	public String getString(PoliticsCard card)
	{
		if(card == null)
			return getNothing();
		return getString(card.getColor());
	}
	
	/**
	 * visitor for actions
	 * @param input: action
	 * @return
	 */
	public String getString(ClientAction input)
	{
		return input.toString(this);
	}

	/**
	 * get name for balcony
	 * @param b: balcony
	 * @return
	 */
	public String getString(Balcony b)
	{
		String s = getBalcony() + ": [";
		s += getString(b.getCouncilcolor().get(0));
		for(int i = 1; i < b.getCouncilcolor().size(); i++)
			s = s.concat(", " + getString(b.getCouncilcolor().get(i)));
		s += "]\n";
		return s;
	}
	
	/**
	 * to String of order
	 * @param order
	 * @return
	 */
	public String getString(Order order)
	{
		if(order == null)
			return getNothing();
		String s = "[" + getHelpers() + ": " + order.getHelper();
		s += ", " + getPoliticCards() + ": ";
		if(order.getPoliticscard().isEmpty())
			s += "0,";
		else
			for(Color card : order.getPoliticscard())
				s = s.concat(getString(card) + ",");
		s += " " + getBusinessCards() + ": ";
		if(order.getBusinesscard().isEmpty())
			s += "0,";
		else
			for(BusinessCard card : order.getBusinesscard())
				s = s.concat(getString(card) + ",");
		s += " " + getPrice() + ": " + order.getPrice() + "]";
		return s;
	}

	/**
	 * toString of business card
	 * @param card
	 * @return
	 */
	public String getString(BusinessCard card)
	{
		if(card == null)
			return getNothing();
		String s = getCities().toUpperCase() + ": ";
		for(City city: card.getCity())
		{
			s = s.concat(getString(city));
			s = s.concat(", ");
		}
		s += getBonuses().toUpperCase() + ": ";
		if(card.getBonus().isEmpty())
			s += "0";
		else
		{
			s = s.concat(getString(card.getBonus().get(0)));
			for(int i = 1; i <card.getBonus().size(); i++)
				s = s.concat(", " + getString(card.getBonus().get(i)));
		}
		return s;
	}
	
	/**
	 * String visitor for bonuses
	 * @param b: bonus
	 * @return
	 */
	public String getString(Bonus b)
	{
		return b.toString(this);
	}
	
	/**
	 * string name
	 * @param city
	 * @return
	 */
	public String getString(City city)
	{
		return city.getName();
	}
	
	/**
	 * toString of color
	 * @param c
	 * @return
	 */
	public String getString(Color c){
		String s;
		if (c==null){
			s = getNothing();
		}
		else if(c.equals(Color.decode("#FF0000"))){
			s= cFF0000;
		}
		else if(c.equals(Color.decode("#0000FF"))){
			s= c0000FF;
		}
		else if(c.equals(Color.decode("#FF7F00"))){
			s= cFF7F00;
		}
		else if(c.equals(Color.decode("#000000"))){
			s= c000000;
		}
		else if(c.equals(Color.decode("#FFFFFF"))){
			s= cFFFFFF;
		}
		else if(c.equals(Color.decode("#FFC0CB"))){
			s= cFFC0CB;
		}
		else if(c.equals(Color.decode(Costants.JOKERCOLOR))){
			s= cJoker;
		}
		else{
			s = "#" + Integer.toHexString(c.getRGB()).substring(2).toUpperCase();
		}
		return s;
	}
	
	/**
	 * toString of region
	 * @param r: region
	 * @return
	 */
	public String getString(RegionType r)
	{
		String s = getNothing();
		if(r.equals(RegionType.PLAIN))
			s = plain.toUpperCase();
		else if(r.equals(RegionType.HILL))
			s = hill.toUpperCase();
		else if(r.equals(RegionType.MOUNTAIN))
			s = mountain.toUpperCase();
		return s;
	}

	/**
	 * @return the newGame
	 */
	public String getNewGame() {
		return newGame;
	}

	/**
	 * @return the reconnect
	 */
	public String getReconnect() {
		return reconnect;
	}

	/**
	 * @return the insertPassword
	 */
	public String getInsertPassword() {
		return insertPass;
	}

	/**
	 * @return the invalidInsertion
	 */
	public String getInvalidInsertion() {
		return invalidInsertion;
	}

	/**
	 * @return the waiting
	 */
	public String getWaiting() {
		return waiting;
	}

	/**
	 * @return the socketCreated
	 */
	public String getSocketCreated() {
		return socketCreated;
	}

	/**
	 * @return the connSuccess
	 */
	public String getConnSuccess() {
		return connSuccess;
	}

	/**
	 * @return the connInsucces
	 */
	public String getConnInsucces() {
		return connInsucces;
	}

	/**
	 * @return the killClient
	 */
	public String getKillClient() {
		return killClient;
	}


	/**
	 * @return the reconnected
	 */
	public String getReconnected() {
		return reconnected;
	}

	/**
	 * @return the connPass
	 */
	public String getConnPass() {
		return connPass;
	}

	/**
	 * @return the insertIp
	 */
	public String getInsertIp() {
		return insertIp;
	}

	/**
	 * @return the useStdIp
	 */
	public String getUseStdIp() {
		return useStdIp;
	}

	/**
	 * @return the insertPort
	 */
	public String getInsertPort() {
		return insertPort;
	}

	/**
	 * @return the useStdPort
	 */
	public String getUseStdPort() {
		return useStdPort;
	}

	/**
	 * @return the serverQuits
	 */
	public String getServerQuits() {
		return serverQuits;
	}

	/**
	 * @return the invalidObj
	 */
	public String getInvalidObj() {
		return invalidObj;
	}

	/**
	 * @return the nothing
	 */
	public String getNothing() {
		return nothing;
	}

	/**
	 * @return the winner
	 */
	public String getWinner() {
		return winner;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @return the chooseActionTypeTitle
	 */
	public String getChooseActionTypeTitle() {
		return chooseActionTypeTitle;
	}

	/**
	 * @return the chooseRegionTitle
	 */
	public String getChooseRegionTitle() {
		return chooseRegionTitle;
	}

	/**
	 * @return the king
	 */
	public String getKing() {
		return king;
	}

	/**
	 * @return the chooseActionTitle
	 */
	public String getChooseActionTitle() {
		return chooseActionTitle;
	}

	/**
	 * @return the chooseColor
	 */
	public String getChooseColor() {
		return chooseColor;
	}

	/**
	 * @return the chooseBusinessCardTitle
	 */
	public String getChooseBusinessCardTitle() {
		return chooseBusinessCardTitle;
	}

	/**
	 * @return the choosePoliticCardTitle
	 */
	public String getChoosePoliticCardTitle() {
		return choosePoliticCardTitle;
	}

	/**
	 * @return the chooseCityTitle
	 */
	public String getChooseCityTitle() {
		return chooseCityTitle;
	}

	/**
	 * @return the bonuses
	 */
	public String getBonuses() {
		return bonuses;
	}

	/**
	 * @return the map
	 */
	public String getMap() {
		return map;
	}

	/**
	 * @return the nobilityPath
	 */
	public String getNobilityPath() {
		return nobilityPath;
	}

	/**
	 * @return the activePlayerId
	 */
	public String getActivePlayerId() {
		return activePlayerId;
	}

	/**
	 * @return the youArePlayer
	 */
	public String getYouArePlayer() {
		return youArePlayer;
	}

	/**
	 * @return the player
	 */
	public String getPlayer() {
		return player;
	}

	/**
	 * @return the numEmporiaLeft
	 */
	public String getNumEmporiaLeft() {
		return numEmporiaLeft;
	}

	/**
	 * @return the money
	 */
	public String getMoney() {
		return money;
	}

	/**
	 * @return the victoryPoints
	 */
	public String getVictoryPoints() {
		return victoryPoints;
	}

	/**
	 * @return the nobilityPoints
	 */
	public String getNobilityPoints() {
		return nobilityPoints;
	}

	/**
	 * @return the numOfHelpers
	 */
	public String getNumOfHelpers() {
		return numOfHelpers;
	}

	/**
	 * @return the numberOf
	 */
	public String getNumberOf() {
		return numberOf;
	}

	/**
	 * @return the politicCards
	 */
	public String getPoliticCards() {
		return politicCards;
	}

	/**
	 * @return the available
	 */
	public String getAvailable() {
		return available;
	}

	/**
	 * @return the quick
	 */
	public String getQuick() {
		return quick;
	}

	/**
	 * @return the main
	 */
	public String getMain() {
		return main;
	}

	/**
	 * @return the freeBusiness
	 */
	public String getFreeBusiness() {
		return freeBusiness;
	}

	/**
	 * @return the usedBusiness
	 */
	public String getUsedBusiness() {
		return usedBusiness;
	}

	/**
	 * @return the currentCity
	 */
	public String getCurrentCity() {
		return currentCity;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @return the cities
	 */
	public String getCities() {
		return cities;
	}

	/**
	 * @return the businessCards
	 */
	public String getBusinessCards() {
		return businessCards;
	}

	/**
	 * @param businessCards the businessCards to set
	 */
	public void setBusinessCards(String businessCards) {
		this.businessCards = businessCards;
	}

	/**
	 * @return the firstCard
	 */
	public String getFirstCard() {
		return firstCard;
	}

	/**
	 * @return the secondCard
	 */
	public String getSecondCard() {
		return secondCard;
	}

	/**
	 * @return the howManyHelpersToSell
	 */
	public String getHowManyHelpersToSell() {
		return howManyHelpersToSell;
	}

	/**
	 * @return the setPrice
	 */
	public String getSetPrice() {
		return setPrice;
	}

	/**
	 * @return the chooseOrder
	 */
	public String getChooseOrder() {
		return chooseOrder;
	}

	/**
	 * @return the market
	 */
	public String getMarket() {
		return market;
	}

	/**
	 * @return the orders
	 */
	public String getOrders() {
		return orders;
	}

	/**
	 * @return the balcony
	 */
	public String getBalcony() {
		return balcony;
	}

	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @return the helpers
	 */
	public String getHelpers() {
		return helpers;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @return the infoYou
	 */
	public String getInfoYou() {
		return infoYou;
	}

	/**
	 * @return the infoPlayer
	 */
	public String getInfoPlayer() {
		return infoPlayer;
	}

	/**
	 * @return the infoGame
	 */
	public String getInfoGame() {
		return infoGame;
	}

	/**
	 * @return the infoOthers
	 */
	public String getInfoOthers() {
		return infoOthers;
	}

	/**
	 * @return the infoCity
	 */
	public String getInfoCity() {
		return infoCity;
	}

	/**
	 * @return the emporiaOfPlayers
	 */
	public String getEmporiaOfPlayers() {
		return emporiaOfPlayers;
	}

	/**
	 * @return the noEmporia
	 */
	public String getNoEmporia() {
		return noEmporia;
	}

	/**
	 * @return the youNoBusiness
	 */
	public String getYouNoBusiness() {
		return youNoBusiness;
	}

	/**
	 * @return the youNoEmporia
	 */
	public String getYouNoEmporia() {
		return youNoEmporia;
	}
}

