package it.polimi.ingsw.PS19.controller;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.PS19.controller.GameController;
import it.polimi.ingsw.PS19.controller.action.ActionMessages;
import it.polimi.ingsw.PS19.message.requests.ChangeKingPositionMessage;
import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.map.City;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class ChangeKingPosition {

	@Test
	public void test() 
	{
		Model m = new Model(2);
		GameController g = new GameController(m);
		
		assertTrue(m.getPlayerById(0).getHelpers() == 1);
		
		ArrayList<Color> card = new ArrayList<>();
		
		card.add(Color.decode("#FEFEFE"));
		card.add(Color.decode("#FEFEFE"));
		card.add(Color.decode("#FEFEFE"));
		card.add(Color.decode("#FEFEFE"));
		
		City c = m.getMap().getRegionByType(RegionType.MOUNTAIN).getCityById(13);
		assertTrue(c != null);
		c.buildEmporium(m.getPlayerById(1));
		
		ChangeKingPositionMessage mess = new ChangeKingPositionMessage(c, card);
		
		g.update(null, mess);
		assertTrue(g.getReply().getResult().equals(ActionMessages.EVERYTHING_IS_OK));
		
		assertTrue(m.getMap().getKing().getCurrentcity().getId() == 13);
		assertTrue(m.getPlayerById(0).getMainActionCounter() == 0);
		assertTrue(m.getPlayerById(0).getHelpers() == 0);
		assertTrue("Money" + m.getPlayerById(0).getMoney(),m.getPlayerById(0).getMoney() == 10 - 4 - 4);
		
	}

}