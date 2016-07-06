package it.polimi.ingsw.PS19.modeltest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.ps19.model.Model;

public class GetMaxIdTest {

	/**
	 * This test makes sure that the getMaxId method 
	 * returns the biggest between the idsof the connected players
	 */
	@Test
	public void test() 
	{
		List<Integer> players = new ArrayList<>();
		players.add(4);
		players.add(13);
		players.add(30);
		players.add(22);
		Model model = new Model(players);
		
		
		
		//Politic deck != 0
		assertTrue(model.getMap().getPoliticdeck().getSize() != 0);
		assertTrue(model.getMaxId() == 30);
		
	}

}
