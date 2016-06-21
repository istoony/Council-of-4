package it.polimi.ingsw.ps19.model.parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Costants 
{
	public static final String JOKERCOLOR = "#FEFEFE";
	public static final int JUMPCOST = 2;
	public static final int NO_ACTIVE_PLAYER = -1;
	public static final int BROADCAST_MESSAGE = -1;
	public static final Random RANDOM_NUMBER = new Random();
	public static final int N_OF_ACTION_TO_ADD = 1;

	private Costants()
	{
		//così sonar è contento
	}
	
	/**
	 * Returns a clone of the parameter
	 * @param <E> a generics type of array that I want to clone
	 * @param politiccard
	 * @return
	 */
	public static <E> List<E> clone(List<E> array) 
	{
		List<E> clone = new ArrayList<>();
		if(!array.isEmpty())
			for (E p : array)
				clone.add(p);
		return clone;
	}
}
