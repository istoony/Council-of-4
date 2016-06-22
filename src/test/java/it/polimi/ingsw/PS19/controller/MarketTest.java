package it.polimi.ingsw.PS19.controller;

import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import it.polimi.ingsw.ps19.controller.GameController;
import it.polimi.ingsw.ps19.controller.action.ActionMessages;
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
		//ID 0 - 1 - 2
		Connection uno = new RMIConnection();
		Connection due = new RMIConnection();
		Map<Integer, Connection> wRoom = new HashMap<>();
		wRoom.put(0, uno);
		wRoom.put(1, due);
		
		WaitingRoom.setConnection(wRoom);
		
		List<Integer> players = new ArrayList<>();
		players.add(0);
		players.add(1);
		//players.add(2);
		
		Model m = new Model(players);
		
		GameController g = new GameController(m);
		
		m.getPlayerById(0).setMoney(100);
		assertTrue("money: " +m.getPlayerById(0).getMoney(), m.getPlayerById(0).getMoney() == 100);
		//Disconnetto il 2
		//PlayerDisconnectedMessage disconnected = new PlayerDisconnectedMessage(2);
		
		//assertTrue(null, m.getCurrentState().getPlayerTurnId() == 0);
		
		/*g.update(null, disconnected);
		
		assertTrue("turn: "+ m.getCurrentState().getPlayerTurnId(), m.getCurrentState().getPlayerTurnId() == 0);
		assertTrue(g.getReply().getResult(), g.getReply().getResult().equals(ActionMessages.PLAYER_DISCONNECTED + "2"));
		assertTrue("card: " + m.getPlayerById(0).getPoliticcard().size(), m.getPlayerById(0).getPoliticcard().size() == 7);
		*/
		m.getPlayerById(0).addCardToHand(new PoliticsCard(Color.decode("#FEFEFE")));
		m.getPlayerById(0).addCardToHand(new PoliticsCard(Color.decode("#FEFEFE")));
		m.getPlayerById(0).addCardToHand(new PoliticsCard(Color.decode("#FEFEFE")));
		m.getPlayerById(0).addCardToHand(new PoliticsCard(Color.decode("#FEFEFE")));
		m.getPlayerById(0).addCardToHand(new PoliticsCard(Color.decode("#FEFEFE")));
		
		ArrayList<Color> card = new ArrayList<>();
		
		card.add(Color.decode("#FEFEFE"));
		card.add(Color.decode("#FEFEFE"));
		card.add(Color.decode("#FEFEFE"));
		card.add(Color.decode("#FEFEFE"));
		
		ChangeKingPositionMessage kingPos = new ChangeKingPositionMessage(m.getMap().getRegionByType(RegionType.HILL).getCityById(7), card);
		kingPos.setId(0);
		
		g.update(null, kingPos);
		
		assertTrue(g.getReply().getResult(), g.getReply().getResult().equals(ActionMessages.EVERYTHING_IS_OK));
		assertTrue(m.getMap().getKing().getCurrentcity().getId() == 7);
		assertTrue("counter: " + m.getPlayerById(0).getMainActionCounter(), m.getPlayerById(0).getMainActionCounter() == 0);
		assertTrue("money: " +m.getPlayerById(0).getMoney(), m.getPlayerById(0).getMoney() == 100 - 4 - 2);
		//assertTrue("card: " + m.getPlayerById(0).getPoliticcard().size(), m.getPlayerById(0).getPoliticcard().size() == 8 - 4);

		EndTurnMessage endTurn = new EndTurnMessage();
		endTurn.setId(0);
		
		g.update(null, endTurn);
		
		assertTrue("turn: " + m.getCurrentState().getPlayerTurnId(), m.getCurrentState().getPlayerTurnId() == 1);
		
		ElectCouncillorMessage elect = new ElectCouncillorMessage(Color.decode("#FF0000"), RegionType.HILL);
		elect.setId(1);
		elect.setMainAction(true);
		
		g.update(null, elect);
		
		assertTrue(g.getReply().getResult().equals(ActionMessages.EVERYTHING_IS_OK));
		assertTrue(m.getPlayerById(1).getMainActionCounter() == 0);
		
		EndTurnMessage endTurn1 = new EndTurnMessage();
		endTurn1.setId(1);
		
		g.update(null, endTurn1);
		
		assertTrue("turn: " + m.getCurrentState().getPlayerTurnId(), m.getCurrentState().getPlayerTurnId() == 1);
		/**
		 * Finito di eseguire le azioni principali imposto il time to market
		 */
		assertTrue(m.getCurrentState().isTimeToMarket());
		
		/**
		 * il player 0 crea l'ordine
		 */
		Order o = new Order();
		o.setHelper(1);
		o.addPoliticsCard(m.getPlayerById(0).getPoliticcard().get(0).getColor());
		o.setPrice(3);
		
		SendOrderMessage order = new SendOrderMessage(o);
		order.setId(0);
	
		g.update(null, order);
		
		assertTrue("class: " + g.getReply().getClass(), g.getReply() instanceof WaitingPlayerForMarketReply);
		
		assertTrue(m.getMarket().getSize() == 1);
		assertTrue(m.getMarket().getListoforder().get(0) == o);
		assertTrue(!m.getCurrentState().isPlayerBought(0));
		assertTrue(m.getCurrentState().isTimeToMarket());
		
		/**
		 * il player 1 crea l'ordine
		 */
		Order o1 = new Order();
		o1.setHelper(1);
		o1.addPoliticsCard(m.getPlayerById(0).getPoliticcard().get(0).getColor());
		o1.setPrice(3);
		
		SendOrderMessage order1 = new SendOrderMessage(o1);
		order1.setId(1);
		
	
		g.update(null, order1);
		
		assertTrue("class: " + g.getReply().getClass(), g.getReply() instanceof CompleteMarketReply);
		
		assertTrue(m.getMarket().getSize() == 2);
		assertTrue("marketsize: " + m.getMarket().getListoforder().get(1), m.getMarket().getListoforder().get(1) == o1);
		assertTrue(!m.getCurrentState().isPlayerBought(1));
		assertTrue(m.getCurrentState().isTimeToMarket());
		
		/**
		 * ricevuti entrambi gli ordini controllo il messaggio che il server invia ai client
		 */
		
		CompleteMarketReply market = (CompleteMarketReply) g.getReply();
		
		assertTrue(market.getMarket() == m.getMarket());
		assertTrue(market.getActivePlayer() == 0 || market.getActivePlayer() == 1);
		assertTrue(market.getId() == -1);
		assertTrue(m.getCurrentState().isTimeToMarket());
		assertTrue(m.getMarket().getSize() == 2);
		
		/**
		 * dato che il turno è casuale creo la variabile e lavoro su questa.
		 */
		
		int playerturn = market.getActivePlayer();
		BuyOrderMessage buyOrder;
		if(playerturn == 0)
			buyOrder = new BuyOrderMessage(o1, 1);
		else
			buyOrder = new BuyOrderMessage(o, 0);
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
		if(playerturn2 == 0)
			buyOrder2 = new BuyOrderMessage(o1, 1);
		else
			buyOrder2 = new BuyOrderMessage(o, 0);
		buyOrder2.setId(playerturn2);
		
		g.update(null, buyOrder2);
		
		//assertTrue("class: "+ g.getReply().getClass(),g.getReply() instanceof SendFullPlayerReply);
		assertTrue(!m.getCurrentState().isTimeToMarket());
		assertTrue("marketsize: " + m.getMarket().getSize(), m.getMarket().getSize() == 0);
		assertTrue("class: "+ g.getReply().getClass(),g.getReply() instanceof SendFullPlayerReply);
	}

}
