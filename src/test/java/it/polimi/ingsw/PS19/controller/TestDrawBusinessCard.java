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

public class TestDrawBusinessCard {

	@Test
	public void test() 
	{
		/**
		 * In questo test provo a pescare sia la prima che la seconda business permit card della regione mountain.
		 * Per iniziare do al player tante carte politica (50) 
		 * Creo successivamente due messaggii di DrawBusiness Card e li invio al controller, il primo serve per pescare la prima carta,
		 * il secondo per la seconda.
		 */
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
			
		//Set to player 0 many cards (50)
		m.getPlayerById(0).setPoliticCardToDraw(50);
		
		GameController g = new GameController(m);
		
		assertTrue("card: " + m.getPlayerById(0).getPoliticcard().size(), m.getPlayerById(0).getPoliticcard().size() == 50 + 6);
		
		ArrayList<Color> card = new ArrayList<>();
		BusinessCard first = m.getMap().getRegionByType(RegionType.MOUNTAIN).getFirstcard();
		
		card.add(Color.decode("#FEFEFE"));
		card.add(Color.decode("#FEFEFE"));
		card.add(Color.decode("#FEFEFE"));
				
		GetBusinessCardMessage mess = new GetBusinessCardMessage(m.getMap().getRegionByType(RegionType.MOUNTAIN).getFirstcard(),RegionType.MOUNTAIN, card);
		mess.setId(0);
		mess.addPoliticCard(Color.decode("#FEFEFE"));
		assertTrue(mess.getCard().getId() == first.getId());
		
		g.update(null, mess);
		
		assertTrue(m.getPlayerById(0).getFreebusinesscard().get(0).getId()==first.getId());
		
		assertTrue(m.getPlayerById(0).getMainActionCounter() == 0);
		
		m.getPlayerById(0).setMainActionCounter(1);
		GetBusinessCardMessage mess2 = new GetBusinessCardMessage(m.getMap().getRegionByType(RegionType.MOUNTAIN).getSecondcard(),RegionType.MOUNTAIN);
		mess2.setId(0);
		
		assertTrue(mess2.getCard().getId() == m.getMap().getRegionByType(RegionType.MOUNTAIN).getSecondcard().getId());
		
		g.update(null, mess2);
		
		assertTrue(m.getPlayerById(0).getMainActionCounter() == 0);
		assertTrue(m.getPlayerById(0).getFreebusinesscard().size() == 2);
	}

}
