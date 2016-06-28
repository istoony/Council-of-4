package it.polimi.ingsw.PS19.controller;

import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import it.polimi.ingsw.ps19.controller.GameController;
import it.polimi.ingsw.ps19.message.requests.GetBusinessCardMessage;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.parameter.RegionType;
import it.polimi.ingsw.ps19.server.WaitingRoom;
import it.polimi.ingsw.ps19.view.connection.Connection;
import it.polimi.ingsw.ps19.view.connection.RMIConnection;

public class DrawBusinessCard {

	@Test
	public void test() 
	{
		Connection uno = new RMIConnection(true);
			uno.setActive();
		Connection due = new RMIConnection(true);
			due.setActive();
	
		Map<Integer, Connection> wRoom = new HashMap<>();
		wRoom.put(0, uno);
		wRoom.put(1, due);
		
		WaitingRoom.setConnection(wRoom);
		
		
		List<Integer> players = new ArrayList<>();
		players.add(0);
		players.add(1);
		Model m = new Model(players);
			
		//Set to player 0 many cards
		m.getPlayerById(0).setPoliticCardToDraw(50);
		
		GameController g = new GameController(m);
		
		assertTrue("card: " + m.getPlayerById(0).getPoliticcard().size(), m.getPlayerById(0).getPoliticcard().size() == 50 + 6);
		
		ArrayList<Color> card = new ArrayList<>();
		BusinessCard first = m.getMap().getRegionByType(RegionType.MOUNTAIN).getFirstcard();
		
		card.add(Color.decode("#FEFEFE"));
		card.add(Color.decode("#FEFEFE"));
		card.add(Color.decode("#FEFEFE"));
		card.add(Color.decode("#FEFEFE"));
		
		GetBusinessCardMessage mess = new GetBusinessCardMessage(m.getMap().getRegionByType(RegionType.MOUNTAIN).getFirstcard(),RegionType.MOUNTAIN);
		mess.setId(0);
		assertTrue(mess.getCard().getId() == first.getId());
		
		g.update(null, mess);
		
		assertTrue(m.getPlayerById(0).getFreebusinesscard().get(0).getId()==first.getId());
		
		assertTrue(m.getPlayerById(0).getMainActionCounter() == 0);
		//System.out.println(m.getMap().getRegionByType(RegionType.HILL).getBusinessdeck().getCard().size());
		
		assertTrue(m.getMap().getRegionByType(RegionType.HILL).getBusinessdeck().getCard().size() == 3-2 );
		
		//System.out.println(m.getMap().getRegionByType(RegionType.MOUNTAIN).getBusinessdeck().getCard().size());
		//Cam
		//assertTrue(m.getMap().getRegionByType(RegionType.MOUNTAIN).getBusinessdeck().getCard().size() == 6-2-1 );
		
		//System.out.println(m.getMap().getRegionByType(RegionType.PLAIN).getBusinessdeck().getCard().size());
		//CHECK NUMBER OF CARD ON politicscard.xml
		//assertTrue(m.getMap().getRegionByType(RegionType.PLAIN).getBusinessdeck().getCard().size() == 8-2 );
		//System.out.println(m.toString());
		
		//This assert depends of a random bonus geven to player
		//assertTrue(m.getPlayerById(0).getMoney() == 10 - 4 );
		//System.out.println(m.toString());
	}

}
