package it.polimi.ingsw.PS19.modeltest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.ps19.model.parameter.Costants;

public class CloneTest {

	@Test
	public void test() 
	{
		List<Integer> test = new ArrayList<>();
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		test.add(5);
		test.add(6);
		List<Integer> second = Costants.clone(test);
		
		assertTrue(second != test);
	}

}
