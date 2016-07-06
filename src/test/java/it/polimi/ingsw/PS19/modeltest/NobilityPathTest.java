package it.polimi.ingsw.PS19.modeltest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import it.polimi.ingsw.ps19.controller.support.SupportMethod;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.server.WaitingRoom;
import it.polimi.ingsw.ps19.view.connection.Connection;
import it.polimi.ingsw.ps19.view.connection.RMIConnection;

//support method is used for protected method on controller
public class NobilityPathTest extends SupportMethod
{

	/**
	 * In questo test controllo se i bonus del percorso della nobiltà siano effettivamente applicati al player.
	 * do al player 2 punti nobiltà e chiamo la funzione checkNobilityPathBonus.
	 * Successivamente aggiungo al player altri due punti e richiamo la funzione.
	 * 
	 * Dopo ogni chiamata controllo che i parametri del player siano corretti.
	 */
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
		//Nobility path:
		// 1- none
		// 2- victory points-> 2 && more money -> 2
		// 3- none
		// 4- city bonus
		m.getPlayerById(0).setNobilityPoints(2);
		
		checkNobilityPathBonus(m, m.getPlayerById(0));	
		assertTrue(m.getPlayerById(0).getMoney() == 10 + 2);
		assertTrue(m.getPlayerById(0).getVictoryPoints() == 2);
		
		m.getPlayerById(0).setNobilityPoints(4);
		checkNobilityPathBonus(m, m.getPlayerById(0));		
		assertTrue(m.getPlayerById(0).isCityBonusRequest());
		
		
		
	}
	
	/**
	 * In questo test provo se i victory points finali siano inviati in maniera corretta ai player.
	 * chiamo le due funzioni calculateLastPoints, sortByVictoryPoints.
	 * la prima calcola quanti victory points aggiungere ai player e la seconda ordina i player in base a quest'ultimi.
	 */
	
	
	@Test
	public void testEndGame() 
	{

		Connection uno = new RMIConnection(true);
			uno.setActive();
		Connection due = new RMIConnection(true);
			due.setActive();
	
		Connection tre = new RMIConnection(true);
			tre.setActive();
		
		Map<Integer, Connection> wRoom = new HashMap<>();
		wRoom.put(0, uno);
		wRoom.put(1, due);
		wRoom.put(3, tre);
		
		WaitingRoom.setConnection(wRoom);
		
		
		List<Integer> players = new ArrayList<>();
		players.add(0);
		players.add(1);
		players.add(2);
		Model m = new Model(players);
		//Nobility path:
		// 1- none
		// 2- victory points-> 2 && more money -> 2
		// 3- none
		// 4- city bonus
		m.getPlayerById(0).setNobilityPoints(2);
		
		checkNobilityPathBonus(m, m.getPlayerById(0));	
		assertTrue(m.getPlayerById(0).getMoney() == 10 + 2);
		assertTrue(m.getPlayerById(0).getVictoryPoints() == 2);
		
		m.getPlayerById(0).setNobilityPoints(4);
		checkNobilityPathBonus(m, m.getPlayerById(0));		
		assertTrue(m.getPlayerById(0).isCityBonusRequest());
		
		m.getPlayerById(1).setNobilityPoints(4);
		checkNobilityPathBonus(m, m.getPlayerById(1));	
		assertTrue(m.getPlayerById(0).isCityBonusRequest());
		
		calculateLastPoints(m);

		assertTrue("pl 0:" + m.getPlayerById(0).getVictoryPoints(), m.getPlayerById(0).getVictoryPoints() == 2 + 5);
		assertTrue("pl 1:" + m.getPlayerById(1).getVictoryPoints(), m.getPlayerById(1).getVictoryPoints() == 5);
		assertTrue("pl 2:" + m.getPlayerById(2).getVictoryPoints(), m.getPlayerById(2).getVictoryPoints() == 0);
		
		List<Player> p = sortByVictoryPoints(m.getPlayer());
		
		assertTrue("pl 0:" + p.get(0).getId(), p.get(0).getId() == 0);
		assertTrue("pl 1:" + p.get(1).getId(), p.get(1).getId() == 1);
		assertTrue("pl 2:" + p.get(2).getId(), p.get(2).getId() == 2); 
		
	}

	/**
	 * Controllo che la lunghezza del percorso della nobiltà sia 20
	 */
	@Test
	public void getMaxTest()
	{
		Connection uno = new RMIConnection(true);
			uno.setActive();
		Connection due = new RMIConnection(true);
			due.setActive();

		Connection tre = new RMIConnection(true);
			tre.setActive();
	
		Map<Integer, Connection> wRoom = new HashMap<>();
			wRoom.put(0, uno);
			wRoom.put(1, due);
			wRoom.put(3, tre);
	
		WaitingRoom.setConnection(wRoom);
	
	
		List<Integer> players = new ArrayList<>();
		players.add(0);
		players.add(1);
		players.add(2);
		Model m = new Model(players);

		assertTrue("SIZE:" + m.getMap().getNobilityPath().getMaxKey(), m.getMap().getNobilityPath().getMaxKey() == 20);
	}
}
