package it.polimi.ingsw.PS19.controller;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import it.polimi.ingsw.ps19.controller.GameController;
import it.polimi.ingsw.ps19.controller.support.ActionMessages;
import it.polimi.ingsw.ps19.message.replies.ElectCouncillorReply;
import it.polimi.ingsw.ps19.message.replies.SendFullPlayerReply;
import it.polimi.ingsw.ps19.message.requests.BuyHelperMessage;
import it.polimi.ingsw.ps19.message.requests.ElectCouncillorMessage;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.server.WaitingRoom;
import it.polimi.ingsw.ps19.view.connection.Connection;
import it.polimi.ingsw.ps19.view.connection.RMIConnection;

public class ReconnectedPlayerTest {

	@Test
	public void ReconnectPlayer() 
	{
		/**
		 * Creo 5 player nella waitingroom, ne disconnetto subito 3
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
	
	/**
	 * inizializzo il model con i 5 player aggiungendo i player disconnessi
	 */

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
	
		//Riconnetto il player 12 e faccio eseguire un azione al player 10
	tre.reconnect();
	
	BuyHelperMessage helper = new BuyHelperMessage();
	helper.setId(10);
	
	g.update(null, helper);
	
	assertTrue(g.getReply() instanceof SendFullPlayerReply);
	assertTrue(m.getPlayerById(10).getHelpers() == 2);
	
		//controllo se il server si è accorto che il player si è riconnesso
	assertTrue("disconnected" + m.getCurrentState().getNumberOfDisconnectedPlayer(), m.getCurrentState().getNumberOfDisconnectedPlayer() == 2);
	
		//provo a far eseguire una seconda azione secondaria, il result dell'azione
		//sarà "GENERIC ERROR"
	ElectCouncillorMessage elect = new ElectCouncillorMessage(
			m.getMap().getAvailableCouncillor().getListofcolors().getRandomColor(), 
			m.getMap().getKing());
	elect.setMainAction(false);
	elect.setId(10);

	g.update(null, elect);
	
	assertTrue("result: "+g.getReply().getResult(), g.getReply() instanceof ElectCouncillorReply);
	assertTrue(g.getReply().getResult() == ActionMessages.GENERIC_ERROR);
	
	
	}

}
