package it.polimi.ingsw.PS19.modeltest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.ps19.model.Model;

public class CompleteModelTest {

	@Test
	public void test() {
		
		List<Integer> players = new ArrayList<>();
		players.add(0);
		players.add(1);
		players.add(2);
		players.add(3);
		Model model = new Model(players);
		
		
		//Politic deck != 0
		assertTrue(model.getMap().getPoliticdeck().getSize() != 0);
		
		//initialize the game on costructor
		//draw politic card for all players
		
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
