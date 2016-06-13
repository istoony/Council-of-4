package it.polimi.ingsw.PS19.model.map;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import it.polimi.ingsw.PS19.model.FileNames;
import it.polimi.ingsw.PS19.model.bonus.GeneralBonus;
import it.polimi.ingsw.PS19.model.card.DeckFactory;
import it.polimi.ingsw.PS19.model.card.PoliticDeck;
import it.polimi.ingsw.PS19.model.parameter.ColorManager;
import it.polimi.ingsw.PS19.model.parameter.RegionType;


public class Map 
{	
	ArrayList<Region> listaRegioni;
	private PoliticDeck politicdeck;
	private AvailableCouncillor councilcolors;
	private King king;
	private GeneralBonus kingBonus;
	private NobilityPath nobilityPath;

	
	private Map(List<Region> list)
	{
		listaRegioni = new ArrayList<>();
		listaRegioni.addAll(list);
		
		kingFactory();	
		councilcolors = BalconyFactory.createBalcony(listaRegioni, king, FileNames.CARD_FILE);
		politicdeck = (PoliticDeck) DeckFactory.politicsDeckFactory(FileNames.CARD_FILE, councilcolors.getListofcolors());
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
		ArrayList<City> cities = new ArrayList<>();
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
	
	public List<Region> getListaRegioni() 
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
		return councilcolors.getListofcolors();
	}
	public PoliticDeck getPoliticdeck() 
	{
		return politicdeck;
	}
	public AvailableCouncillor getAvailableCouncillor() 
	{
		return councilcolors;
	}
	public NobilityPath getNobilityPath() 
	{
		return nobilityPath;
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
	
	public static Map finalMapBuilder(List<ArrayList<City>> citiesbyregion){

		return new Map(Region.finalRegionBuilder(citiesbyregion));
		
	}
	
	public List<City> calculateShorterPath(City start, City end){
		ArrayList<City> result = new ArrayList<>();
		for(Region r : this.listaRegioni){
			for(City c : r.getCities()){
				if(start.equals(c)){
					HashMap<City, City> visited = new HashMap<>(); 
					HashMap<City, City> parentree = new HashMap<>(); 
					ArrayList<City> frontier = new ArrayList<>();
					result = recursiveBFS(start, end, result, c, visited, frontier, parentree);
				}
			}
		}
		return result;
	}
	
	
	//hashmap for path reconstruction, <node, parentnode>
	private ArrayList<City> recursiveBFS(City root, City end, ArrayList<City> path, City start, HashMap<City, City> visited, ArrayList<City> frontier, HashMap<City, City> parenttree){
		if(root.equals(end))
		{
			path.add(end);
			City temp;
			while(!path.contains(start)){
				temp = parenttree.get(root);
				path.add(temp);
				root=temp;
			}
			return path;
		}
		
		for(City c : root.getNeighbours()){
			if(!parenttree.containsKey(c)){
				parenttree.put(c, root);
			}
		}
		frontier.addAll(root.getNeighbours());
		City newroot = frontier.get(0);
		frontier.remove(0);
		while(visited.containsValue(newroot)){
			newroot = frontier.get(0);
			frontier.remove(0);
		}
		visited.put(newroot, root);
		return recursiveBFS(newroot, end, path, start, visited, frontier, parenttree);
		
		}
		
}


