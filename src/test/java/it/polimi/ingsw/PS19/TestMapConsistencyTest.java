package it.polimi.ingsw.PS19;

import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.map.City;

public class TestMapConsistencyTest {

	@Test
	public void test() 
	{
		Model m = new Model(2);
		
		//starting city
		City city1 = m.getMap().getListaRegioni().get(0).getCities().get(0);
		City city2;
		List<City> path;
		
		//check the shortest path to all the cities in the map
		for(int i=0; i<m.getMap().getListaRegioni().size(); i++)
		{
			for(int j=0; j<m.getMap().getListaRegioni().get(i).getCities().size(); j++)
			{
				city2 = m.getMap().getListaRegioni().get(i).getCities().get(j);
				path = m.getMap().calculateShorterPath(city1, city2);
				//System.out.println("stampa percorso");
				for(int k=path.size()-1; k>=0; k--)
				{
					//System.out.println(path.get(k).getName());
				}
				//System.out.println("lenght percorso"+path.size()+"\n");
			}
		}
	}

}
