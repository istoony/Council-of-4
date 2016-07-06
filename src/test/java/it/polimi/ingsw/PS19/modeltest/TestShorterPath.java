package it.polimi.ingsw.PS19.modeltest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.map.City;
import it.polimi.ingsw.ps19.model.parameter.Costants;

public class TestShorterPath {

	@Test
	public void test() 
	{
		/**
		 * Test for djisktra algorithm implementation.
		 * Starting from the first city the algorithm is executed on every city.
		 * At the end we confront the resulting array of "minimum paths" with the correct one testForCurrentMap
		 * 
		 * With different correct arrays we can test the algorithm starting from any city.
		 */

		List<Integer> testForCurrentMap = new ArrayList<>();
		testForCurrentMap.add(1);
		testForCurrentMap.add(2);
		testForCurrentMap.add(2);
		testForCurrentMap.add(3);
		testForCurrentMap.add(3);
		testForCurrentMap.add(3);
		testForCurrentMap.add(4);
		testForCurrentMap.add(5);
		testForCurrentMap.add(4);
		testForCurrentMap.add(4);
		testForCurrentMap.add(5);
		testForCurrentMap.add(6);
		testForCurrentMap.add(6);
		testForCurrentMap.add(6);
		testForCurrentMap.add(5);
		
		List<Integer> players = new ArrayList<>();
		players.add(0);
		players.add(1);
		Model m = new Model(players);
		City start = m.getMap().getAllCities().get(0);	
		int i = 0;
		for (City end : m.getMap().getAllCities()) 
		{
			assertTrue(Costants.calculateShorterPath(start, end, m.getMap().getRegionList()).size() == testForCurrentMap.get(i));
			i++;
		}
	}

}
