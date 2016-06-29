package it.polimi.ingsw.ps19.client.language;

import java.awt.Color;

import it.polimi.ingsw.ps19.client.clientaction.BuildEmporiumInputs;
import it.polimi.ingsw.ps19.client.clientaction.BuildWithKingInputs;
import it.polimi.ingsw.ps19.client.clientaction.BuyHelperInputs;
import it.polimi.ingsw.ps19.client.clientaction.BuyMainActionInput;
import it.polimi.ingsw.ps19.client.clientaction.ClientAction;
import it.polimi.ingsw.ps19.client.clientaction.ElectCouncillorInputs;
import it.polimi.ingsw.ps19.client.clientaction.EndTurnInput;
import it.polimi.ingsw.ps19.client.clientaction.FastAction;
import it.polimi.ingsw.ps19.client.clientaction.GetBusinessPermitInput;
import it.polimi.ingsw.ps19.client.clientaction.MainAction;
import it.polimi.ingsw.ps19.client.clientaction.MarketSell;
import it.polimi.ingsw.ps19.client.clientaction.RedrawBusinessCardInput;
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
public abstract class Language 
{
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
	
	//General
	public static final String SOCKET = "Socket";
	public static final String RMI = "RMI";
	public static final String CLI = "CLI";
	public static final String GUI = "GUI";
	
	//setup
	public String insertIp;
	public String insertPort;
	public String tryConn;
	public String useStdIp;
	public String useStdPort;
	public String newGame;
	public String reconnect;
	public String insertPassword;
	public String connSuccess;
	public String connInsucces;
	public String killClient;
	public String waiting;
	public String invalidInsertion;
	public String socketCreated;
	public String serverQuits;
	public String reconnected;
	public String connPass;
	public String invalidObj;
	
	//general
	public String nothing;
	public String numberOf;
	public String available;
	
	//map
	public String cities;
	public String currentCity;
	public String bonuses;
	public String balcony;
	public String region;
	public String firstCard;
	public String secondCard;
	public String king;
	public String politicCards;
	public String businessCards;
	public String noEmporia;
	public String emporiaOfPlayers;
	public String map;
	
	//players
	public String player;
	public String numEmporiaLeft;
	public String numOfHelpers;
	public String money;
	public String victoryPoints;
	public String nobilityPoints;
	public String main;
	public String freeBusiness;
	public String usedBusiness;
	public String quick;
	public String result;
	public String activePlayerId;
	public String youArePlayer;
	
	//choosing title
	public String chooseActionTypeTitle;
	public String chooseActionTitle;
	public String chooseRegionTitle;
	public String chooseBalconyTitle;
	public String choosePoliticCardTitle;
	public String chooseBusinessCardTitle;
	public String chooseCityTitle;
	public String chooseColor;
	
	//market
	public String howManyHelpersToSell;
	public String setPrice;
	public String chooseOrder;
	public String market;
	public String noOrders;
	public String orders;
	public String price;
	public String helpers;
	
	public String mountain;
	public String hill;
	public String plain;
	
	public String winner;
	
	public String info;
	public String infoCity;
	public String infoGame;
	public String infoYou;
	public String infoOthers;
	public String infoPlayer;
	public String position;
	
	public String youNoEmporia;
	public String youNoBusiness;
	
	//Colors
	public String c_FF0000;
	public String c_0000FF;
	public String c_FF7F00;
	public String c_000000;
	public String c_FFFFFF;
	public String c_FFC0CB;
	public String c_Joker;
	
	//Actions
	public abstract String getString(BuildWithKingInputs input);
	public abstract String getString(BuildEmporiumInputs input);
	public abstract String getString(ElectCouncillorInputs input);
	public abstract String getString(EndTurnInput input);
	public abstract String getString(GetBusinessPermitInput input);
	public abstract String getString(RedrawBusinessCardInput input);
	public abstract String getString(BuyHelperInputs input);
	public abstract String getString(BuyMainActionInput input);
	public abstract String getString(MarketSell input);
	
	public abstract String getString(FastAction input);
	public abstract String getString(MainAction input);
	
	public abstract String getString(DrawBusinessCard input);
	public abstract String getString(DrawPoliticCard input, int howMany);
	public abstract String getString(GeneralBonus input);
	public abstract String getString(GetCityBonus input);
	public abstract String getString(MoreHelpers input, int howMany);
	public abstract String getString(MoreMainAction input, int howMany);
	public abstract String getString(MoreMoney input, int howMany);
	public abstract String getString(MoreNobilityPoints input, int howMany);
	public abstract String getString(MoreVictoryPoints input, int howMany);
	public abstract String getString(ReuseBusinessCardBonus input);

	public String getString(PoliticsCard card)
	{
		if(card == null)
			return nothing;
		return getString(card.getColor());
	}
	
	public String getString(ClientAction input)
	{
		return input.toString(this);
	}

	public String getString(Balcony b)
	{
		String s = balcony + ": [";
		for(Color c : b.getCouncilcolor())
		{
			s = s.concat(getString(c) + ", ");
		}
		s += "]\n";
		return s;
	}
	
	public String getString(Order order)
	{
		if(order == null)
			return nothing;
		String s = "[" + helpers + ": " + order.getHelper();
		s += ", " + politicCards + ": ";
		if(order.getPoliticscard().isEmpty())
			s += "0,";
		else
			for(Color card : order.getPoliticscard())
				s = s.concat(getString(card) + ",");
		s += " " + businessCards + ": ";
		if(order.getBusinesscard().isEmpty())
			s += "0,";
		else
			for(BusinessCard card : order.getBusinesscard())
				s = s.concat(getString(card) + ",");
		s += " " + price + ": " + order.getPrice() + "]";
		return s;
	}

	public String getString(BusinessCard card)
	{
		if(card == null)
			return nothing;
		String s = "[";
		s = s.concat(cities + ": ");
		for(City city: card.getCity())
		{
			s = s.concat(getString(city));
			s = s.concat(", ");
		}
		s += bonuses + ": ";
		if(card.getBonus().isEmpty())
			s += "0";
		else
		{
			for(Bonus b : card.getBonus())
			{
				s = s.concat(getString(b));
				s = s.concat(", ");
			}
		}
		s += "]\n";
		return s;
	}
	
	public String getString(Bonus b)
	{
		return b.toString(this);
	}
	
	public String getString(City city)
	{
		return city.getName();
	}
	
	public String getString(Color c){
		String s="";
		if (c==null){
			s = nothing;
		}
		else if(c.equals(Color.decode("#FF0000"))){
			s= c_FF0000;
		}
		else if(c.equals(Color.decode("#0000FF"))){
			s= c_0000FF;
		}
		else if(c.equals(Color.decode("#FF7F00"))){
			s= c_FF7F00;
		}
		else if(c.equals(Color.decode("#000000"))){
			s= c_000000;
		}
		else if(c.equals(Color.decode("#FFFFFF"))){
			s=  c_FFFFFF;
		}
		else if(c.equals(Color.decode("#FFC0CB"))){
			s= c_FFC0CB;
		}
		else if(c.equals(Color.decode(Costants.JOKERCOLOR))){
			s= c_Joker;
		}
		else{
			s = "#" + Integer.toHexString(c.getRGB()).substring(2).toUpperCase();
		}
		return s;
	}
	
	/**
	 * returns string
	 * @param r
	 * @return
	 */
	public String getString(RegionType r)
	{
		String s = nothing;
		if(r.equals(RegionType.PLAIN))
			s = plain.toUpperCase();
		else if(r.equals(RegionType.HILL))
			s = hill.toUpperCase();
		else if(r.equals(RegionType.MOUNTAIN))
			s = mountain.toUpperCase();
		return s;
	}
}
