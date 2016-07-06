package it.polimi.ingsw.ps19.model.parameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import it.polimi.ingsw.ps19.model.map.City;
import it.polimi.ingsw.ps19.model.map.Region;

/**
 * Class with constants related to the model
 */
public class Costants 
{
	public static final String JOKERCOLOR = "#FEFEFE";
	public static final int JUMPCOST = 2;
	public static final int NO_ACTIVE_PLAYER = -1;
	public static final int INVALID_ID = NO_ACTIVE_PLAYER;
	public static final int BROADCAST_MESSAGE = -1;
	public static final Random RANDOM_NUMBER = new Random();
	public static final int EMPORIUM_SIZE = 10;

	private Costants()
	{
		//così sonar è contento
	}
	
	/**
	 * Returns a clone of the parameter
	 * @param array: the generic type of array to clone
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
	
	/**
	 * Returns whether the path contains the city
	 * @param path
	 * @param city
	 * @return
	 */
	public static boolean containsCityId(List<City> path, City city)
	{
		for (City c : path) 
			if(c.getId() == city.getId())
				return true;
		return false;
	}
	
	/**
	 * Gets list of cities for the shortest path
	 * @param start
	 * @param end
	 * @param regions
	 * @return
	 */
	public static List<City> calculateShorterPath(City start, City end, List<Region> regions)
	{
		List<City> result = new ArrayList<>();
		for(Region r : regions){
			for(City c : r.getCities()){
				if(start.getId() == c.getId())
				{
					Map<City, City> visited = new HashMap<>(); 
					Map<City, City> parentree = new HashMap<>(); 
					ArrayList<City> frontier = new ArrayList<>();
					result = recursiveBFS(start, end, result, c, visited, frontier, parentree);
				}
			}
		}
		return result;
	}
	
	private static List<City> recursiveBFS(City root, City end, List<City> path, City start, Map<City, City> visited, List<City> frontier, Map<City, City> parenttree)
	{
		City temp = root;
		if(temp.getId() == end.getId())
		{
			path.add(end);
			while(!Costants.containsCityId(path, start))
			{
				temp = parenttree.get(temp);
				path.add(temp);
			}
			return path;
		}
		
		for(City c : temp.getNeighbours()){
			if(!parenttree.containsKey(c)){
				parenttree.put(c, temp);
			}
		}
		frontier.addAll(temp.getNeighbours());
		City newRoot = frontier.get(0);
		frontier.remove(0);
		while(visited.containsValue(newRoot)){
			newRoot = frontier.get(0);
			frontier.remove(0);
		}
		visited.put(newRoot, temp);
		return recursiveBFS(newRoot, end, path, start, visited, frontier, parenttree);
	}
}
