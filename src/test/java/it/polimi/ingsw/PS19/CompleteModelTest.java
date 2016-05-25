package it.polimi.ingsw.PS19;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.card.PoliticDeck;

public class CompleteModelTest {

	@Test
	public void testModel() 
	{
		Model m = new Model(2);
		assertTrue(m != null);
	}

}
