package it.polimi.ingsw.PS19.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import it.polimi.ingsw.ps19.controller.GameController;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.server.WaitingRoom;
import it.polimi.ingsw.ps19.view.connection.Connection;
import it.polimi.ingsw.ps19.view.connection.RMIConnection;

public class BusinessCardBonusTest {

	@Test
	public void testBusinessCardOrCityBonusBusinessCardInt() 
	{

		Map<Integer, Connection> wRoom = new HashMap<>();
		
		Connection uno = new RMIConnection(true);
			uno.setActive();
		Connection due = new RMIConnection(true);
			due.setActive();
		Connection tre = new RMIConnection(true);
			tre.setActive();
		Connection quattro = new RMIConnection(true);
			quattro.setActive();
		
		wRoom.put(0, uno);
		wRoom.put(1, due);
		wRoom.put(0, tre);
		wRoom.put(1, quattro);
	
		WaitingRoom.setConnection(wRoom);
		
		List<Integer> players = new ArrayList<>();
		players.add(0);
		players.add(1);
		players.add(2);
		players.add(3);
		Model m = new Model(players);
		GameController g = new GameController(m);
		
		m.getPlayerById(0).setBusinessCardRequest(true);
		
		
	}

	@Test
	public void testBusinessCardOrCityBonusCityInt() 
	{
		fail("Not yet implemented");
	}

}
