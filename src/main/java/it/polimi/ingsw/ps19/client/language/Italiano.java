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
 * Lingua Italiana
 */
public final class Italiano extends Language
{
	public Italiano()
	{
		//setup
		newGame = "nuovo gioco";
		reconnect = "riconnettiti a un gioco già esistente";
		insertPassword = "inserisci password" ;
		
		//general
		nothing = "niente";
		numberOf = "numero di";
		available = "disponibili";
		
		//map
		cities = "città";
		currentCity = "città attuale";
		bonuses = "bonus";
		balcony = "balcone";
		region = "regione";
		firstCard = "prima carta";
		secondCard = "seconda carta";
		king = "RE";
		politicCards = "carte politica";
		businessCards = "tessere permesse";
		noEmporia = "numero di empori";
		emporiaOfPlayers = "empori dei giocatori";
		map = "mappa";
		
		//players
		player = "giocatore";
		numEmporiaLeft = "numero di empori rimanenti";
		numOfHelpers = "numero di" + helpers;
		money = "monete";
		victoryPoints = "punti vittoria";
		nobilityPoints = "punti nobiltà";
		main = "principale";
		freeBusiness = businessCards + " libere";
		usedBusiness = businessCards + " usate";
		quick = "veloci";
		result = "risultato";
		activePlayerId = player + " attivo";
		youArePlayer = "tu sei il " + player;
		
		//choosing title
		chooseActionTypeTitle = "scegli il tipo di azione";
		chooseActionTitle = "scegli un azione";
		chooseRegionTitle = "scegli la regione";
		chooseBalconyTitle = "scegli il balcone";
		choosePoliticCardTitle = "scegli la carta politica";
		chooseBusinessCardTitle = "scegli la tessera permesso";
		chooseCityTitle = "scegli la città";
		chooseColor = "scegli color";
				
		//market
		howManyHelpersToSell = "quanti " + helpers + "vuoi vendere?";
		setPrice = "definisci il " + price;
		chooseOrder = "scegli l'ordine da comprare";
		market = "mercato";
		noOrders = "numero di ordini";
		orders = "ordini";
		price = "prezzo";
		helpers = "aiutanti";
		
		mountain = "montagna";
		hill = "collina";
		plain = "pianura";
	}
	
	@Override
	public String toString()
	{
		return "Italiano";
	}

	@Override
	public String getString(BuildWithKingInputs input) {
		return "Costruisci con l'aiuto del re";
	}

	@Override
	public String getString(BuildEmporiumInputs input) {
		return "Costruisci un emporio";
	}

	@Override
	public String getString(ElectCouncillorInputs input) {
		return "Eleggi un consigliere";
	}

	@Override
	public String getString(EndTurnInput input) {
		return "Finisci il turno";
	}

	@Override
	public String getString(GetBusinessPermitInput input) {
		return "compra una tessera permesso";
	}

	@Override
	public String getString(RedrawBusinessCardInput input) {
		return "Ridistrubisci le tessere permesso";
	}

	@Override
	public String getString(BuyHelperInputs input) {
		return "Compra un aiutante";
	}

	@Override
	public String getString(BuyMainActionInput input) {
		return "Compi un'azione principale aggiuntiva";
	}

	@Override
	public String getString(MarketSell input) {
		return "vendi nel market";
	}

	@Override
	public String getString(FastAction input) {
		return "azione " + quick;
	}

	@Override
	public String getString(MainAction input) {
		return "azione " + main;
	}

}
