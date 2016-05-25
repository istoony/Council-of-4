package it.polimi.ingsw.PS19.model.map;

import java.awt.Color;
import java.util.ArrayList;

import it.polimi.ingsw.PS19.model.FileNames;
import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.bonus.CityColorBonus;
import it.polimi.ingsw.PS19.model.bonus.GeneralBonus;
import it.polimi.ingsw.PS19.model.card.DeckFactory;
import it.polimi.ingsw.PS19.model.card.PoliticDeck;
import it.polimi.ingsw.PS19.model.parameter.ColorManager;
import it.polimi.ingsw.PS19.model.parameter.RegionType;
import it.polimi.ingsw.PS19.model.map.Region;

public class Map 
{	
	ArrayList<Region> listaRegioni;
	private PoliticDeck politicdeck;
	private ColorManager councilcolors;
	private King king;
	private GeneralBonus kingBonus;

	
	public static void main(String[] args) {
		Map map = MapLoader.builder();
		map.kingBonus.print();
	}
	
	private Map(ArrayList<Region> regionlist)
	{
		listaRegioni = new ArrayList<Region>();
		listaRegioni.addAll(regionlist);
		
		kingFactory();	
		councilcolors = BalconyFactory.createBalcony(listaRegioni, king, FileNames.CARD_FILE);
		politicdeck = (PoliticDeck) DeckFactory.politicsDeckFactory(FileNames.CARD_FILE, councilcolors);
		kingBonus = new GeneralBonus(FileNames.MAP_FILE, listaRegioni);
	}
	
	private void kingFactory()
	{
		ArrayList<City> cities = getAllCities();
		for (City city : cities)
		{
			if(city.getCitycolor().equals(Color.decode("#8B008B")))
			{
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
	
	public Region getRegionByType(RegionType r)
	{
		for (Region region : listaRegioni) 
		{
			if(region.getType() == r)
				return region;
		}
		return null;
	}
	
	public ArrayList<Region> getListaRegioni() 
	{
		return listaRegioni;
	}
	
	public King getKing() 
	{
		return king;
	}
	public ColorManager getCouncilcolors() 
	{
		return councilcolors;
	}
	
	@Override
	public String toString() 
	{
		String s = "";
		for (Region region : listaRegioni) 
			s += region.toString() + "\n";
		s += "KING:  " + king.toString();
		return s;
	}
	
	public static Map finalMapBuilder(ArrayList<ArrayList<City>> citiesbyregion){
		
		Map map = new Map(Region.finalRegionBuilder(citiesbyregion));
		return map;
		
	}
}
