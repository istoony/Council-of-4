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

/**
 * English language
 */
public class English extends Language 
{
	public English()
	{
		//setup
		newGame = "new game";
		reconnect = "reconnect to existing game";
		insertPassword = "insert password";
	
		//general
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
	
		//players
		player = "player";
		numEmporiaLeft = "number of emporia left";
		numOfHelpers = "number of " + helpers;
		money = "coins";
		victoryPoints = "victory points";
		nobilityPoints = "nobility points";
		main = "main";
		freeBusiness = "free " + businessCards;
		usedBusiness = "used " + businessCards;
		quick = "quick";
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
		setPrice = "set the " + price + " for your order";
		chooseOrder = "choose an order";
		market = "market";
		noOrders = nothing;
		orders = "orders";
		price = "price";
		helpers = "helpers";
	
		mountain = "mountain";
		hill = "hill";
		plain = "plain";
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
		return "redraw business permits";
	}

	@Override
	public String getString(BuyHelperInputs input) {
		return "buy helper";
	}

	@Override
	public String getString(BuyMainActionInput input) {
		return "buy an extra main action";
	}

	@Override
	public String getString(MarketSell input) {
		return "sell on market";
	}

	@Override
	public String getString(FastAction input) {
		return "quick action";
	}

	@Override
	public String getString(MainAction input) {
		return "main action";
	}
}
