package it.polimi.ingsw.PS19.controller;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import it.polimi.ingsw.ps19.controller.GameController;
import it.polimi.ingsw.ps19.message.requests.BuyHelperMessage;
import it.polimi.ingsw.ps19.message.requests.BuyMainActionMessage;
import it.polimi.ingsw.ps19.message.requests.ElectCouncillorMessage;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.server.WaitingRoom;
import it.polimi.ingsw.ps19.view.connection.Connection;
import it.polimi.ingsw.ps19.view.connection.RMIConnection;

public class BuyMainActionTest 
{

	@Test
	public void test() 
	{
		/**
		 * Test della Buy helper e Buy main action:
		 * Inizializzo il model con 4 player. con un for ripeto 4 volte la mossa di comprare un aiutante
		 * Controllando ad ogni iterazione che i parametri del player siano corretti.
		 * 
		 * finito il ciclo creo un messaggio di BuyMainAction e lo invio al controller, infine controllo nuovamente i parametri
		 * del player per avere la certezza che l'azione è andata a buon fine.
		 */
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
		int i = 0;
		for(i=1; i<4;i++)
		{
		
			ElectCouncillorMessage elect = new ElectCouncillorMessage(
							m.getMap().getAvailableCouncillor().getListofcolors().getRandomColor(),
							m.getMap().getKing());
			elect.setId(0);
			elect.setMainAction(true);
			
			g.update(m, elect);
			
			assertTrue(m.getPlayerById(0).getMainActionCounter() == 0);
			//assertTrue(m.getPlayerById(0).getMoney() == (10 + 4*i));
			
			BuyHelperMessage helper = new BuyHelperMessage();
			helper.setId(0);
	
			g.update(null, helper);
			
			assertTrue("main: " + m.getPlayerById(0).getMainActionCounter(), 
					m.getPlayerById(0).getMainActionCounter() == 0);
			assertTrue("fast: " + m.getPlayerById(0).getFastActionCounter(), 
					m.getPlayerById(0).getFastActionCounter() == 0);
			assertTrue("money: " + m.getPlayerById(0).getMoney(), 
					m.getPlayerById(0).getMoney() == 10 + i);
			assertTrue("helper: "+ m.getPlayerById(0).getHelpers(), 
					m.getPlayerById(0).getHelpers() == 1 + i);
			m.getCurrentState().setPlayerTurnId(0);
			m.getPlayerById(0).setMainActionCounter(1);
			m.getPlayerById(0).setFastActionCounter(1);

		}
		BuyMainActionMessage mess = new BuyMainActionMessage();
		
		mess.setId(0);
		
		g.update(null, mess);
		
		assertTrue("main" + m.getPlayerById(0).getMainActionCounter(), m.getPlayerById(0).getMainActionCounter() == 2);
		assertTrue(m.getPlayerById(0).getFastActionCounter() == 0);
	}

}
