package it.polimi.ingsw.PS19.model.map;

import java.util.ArrayList;

public class Region {
	
	int position; //0, 1, 2 
	ArrayList<City> cities; 
	
	
	private Region(ArrayList<City> c){
		cities = new ArrayList<City>();
		cities.addAll(c);
	}
	
	private void setPosition(int pos){
		this.position=pos;
	}
	
	
	public static ArrayList<Region> finalRegionBuilder(ArrayList<ArrayList<City>> citiesbyregion){
		
		ArrayList<Region> returnmap = new ArrayList<Region>();
		
		for(ArrayList<City> el : citiesbyregion){
			returnmap.add(new Region(el));
			returnmap.get(returnmap.size()-1).setPosition(returnmap.size()-1);
		}
		
		return returnmap;
	}
	
}
