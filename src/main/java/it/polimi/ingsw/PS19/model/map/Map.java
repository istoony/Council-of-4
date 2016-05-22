package it.polimi.ingsw.PS19.model.map;

import java.util.ArrayList;

public class Map 
{	
	ArrayList<Region> listaRegioni;

	
	private Map(ArrayList<Region> regionlist){
		listaRegioni = new ArrayList<Region>();
		listaRegioni.addAll(regionlist);
	}
	
	
	public static Map finalMapBuilder(ArrayList<ArrayList<City>> citiesbyregion){
		
		Map map = new Map(Region.finalRegionBuilder(citiesbyregion));
		return map;
		
	}
}
