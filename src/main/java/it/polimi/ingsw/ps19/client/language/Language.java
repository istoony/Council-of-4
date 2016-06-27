package it.polimi.ingsw.ps19.client.language;

import it.polimi.ingsw.ps19.client.clientaction.BuildEmporiumInputs;
import it.polimi.ingsw.ps19.client.clientaction.BuildWithKingInputs;
import it.polimi.ingsw.ps19.client.clientaction.BuyHelperInputs;
import it.polimi.ingsw.ps19.client.clientaction.BuyMainActionInput;
import it.polimi.ingsw.ps19.client.clientaction.ElectCouncillorInputs;
import it.polimi.ingsw.ps19.client.clientaction.EndTurnInput;
import it.polimi.ingsw.ps19.client.clientaction.FastAction;
import it.polimi.ingsw.ps19.client.clientaction.GetBusinessPermitInput;
import it.polimi.ingsw.ps19.client.clientaction.MainAction;
import it.polimi.ingsw.ps19.client.clientaction.MarketSell;
import it.polimi.ingsw.ps19.client.clientaction.RedrawBusinessCardInput;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

/**
 * Class with string constants for language
 */
public abstract class Language 
{
	//Server constants
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
	public static final String GUI = "RMI";
	
	//setup
	public String newGame;
	public String reconnect;
	public String insertPassword;
	
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
	
	/**
	 * returns string
	 * @param r
	 * @return
	 */
	public String getString(RegionType r)
	{
		String s = nothing;
		if(r.equals(RegionType.PLAIN))
			s = plain;
		else if(r.equals(RegionType.HILL))
			s = hill;
		else if(r.equals(RegionType.MOUNTAIN))
			s = mountain;
		return s;
	}
}
