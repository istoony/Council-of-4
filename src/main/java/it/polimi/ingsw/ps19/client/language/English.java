package it.polimi.ingsw.ps19.client.language;

import it.polimi.ingsw.ps19.client.clientinput.BuildEmporiumInputs;
import it.polimi.ingsw.ps19.client.clientinput.BuildWithKingInputs;
import it.polimi.ingsw.ps19.client.clientinput.BuyHelperInputs;
import it.polimi.ingsw.ps19.client.clientinput.BuyMainActionInput;
import it.polimi.ingsw.ps19.client.clientinput.ElectCouncillorInputs;
import it.polimi.ingsw.ps19.client.clientinput.EndTurnInput;
import it.polimi.ingsw.ps19.client.clientinput.FastAction;
import it.polimi.ingsw.ps19.client.clientinput.GetBusinessPermitInput;
import it.polimi.ingsw.ps19.client.clientinput.MainAction;
import it.polimi.ingsw.ps19.client.clientinput.MarketSell;
import it.polimi.ingsw.ps19.client.clientinput.RedrawBusinessCardInput;
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

/**
 * English language
 */
public class English extends Language 
{
	private static final long serialVersionUID = 6039022854625788953L;

	public English()
	{
		//setup
		invalidInsertion = "Invalid Insertion!";
		newGame = "new game";
		reconnect = "reconnect to existing game";
		insertPassword = "insert password";
		insertIp = "Insert server IP address";
		insertPort = "Insert server port";
		useStdIp = invalidInsertion + " trying with standard IP address";
		useStdPort = invalidInsertion + "trying with standard port";
		connSuccess = "Connection successful";
		connInsucces = "Connection unsuccessful";
		killClient = "The program will now close";
		waiting = "Trying to connect...";
		socketCreated = "Socket created";
		serverQuits = "Connection has quitted!";
		reconnected = "Reconnected to the game!";
		connPass = "Your connection password is";
		invalidObj = "Invalid object received!";
		
		//general
		helpers = "helpers";
		nothing = "nothing";
		numberOf = "number of";
		available = "available";
	
		//map
		cities = "cities";
		currentCity = "current city";
		bonuses = "bonuses";
		balcony = "balcony";
		region = "region";
		firstCard = "first card";
		secondCard = "second card";
		king = "king";
		politicCards = "politic cards";
		businessCards = "permit tiles";
		noEmporia = "number of emporia";
		emporiaOfPlayers = "emporia of players";
		map = "map";
		nobilityPath = "Nobility Path";
		
		//players
		player = "player";
		numEmporiaLeft = "number of emporia left";
		numOfHelpers = "number of " + helpers;
		money = "coins";
		victoryPoints = "victory points";
		nobilityPoints = "nobility points";
		main = "main actions";
		freeBusiness = "free " + businessCards;
		usedBusiness = "used " + businessCards;
		quick = "quick actions";
		result = "result";
		activePlayerId = "active " + player;
		youArePlayer = "you are " + player;
	
		//choosing title
		chooseActionTypeTitle = "choose the action type";
		chooseActionTitle = "choose an action to perform";
		chooseRegionTitle = "choose a region";
		chooseBalconyTitle = "choose a balcony";
		choosePoliticCardTitle = "choose a politic card";
		chooseBusinessCardTitle = "choose a permit tile";
		chooseCityTitle = "choose a city";
		chooseColor = "choose a color";
				
		//market
		howManyHelpersToSell = "how many " + helpers + " do you want to sell?";
		price = "price";
		setPrice = "set the " + price + " for your order";
		chooseOrder = "choose an order";
		market = "market";
		noOrders = nothing;
		orders = "orders";
	
		mountain = "mountain";
		hill = "hill";
		plain = "plain";
		
		winner = "Winner";
		info = "informations about";
		infoCity = info + " the city";
		infoGame = info + " the game";
		infoYou = info + " you";
		infoOthers = info + " the other players";
		infoPlayer = info +  " " + player;
		position = "position";
		
		youNoEmporia = "You don't have any emporia";
		youNoBusiness = "You don't have any " + businessCards;
		
		//Colors
		 c_FF0000 = "Red";
		 c_0000FF = "Blue";
		 c_FF7F00 = "Orange";
		 c_000000 = "White";
		 c_FFFFFF = "Black";
		 c_FFC0CB = "Pink";
		 c_Joker = "Joker";
	}		
	
	@Override
	public String toString()
	{
		return "English";
	}

	@Override
	public String getString(BuildWithKingInputs input) {
		return "build with the help of the king";
	}

	@Override
	public String getString(BuildEmporiumInputs input) {
		return "build emporium";
	}

	@Override
	public String getString(ElectCouncillorInputs input) {
		return "elect a councillor";
	}

	@Override
	public String getString(EndTurnInput input) {
		return "end turn";
	}

	@Override
	public String getString(GetBusinessPermitInput input) {
		return "buy a business permit";
	}

	@Override
	public String getString(RedrawBusinessCardInput input) {
		return "redraw " + businessCards;
	}

	@Override
	public String getString(BuyHelperInputs input) {
		return "buy helper";
	}

	@Override
	public String getString(BuyMainActionInput input) {
		return "buy an extra " + main;
	}

	@Override
	public String getString(MarketSell input) {
		return "sell on " + market;
	}

	@Override
	public String getString(FastAction input) {
		return quick;
	}

	@Override
	public String getString(MainAction input) {
		return main;
	}

	@Override
	public String getString(DrawBusinessCard input) {
		return "draw a permit tile from a region";
	}

	@Override
	public String getString(DrawPoliticCard input, int howMany) {
		return "+ " + howMany + " " + politicCards;
	}

	@Override
	public String getString(GeneralBonus input) {
		return "general bonus";
	}

	@Override
	public String getString(GetCityBonus input) {
		return "get bonus from one of your " + cities;
	}

	@Override
	public String getString(MoreHelpers input, int howMany) {
		return "+ " + howMany + " " + helpers;

	}

	@Override
	public String getString(MoreMainAction input, int howMany) {
		return "+ " + howMany + " " + main + "actions";
	}

	@Override
	public String getString(MoreMoney input, int howMany) {
		return "+ " + howMany + " " + money;
	}

	@Override
	public String getString(MoreNobilityPoints input, int howMany) {
		return "+ " + howMany + " " + nobilityPoints;
	}

	@Override
	public String getString(MoreVictoryPoints input, int howMany) {
		return "+ " + howMany + " " + victoryPoints;
	}

	@Override
	public String getString(ReuseBusinessCardBonus input) {
		return "bonus from on of your " + businessCards;
	}
}
