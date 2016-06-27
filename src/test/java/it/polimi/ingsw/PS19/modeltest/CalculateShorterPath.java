package it.polimi.ingsw.PS19.modeltest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.map.City;
import it.polimi.ingsw.ps19.model.parameter.Costants;

public class CalculateShorterPath {

	@Test
	public void test()
	{
		/**
		 * Test per il calcolo dei cammini minimi.
		 * Partendo dalla prima città eseguo l'algoritmo su tutte le altre.
		 * Una volta calcolato il numero di salti lo confronto con il vettore testForCurrentMap
		 * che contiene i valori corretti dei "salti".
		 * 
		 * Creando un nuovo vettore per i "salti" posso eseguire il test su qualsiasi città di partenza
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
