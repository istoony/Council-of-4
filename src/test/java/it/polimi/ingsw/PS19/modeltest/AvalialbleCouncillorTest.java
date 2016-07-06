package it.polimi.ingsw.PS19.modeltest;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.ps19.model.Model;

public class AvalialbleCouncillorTest {
	
	/**
	 * This test checks if the class AvailableCouncillor works properly.
	 * The class contains the available councillors (aka councillors not in the balcony) which are 
	 * rappresented as a map of colors and number (of councillors available of that color).
	 * 
	 * The first test checks that it is possible to execute 'decrement' on all available councillors
	 * 
	 * The second test checks that the colors match the colors of the councillors in the balconies.
	 * 
	 * Il secondo test controlla che il totale dei colori presenti in Available Councillor sia coerente con quello del model
	 * 
	 * The third test makes sure that the colors match the colors read from the configuration file.
	 * In particular:
	 * 		FF0000
	 * 		FFFFFF
	 * 		FE1200
	 * 		FF4567
	 */
	

	@Test
	public void testGetAvailableColors() 
	{
		List<Integer> players = new ArrayList<>();
		players.add(0);
		players.add(1);
		players.add(2);
		players.add(3);
		Model model = new Model(players);
		
		List<Color> color = model.getMap().getAvailableCouncillor().getAvailableColors();
		for (Color c : color) 
		{
				assertTrue(model.getMap().getAvailableCouncillor().getListofcolors().getColors().contains(c));
				assertTrue(model.getMap().getAvailableCouncillor().decrement(c));
		}
	}

	@Test
	public void testGetListofcolors() 
	{
		List<Integer> players = new ArrayList<>();
		players.add(0);
		players.add(1);
		players.add(2);
		players.add(3);
		Model model = new Model(players);
		
		assertTrue(model.getMap().getAvailableCouncillor().getListofcolors() == model.getMap().getCouncilcolors());
	}

	@Test
	public void testFindColor() 
	{
		List<Integer> players = new ArrayList<>();
		players.add(0);
		players.add(1);
		players.add(2);
		players.add(3);
		Model model = new Model(players);
		
		assertTrue(model.getMap().getAvailableCouncillor().findColor(Color.decode("#FF0000")));
		assertTrue(model.getMap().getAvailableCouncillor().findColor(Color.decode("#FFFFFF")));
		assertTrue(!model.getMap().getAvailableCouncillor().findColor(Color.decode("#FE1200")));
		assertTrue(!model.getMap().getAvailableCouncillor().findColor(Color.decode("#FF4567")));
	}

}
