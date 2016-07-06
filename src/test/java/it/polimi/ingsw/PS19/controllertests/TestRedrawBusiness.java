package it.polimi.ingsw.PS19.controllertests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import it.polimi.ingsw.ps19.controller.GameController;
import it.polimi.ingsw.ps19.message.requests.RedrawBusinessCardMessage;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.parameter.RegionType;
import it.polimi.ingsw.ps19.server.WaitingRoom;
import it.polimi.ingsw.ps19.view.connection.Connection;
import it.polimi.ingsw.ps19.view.connection.RMIConnection;

public class TestRedrawBusiness {

	@Test
	public void test() 
	{
		/**
		 * Inizializzo il gioco, salvo la prima e la seconda carta business della regione PLAIN in due variabili
		 * successivamente eseguo la mossa di RedrawBusinessCard ed infine controllo che le due nuove carte siano diverse
		 * da quelle iniziali.
		 */
		
		for(int i =0; i< 100; i++)
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
			
			BusinessCard first = m.getMap().getRegionByType(RegionType.PLAIN).getFirstcard();
			BusinessCard second = m.getMap().getRegionByType(RegionType.PLAIN).getSecondcard();
			
			GameController g = new GameController(m);
			RedrawBusinessCardMessage message = new RedrawBusinessCardMessage(RegionType.PLAIN);
		
			message.setId(0);
		
			g.update(null, message);
		
			BusinessCard firstNew = m.getMap().getRegionByType(RegionType.PLAIN).getFirstcard();
			BusinessCard secondNew = m.getMap().getRegionByType(RegionType.PLAIN).getSecondcard();

			assertTrue("prima " + first.getId() + " - seconda " + firstNew.getId(), first.getId() != firstNew.getId());
			assertTrue("prima " + second.getId() + " - seconda " + secondNew.getId(), second.getId() != secondNew.getId());
		}
	}

}
