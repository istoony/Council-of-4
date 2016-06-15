package it.polimi.ingsw.PS19.modeltest;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.PS19.controller.GameController;
import it.polimi.ingsw.PS19.model.Model;

public class CompleteModelTest {

	@Test
	public void test() {
		Model model = new Model(4);
		
		
		//Politic deck != 0
		assertTrue(model.getMap().getPoliticdeck().getSize() != 0);
		
		//initialize the game on costructor
		GameController g = new GameController(model);
		
		//player 0 have > 0 cards
			assertTrue(model.getPlayerById(0).getPoliticcard().size() == 7);
		//player 0 have > 0 cards
			assertTrue(model.getPlayerById(1).getPoliticcard().size() == 6);
		//player 0 have > 0 cards
			assertTrue(model.getPlayerById(2).getPoliticcard().size() == 6);
		//player 0 have > 0 cards
			assertTrue(model.getPlayerById(3).getPoliticcard().size() == 6);
	}

}
