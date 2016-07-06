package it.polimi.ingsw.PS19.clienttest;

import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.controller.GameController;
import it.polimi.ingsw.ps19.message.replies.ElectCouncillorReply;
import it.polimi.ingsw.ps19.message.replies.SendFullGameReply;
import it.polimi.ingsw.ps19.message.requests.ElectCouncillorMessage;
import it.polimi.ingsw.ps19.message.requests.SendFullGameMessage;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.parameter.RegionType;
import it.polimi.ingsw.ps19.server.WaitingRoom;
import it.polimi.ingsw.ps19.view.connection.Connection;
import it.polimi.ingsw.ps19.view.connection.RMIConnection;

public class TestCompleteModelUpdate {

	@Test
	public void test() 
	{
		/**
		 * This test checks that, following a request, the "server" returns the 'FullGameMessage' with the proper
		 * values.
		 * Subsequentally the "client" creates an 'ElectCouncillorRequest' which is forwarded to the client.
		 * Finally the test checks that the ElectCouncillorReply, which is the response of the server to the client action
		 * has proper values.
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
		ClientModel clientModel = new ClientModel(m);
	
		SendFullGameMessage sendFullGame = new SendFullGameMessage(0);
		
		GameController g = new GameController(m);
		
		g.update(null, sendFullGame);
		
		assertTrue(g.getReply() instanceof SendFullGameReply);
		
		assertTrue(clientModel.getActiveplayer() >=0 );
		assertTrue(clientModel.getAllCities() != null);
		assertTrue(clientModel.getAvailablecouncillor() != null);
		assertTrue(clientModel.getKing() != null);
		assertTrue(clientModel.getRegions() != null);
		assertTrue(clientModel.getPlayer() != null);
		
		ElectCouncillorMessage electCouncillor = new ElectCouncillorMessage(Color.decode("#FF0000"), 
				RegionType.MOUNTAIN);
		electCouncillor.setId(0);
		electCouncillor.setMainAction(true);
		
		g.update(null, electCouncillor);
		
		assertTrue(g.getReply() instanceof ElectCouncillorReply);
		
	}

}
