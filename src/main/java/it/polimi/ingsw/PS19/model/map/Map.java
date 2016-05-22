package it.polimi.ingsw.PS19.model.map;

import java.awt.Color;
import java.util.ArrayList;

import it.polimi.ingsw.PS19.model.card.DeckFactory;
import it.polimi.ingsw.PS19.model.card.PoliticDeck;
import it.polimi.ingsw.PS19.model.parameter.ColorManager;
import it.polimi.ingsw.PS19.model.map.Region;

public class Map 
{	
	ArrayList<Region> listaRegioni;
	private PoliticDeck politicdeck;
	private ColorManager councilcolors;
	private King king;

	
	private Map(ArrayList<Region> regionlist)
	{
		listaRegioni = new ArrayList<Region>();
		listaRegioni.addAll(regionlist);
		
		kingFactory();	
		///OVVIAMENTE DA CAMBIARE IL PATH DEL FILE SOTTOS
		councilcolors = BalconyFactory.createBalcony(listaRegioni, king, "mapfile/politicscard.xml");
		
		politicdeck = (PoliticDeck) DeckFactory.politicsDeckFactory("mapfile/politicscard.xml", councilcolors);
	}
	
	private void kingFactory()
	{
		ArrayList<City> cities = getAllCities();
		for (City city : cities)
		{
			//System.out.println(city.citycolor.toString());
			if(city.getCitycolor().equals(Color.decode("#8B008B")))
			{
				//System.out.println(city.toString());
				king = new King(city);		
			}
		}
	}
	
	private ArrayList<City> getAllCities()
	{
		ArrayList<City> cities = new ArrayList<City>();
		for (Region region : listaRegioni) 
		{
			cities.addAll(region.getCities());
		}
		return cities;
	}
	
	@Override
	public String toString() {
		String s = "";
		for (Region region : listaRegioni) 
			s += region.toString() + "\n";
		return s;
	}
	
	public static Map finalMapBuilder(ArrayList<ArrayList<City>> citiesbyregion){
		
		Map map = new Map(Region.finalRegionBuilder(citiesbyregion));
		return map;
		
	}
}
