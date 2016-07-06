package it.polimi.ingsw.PS19.modeltest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.server.WaitingRoom;
import it.polimi.ingsw.ps19.view.connection.Connection;
import it.polimi.ingsw.ps19.view.connection.RMIConnection;

public class playerConnectionTest {

	@Test
	public void test() 
	{
		
		/**
		 * Test for the algorithm that changes turn
		 * A game is started with 6 player
		 * Players 5, 3 and 1 are disconnected.
		 * Calling repetetly the 'giveNextCorrectId' and the 'setPlayerTurnId' methods, we check that
		 * the id "flow" is correct.
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
		Connection cinque = new RMIConnection(true);
			cinque.setActive();
		
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
		Model model = new Model(players);
	
		for(int i = 10; i<15; i++)
			assertTrue(model.getPlayerById(i) != null);
		
		model.getCurrentState().addDisconnectedPlayer(12);
		
		assertTrue("id = " + model.getCurrentState().getPlayerTurnId(), model.getCurrentState().getPlayerTurnId() == 10);
		model.getCurrentState().setPlayerTurnId(model.getCurrentState().giveNextCorrectId(model.getCurrentState().getPlayerTurnId()));
		
		assertTrue("id = " + model.getCurrentState().getPlayerTurnId(),model.getCurrentState().getPlayerTurnId() == 11);
		model.getCurrentState().setPlayerTurnId(model.getCurrentState().giveNextCorrectId(model.getCurrentState().getPlayerTurnId()));
		
		assertTrue("id = " + model.getCurrentState().getPlayerTurnId(),model.getCurrentState().getPlayerTurnId() == 13);

		model.getCurrentState().setPlayerTurnId(model.getCurrentState().giveNextCorrectId(model.getCurrentState().getPlayerTurnId()));
		
		assertTrue("id = " + model.getCurrentState().getPlayerTurnId(),model.getCurrentState().getPlayerTurnId() == 14);

		model.getCurrentState().setPlayerTurnId(model.getCurrentState().giveNextCorrectId(model.getCurrentState().getPlayerTurnId()));
		
		assertTrue("id = " + model.getCurrentState().getPlayerTurnId(),model.getCurrentState().getPlayerTurnId() == 10);
		
	}

}
