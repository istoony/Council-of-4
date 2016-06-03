package it.polimi.ingsw.PS19.model.map;

import java.awt.Color;
import java.util.ArrayList;

import it.polimi.ingsw.PS19.model.FileNames;
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
	private NobilityPath nobilityPath;

	
	private Map(ArrayList<Region> regionlist)
	{
		listaRegioni = new ArrayList<Region>();
		listaRegioni.addAll(regionlist);
		
		kingFactory();	
		councilcolors = BalconyFactory.createBalcony(listaRegioni, king, FileNames.CARD_FILE);
		politicdeck = (PoliticDeck) DeckFactory.politicsDeckFactory(FileNames.CARD_FILE, councilcolors);
		kingBonus = new GeneralBonus(FileNames.MAP_FILE, listaRegioni);
		nobilityPath = new NobilityPath(FileNames.CARD_FILE);
	}
	
	private void kingFactory()
	{
		ArrayList<City> cities = getAllCities();
		for (City city : cities)
			if(city.getCitycolor().equals(Color.decode("#8B008B")))
				king = new King(city);		
	}
	
	private ArrayList<City> getAllCities()
	{
		ArrayList<City> cities = new ArrayList<City>();
		for (Region region : listaRegioni) 
			cities.addAll(region.getCities());
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
		ArrayList<Region> reg = new ArrayList<>();
		reg.addAll(listaRegioni);
		return reg;
	}
	
	public King getKing() 
	{
		return king;
	}
	public ColorManager getCouncilcolors() 
	{
		return councilcolors;
	}
	public PoliticDeck getPoliticdeck() 
	{
		return politicdeck;
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

		return new Map(Region.finalRegionBuilder(citiesbyregion));
		
	}
	
	public int calculateShorterPath(City start, City end){
		ArrayList<City> result = new ArrayList<City>();
		for(Region r : this.listaRegioni){
			for(City c : r.getCities()){
				if(start.equals(c)){
					ArrayList<City> vis = new ArrayList<City>();
					result = this.recursive(start, end, result, c, vis);
				}
			}
		}
		return result.size()+1;
	}
	
	private ArrayList<City> recursive(City start, City end, ArrayList<City> path, City c, ArrayList<City> visited){
		for(City n : c.getNeighbours()){
			if(!visited.contains(n)){
				if(end.equals(n)){
					path.add(n);
					return path;
				}
				visited.add(c);
				visited.add(n);
				path = this.recursive(start, end, path, n, visited);
				if(!path.isEmpty()){
					if(path.contains(n)){
						return path;
					}
					path.add(n);
					return path;
				}
			}
		}
		return path;
	}
}
