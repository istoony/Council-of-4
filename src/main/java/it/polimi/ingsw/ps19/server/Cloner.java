package it.polimi.ingsw.ps19.server;

import java.util.ArrayList;
import java.util.List;

/**
 * Clones an array
 * @param <E>
 */
public class Cloner<E>
{
	public List<E> cloneList(List<E> list)
	{
		List<E> clone = new ArrayList<>();
		clone.addAll(list);
		return clone;
	}
}
