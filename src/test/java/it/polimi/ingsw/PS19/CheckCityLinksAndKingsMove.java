package it.polimi.ingsw.PS19;


import java.util.ArrayList;

import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.map.City;

public class CheckCityLinksAndKingsMove {
	
	public static void main(String[] args) {
		
		Model m = new Model(2);
		

				City city1 = m.getMap().getListaRegioni().get(0).getCities().get(0);
				City city2 = m.getMap().getListaRegioni().get(2).getCities().get(4);
				ArrayList<City> path = m.getMap().calculateShorterPath(city1, city2);
				for(City c : path){
					System.out.println(c.getName());
				}

	}
	
}
