package it.polimi.ingsw.PS19.clienttest;

import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import it.polimi.ingsw.ps19.client.clientinput.TestSatisfyCouncillor;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.controller.GameController;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.card.PoliticsCard;
import it.polimi.ingsw.ps19.model.parameter.Costants;
import it.polimi.ingsw.ps19.model.parameter.RegionType;
import it.polimi.ingsw.ps19.server.WaitingRoom;
import it.polimi.ingsw.ps19.view.connection.Connection;
import it.polimi.ingsw.ps19.view.connection.RMIConnection;

public class TestCouncillorInput 
{
	/**
	 * Questi due test controllano la correttezza della logica del client chiamando i metodi di SatisfyCouncillorInput.
	 * Dato che i metodi sono protetti è necessario creare una nuova classe con metodi pubblici che richiamano i metodi protetti.
	 * 
	 * Il primo test controlla che le regioni da in cui il player possa pescare una carta siano tutte e 3. 
	 * Questo non è sempre verificato ma prima imposto che il player abbia 56 carte, per questo motivo il test è corretto.
	 * 
	 * Nel secondo testo il metodo che passati due vettori, uno di colori e uno di carte politica ritorna il vettore delle carte politica
	 * che possono essere utilizzate per corrompere il balcone.
	 * 
	 * Altri metodi di SatisfyCouncillorInput non possono essere utilizzabili perchè richiedono input da utente che i test non possono fornire.
	 */
	
	private TestSatisfyCouncillor init()
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
		m.getPlayerById(0).setPoliticCardToDraw(20);
		new GameController(m);
		
		ClientModel clientModel = new ClientModel(m);

		assertTrue(clientModel.getActiveplayer() >=0 );
		assertTrue(clientModel.getAllCities() != null);
		assertTrue(clientModel.getAvailablecouncillor() != null);
		assertTrue(clientModel.getKing() != null);
		assertTrue(clientModel.getRegions() != null);
		assertTrue(clientModel.getPlayer() != null);
		
		 return new TestSatisfyCouncillor(clientModel);
	}

	@Test
	public void testGetAvailableRegions() 
	{
		TestSatisfyCouncillor s = init();
		for (RegionType type : RegionType.getValues())
			assertTrue("type: " + type, s.getAvailableRegions().contains(type));
	}
	
	@Test
	public void getAvailablePoliticsTest()
	{
		TestSatisfyCouncillor s = init();
		List<Color> balcony = new ArrayList<>();
		balcony.add(Color.RED);
		balcony.add(Color.BLUE);
		balcony.add(Color.GRAY);
		balcony.add(Color.BLACK);
		
		List<PoliticsCard> cards = new ArrayList<>();
		cards.add(new PoliticsCard(Color.RED));
		cards.add(new PoliticsCard(Color.BLUE));
		cards.add(new PoliticsCard(Color.BLACK));
		cards.add(new PoliticsCard(Color.GREEN));
		
		
		for (PoliticsCard politicsCard : s.getAvailablePolitics(balcony, Costants.clone(cards)))
			assertTrue(cards.contains(politicsCard));
	}

}
