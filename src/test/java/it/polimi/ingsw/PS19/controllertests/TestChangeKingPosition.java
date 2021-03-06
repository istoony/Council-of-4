package it.polimi.ingsw.PS19.controllertests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.ps19.controller.GameController;
import it.polimi.ingsw.ps19.controller.support.ActionMessages;
import it.polimi.ingsw.ps19.message.requests.ChangeKingPositionMessage;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.card.PoliticsCard;
import it.polimi.ingsw.ps19.model.map.City;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

public class TestChangeKingPosition {

	@Test
	public void test() 
	{
		/**
		 * In questo test muovo il re su una città e costruisco un emporio.
		 */
		List<Integer> players = new ArrayList<>();
		players.add(0);
		players.add(1);
		Model m = new Model(players);
		GameController g = new GameController(m);
		
		m.getPlayerById(0).setMoney(10);
		
		assertTrue(m.getPlayerById(0).getHelpers() == 1);
		
		ArrayList<Color> card = new ArrayList<>();
		
		card.add(Color.decode("#FEFEFE"));
		card.add(Color.decode("#FEFEFE"));
		card.add(Color.decode("#FEFEFE"));
		card.add(Color.decode("#FEFEFE"));
		
		m.getPlayerById(0).addCardToHand(new PoliticsCard(Color.decode("#FEFEFE")));
		m.getPlayerById(0).addCardToHand(new PoliticsCard(Color.decode("#FEFEFE")));
		m.getPlayerById(0).addCardToHand(new PoliticsCard(Color.decode("#FEFEFE")));
		m.getPlayerById(0).addCardToHand(new PoliticsCard(Color.decode("#FEFEFE")));
		m.getPlayerById(0).addCardToHand(new PoliticsCard(Color.decode("#FEFEFE")));
		
		City c = m.getMap().getRegionByType(RegionType.MOUNTAIN).getCityById(13);
		assertTrue(c != null);
		c.buildEmporium(m.getPlayerById(1));
		
		ChangeKingPositionMessage mess = new ChangeKingPositionMessage(c, card);
		
		g.update(null, mess);
		assertTrue("result " + g.getReply().getResult(), g.getReply().getResult().equals(ActionMessages.EVERYTHING_IS_OK));
		
		assertTrue(m.getMap().getKing().getCurrentcity().getId() == 13);
		assertTrue(m.getPlayerById(0).getMainActionCounter() == 0);
		
		//MONEY E HELPER DIPENDONO DAL BONUS DELLE CITTA'
		//assertTrue("h: "+ m.getPlayerById(0).getHelpers(), m.getPlayerById(0).getHelpers() == 0);
		//assertTrue("Money" + m.getPlayerById(0).getMoney(),m.getPlayerById(0).getMoney() == 10 - 4 - 4);
		assertTrue(m.getPlayerById(0).getMaxemporia() == 9);
	}

}
