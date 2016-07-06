package it.polimi.ingsw.PS19.controller;

import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import it.polimi.ingsw.ps19.controller.GameController;
import it.polimi.ingsw.ps19.controller.support.ActionMessages;
import it.polimi.ingsw.ps19.message.replies.CompleteMarketReply;
import it.polimi.ingsw.ps19.message.replies.SendFullPlayerReply;
import it.polimi.ingsw.ps19.message.replies.WaitingPlayerForMarketReply;
import it.polimi.ingsw.ps19.message.requests.BuyOrderMessage;
import it.polimi.ingsw.ps19.message.requests.ChangeKingPositionMessage;
import it.polimi.ingsw.ps19.message.requests.ElectCouncillorMessage;
import it.polimi.ingsw.ps19.message.requests.EndTurnMessage;
import it.polimi.ingsw.ps19.message.requests.SendOrderMessage;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.Order;
import it.polimi.ingsw.ps19.model.card.PoliticsCard;
import it.polimi.ingsw.ps19.model.parameter.RegionType;
import it.polimi.ingsw.ps19.server.WaitingRoom;
import it.polimi.ingsw.ps19.view.connection.Connection;
import it.polimi.ingsw.ps19.view.connection.RMIConnection;

public class MarketTest {
	
	@Test
	public void test() 
	{
		/**
		 * Creo 5 player e ne disconnetto subito 3 in modo da avere la certezza che il market funziona 
		 * anche quando alcuni player si sono disconnessi.
		 * Faccio compiere una main action e una fast action ai due player rimanenti controllando i risultati,
		 * finite le azioni controllo se il server è entrato nella fase di "TimeToMarket".
		 * 
		 * i due player creano un ordine e lo inseriscono nel messaggio di SendOrderMessage inviandolo al controller.
		 * Il controller sceglie a caso il turno del primo player che deve comprare, e il player attivo invia
		 * il messaggio di BuyOrderMessage. La stessa cosa avviene anche per il secondo player.
		 * Infine controllo che i riultati ottenuti siano coerenti.
		 */
		Connection uno = new RMIConnection(true);
			uno.setActive();
		Connection due = new RMIConnection(true);
			due.setActive();
		Connection tre = new RMIConnection(true);
			tre.setActive();
			tre.setDisconnected();
		Connection quattro = new RMIConnection(true);
			quattro.setActive();
			quattro.setDisconnected();
		Connection cinque = new RMIConnection(true);
			cinque.setActive();
			cinque.setDisconnected();
	
		Map<Integer, Connection> wRoom = new HashMap<>();
		wRoom.put(10, uno);
		wRoom.put(11, due);
		wRoom.put(12, tre);
		wRoom.put(13, quattro);
		wRoom.put(14, cinque);
	
		WaitingRoom.setConnection(wRoom);
	
	
		List<Integer> players = new ArrayList<>();
		players.add(10);
		players.add(11);
		players.add(12);
		players.add(13);
		players.add(14);
		
		Model m = new Model(players);
		m.getCurrentState().addDisconnectedPlayer(12);

		m.getCurrentState().addDisconnectedPlayer(13);

		m.getCurrentState().addDisconnectedPlayer(14);
		
		GameController g = new GameController(m);
		
		//Do al player 100 money per poter eseguire mosse senza errori
		m.getPlayerById(10).setMoney(100);
		assertTrue("money: " +m.getPlayerById(10).getMoney(), m.getPlayerById(10).getMoney() == 100);
		
		//Do al player 4 joker in modo da poter eseguire la mossa di change king senza problemi.
		m.getPlayerById(10).addCardToHand(new PoliticsCard(Color.decode("#FEFEFE")));
		m.getPlayerById(10).addCardToHand(new PoliticsCard(Color.decode("#FEFEFE")));
		m.getPlayerById(10).addCardToHand(new PoliticsCard(Color.decode("#FEFEFE")));
		m.getPlayerById(10).addCardToHand(new PoliticsCard(Color.decode("#FEFEFE")));
		m.getPlayerById(10).addCardToHand(new PoliticsCard(Color.decode("#FEFEFE")));
		
		ArrayList<Color> card = new ArrayList<>();
		
		card.add(Color.decode("#FEFEFE"));
		card.add(Color.decode("#FEFEFE"));
		card.add(Color.decode("#FEFEFE"));
		card.add(Color.decode("#FEFEFE"));
		
		ChangeKingPositionMessage kingPos = new ChangeKingPositionMessage(m.getMap().getRegionByType(RegionType.HILL).getCityById(7), card);
		kingPos.setId(10);
		
		g.update(null, kingPos);
		
		assertTrue(g.getReply().getResult(), g.getReply().getResult().equals(ActionMessages.EVERYTHING_IS_OK));
		assertTrue(m.getMap().getKing().getCurrentcity().getId() == 7);
		assertTrue("counter: " + m.getPlayerById(10).getMainActionCounter(), m.getPlayerById(10).getMainActionCounter() == 0);
		//assertTrue("money: " +m.getPlayerById(10).getMoney(), m.getPlayerById(10).getMoney() == 100 - 4 - 2);
		//assertTrue("card: " + m.getPlayerById(0).getPoliticcard().size(), m.getPlayerById(0).getPoliticcard().size() == 8 - 4);

		EndTurnMessage endTurn = new EndTurnMessage();
		endTurn.setId(10);
		
		g.update(null, endTurn);
		
		assertTrue("turn: " + m.getCurrentState().getPlayerTurnId(), m.getCurrentState().getPlayerTurnId() == 11);
		
		ElectCouncillorMessage elect = new ElectCouncillorMessage(Color.decode("#FF0000"), RegionType.HILL);
		elect.setId(11);
		elect.setMainAction(true);
		
		g.update(null, elect);
		
		assertTrue(g.getReply().getResult().equals(ActionMessages.EVERYTHING_IS_OK));
		assertTrue(m.getPlayerById(11).getMainActionCounter() == 0);
		
		EndTurnMessage endTurn1 = new EndTurnMessage();
		endTurn1.setId(11);
		
		g.update(null, endTurn1);
		
		assertTrue("turn: " + m.getCurrentState().getPlayerTurnId(), m.getCurrentState().getPlayerTurnId() == 11);
		/**
		 * Finito di eseguire le azioni principali imposto il time to market
		 */
		assertTrue(m.getCurrentState().isTimeToMarket());
		assertTrue("idTurn" + m.getCurrentState().getPlayerTurnId(), m.getCurrentState().getPlayerTurnId() == 11);

		/**
		 * il player 1 crea l'ordine
		 */
		Order o1 = new Order();
		o1.setHelper(1);
		o1.addPoliticsCard(m.getPlayerById(11).getPoliticcard().get(0).getColor());
		o1.setPrice(3);
		
		SendOrderMessage order1 = new SendOrderMessage(o1);
		order1.setId(11);
		
	
		g.update(null, order1);
		
		
		assertTrue("class: " + g.getReply().getResult(), (g.getReply() instanceof WaitingPlayerForMarketReply));
		
		assertTrue("size" + m.getMarket().getSize(), m.getMarket().getSize() == 1);
		assertTrue("marketsize: " + m.getMarket().getListoforder().get(11), m.getMarket().getListoforder().get(11) == o1);
		assertTrue(!m.getCurrentState().isPlayerBought(11));
		assertTrue(m.getCurrentState().isTimeToMarket());
		
		/**
		 * il player 0 crea l'ordine
		 */
		Order o = new Order();
		o.setHelper(1);
		o.addPoliticsCard(m.getPlayerById(10).getPoliticcard().get(0).getColor());
		o.setPrice(3);
		
		SendOrderMessage order = new SendOrderMessage(o);
		order.setId(10);
	
		g.update(null, order);
		
		assertTrue("class: " + g.getReply().getClass(), g.getReply() instanceof CompleteMarketReply);
		
		assertTrue(m.getMarket().getSize() == 2);
		assertTrue(m.getMarket().getListoforder().get(10) == o);
		assertTrue(!m.getCurrentState().isPlayerBought(10));
		assertTrue(m.getCurrentState().isTimeToMarket());
		
		
		/**
		 * ricevuti entrambi gli ordini controllo il messaggio che il server invia ai client
		 */
		
		assertTrue(g.getReply() instanceof CompleteMarketReply);
		
		CompleteMarketReply market = (CompleteMarketReply) g.getReply();
		
		assertTrue(market.getMarket() == m.getMarket());
		assertTrue(market.getActivePlayer() == 10 || market.getActivePlayer() == 11);
		assertTrue(market.getId() == -1);
		assertTrue(m.getCurrentState().isTimeToMarket());
		assertTrue(m.getMarket().getSize() == 2);
		
		/**
		 * dato che il turno è casuale creo la variabile e lavoro su questa.
		 */
		
		int playerturn = market.getActivePlayer();
		BuyOrderMessage buyOrder;
		if(playerturn == 10)
			buyOrder = new BuyOrderMessage(o1, 11);
		else
			buyOrder = new BuyOrderMessage(o, 10);
		buyOrder.setId(playerturn);
		
		g.update(null, buyOrder);
		
		assertTrue("class: " + g.getReply().getClass(), g.getReply() instanceof CompleteMarketReply);
		assertTrue(m.getCurrentState().isTimeToMarket());
		assertTrue(m.getMarket().getSize() == 1);
		
		/**
		 * dato che il turno è casuale creo la variabile e lavoro su questa.
		 * Leggo il turno successivo con l'active player
		 * cambia la risposta
		 */
		CompleteMarketReply market2 = (CompleteMarketReply) g.getReply();
		assertTrue(market != market2);
		
		int playerturn2 = market2.getActivePlayer();
		
		assertTrue(playerturn != playerturn2);
		
		BuyOrderMessage buyOrder2;
		if(playerturn2 == 10)
			buyOrder2 = new BuyOrderMessage(o1, 11);
		else
			buyOrder2 = new BuyOrderMessage(o, 10);
		buyOrder2.setId(playerturn2);
		
		g.update(null, buyOrder2);
		
		//assertTrue("class: "+ g.getReply().getClass(),g.getReply() instanceof SendFullPlayerReply);
		/**
		 * Controllo che non è più TimeToMarket e controllo che la grandezza del mercato sia zero
		 */
		assertTrue(!m.getCurrentState().isTimeToMarket());
		assertTrue("marketsize: " + m.getMarket().getSize(), m.getMarket().getSize() == 0);
		assertTrue("class: "+ g.getReply().getClass(),g.getReply() instanceof SendFullPlayerReply);
	}
}
