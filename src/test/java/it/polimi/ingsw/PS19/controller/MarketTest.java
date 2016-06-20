package it.polimi.ingsw.PS19.controller;

import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.PS19.controller.action.ActionMessages;
import it.polimi.ingsw.PS19.message.requests.ChangeKingPositionMessage;
import it.polimi.ingsw.PS19.message.requests.ElectCouncillorMessage;
import it.polimi.ingsw.PS19.message.requests.EndTurnMessage;
import it.polimi.ingsw.PS19.message.requests.PlayerDisconnectedMessage;
import it.polimi.ingsw.PS19.message.requests.SendOrderMessage;
import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.Order;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class MarketTest {

	@Test
	public void test() 
	{
		//ID 0 - 1 - 2
		Model m = new Model(3);
		
		GameController g = new GameController(m);
		
		//Disconnetto il 2
		PlayerDisconnectedMessage disconnected = new PlayerDisconnectedMessage(2);
		
		assertTrue(null, m.getCurrentState().getPlayerTurnId() == 0);
		
		g.update(null, disconnected);
		
		assertTrue("turn: "+ m.getCurrentState().getPlayerTurnId(), m.getCurrentState().getPlayerTurnId() == 0);
		assertTrue(g.getReply().getResult(), g.getReply().getResult().equals(ActionMessages.PLAYER_DISCONNECTED + "2"));
		assertTrue("card: " + m.getPlayerById(0).getPoliticcard().size(), m.getPlayerById(0).getPoliticcard().size() == 8);
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
		assertTrue(m.getPlayerById(0).getMainActionCounter() == 0);
		assertTrue("money: 0" +m.getPlayerById(0).getMoney(), m.getPlayerById(0).getMoney() == 10 - 4 - 2);
		assertTrue("card: " + m.getPlayerById(0).getPoliticcard().size(), m.getPlayerById(0).getPoliticcard().size() == 8 - 4);

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
		
		assertTrue("turn: " + m.getCurrentState().getPlayerTurnId(), m.getCurrentState().getPlayerTurnId() == 0);
		
		assertTrue(m.getCurrentState().getTimeToMarket());
		
		Order o = new Order();
		o.setHelper(1);
		o.addPoliticsCard(m.getPlayerById(0).getPoliticcard().get(0).getColor());
		o.setPrice(3);
		
		SendOrderMessage order = new SendOrderMessage(o);
	
		g.update(null, order);
		
		assertTrue(m.getMarket().getSize() == 1);
		assertTrue(m.getMarket().getListoforder().get(0) == o);
		
	}

}
