package it.polimi.ingsw.ps19.model.map;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.model.bonus.GeneralBonus;
import it.polimi.ingsw.ps19.model.card.DeckFactory;
import it.polimi.ingsw.ps19.model.card.PoliticDeck;
import it.polimi.ingsw.ps19.model.parameter.ColorManager;
import it.polimi.ingsw.ps19.model.parameter.FileNames;
import it.polimi.ingsw.ps19.model.parameter.RegionType;


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
		List<City> cities = getAllCities();
		for (City city : cities)
			if(city.getCitycolor().equals(Color.decode("#8B008B")))
				king = new King(city);		
	}
	
	public List<City> getAllCities()
	{
		List<City> cities = new ArrayList<>();
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
		List<Region> reg = new ArrayList<>();
		for (Region region : listaRegioni)
			reg.add(region);
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
	public GeneralBonus getKingBonus() 
	{
		return kingBonus;
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
	
	public static Map finalMapBuilder(List<List<City>> regioncitylist){

		return new Map(Region.finalRegionBuilder(regioncitylist));
		
	}
}


