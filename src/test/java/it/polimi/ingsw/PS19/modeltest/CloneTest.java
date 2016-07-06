package it.polimi.ingsw.PS19.modeltest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.ps19.model.parameter.Costants;

public class CloneTest {
	
	/**
	 * in questo semlicissimo test dimostro che la funzione statica Costants.clone(List<E> list)
	 * ritorna un vettore diverso ma con gli stessi elementi del primo.
	 * Questa funzione è molto usata per il return dei vettori in modo da non ritornare il puntatore al vettore originale
	 * ma ritornare il puntatore al vettore copiato.
	 * 
	 * Utilizzando questa tecnica impediamo modifiche indesiderate degli attributi di una classe da parte di un altra classe
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
