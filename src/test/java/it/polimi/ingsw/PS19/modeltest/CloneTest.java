package it.polimi.ingsw.PS19.modeltest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.ps19.model.parameter.Costants;

public class CloneTest {
	
	/**
	 * This test is to make sure that the static function Costants.clone(List<E> list) actually returns
	 * a list, which is different from the parameter but has the same elements
	 * 
	 * This method is vastly used around the program to get "copies" of list to work with.
	 */

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
		
		int i=0;
		for (Integer integer : second) 
		{
			assertTrue(integer == test.get(i));
			i++;
		}
	}

}
