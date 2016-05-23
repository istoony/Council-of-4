package it.polimi.ingsw.PS19.model.map;

import java.util.ArrayList;

import it.polimi.ingsw.PS19.model.card.BusinessDeck;
import it.polimi.ingsw.PS19.model.card.Card;
import it.polimi.ingsw.PS19.model.card.DeckFactory;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class Region {
	
	public static final String FILE_PATH = "mapfile/politicscard.xml"; //---->forse file configurazione?
	
	
	private RegionType position; //0, 1, 2 --->MOLTO MEGLIO MOUNTAIN; HILL; PLAIN
	private ArrayList<City> cities;
	private BusinessDeck businessdeck;
	private Card firstcard;
	private Card secondcard;
	private Balcony balcony;
	
	private Region(ArrayList<City> c)
	{
		cities = new ArrayList<City>();
		cities.addAll(c);
		
		
	}
	
	private void setPosition(RegionType pos)
	{
		this.position=pos;
		
		initBusinessDeck();
	}
	
	public void setBalcony(Balcony balcony) 
	{
		this.balcony = balcony;
	}
	
	private void initBusinessDeck()
	{
		this.businessdeck = DeckFactory.businessDeckFactory(FILE_PATH,position, cities);
		firstcard = businessdeck.getFirstCard();
		secondcard = businessdeck.getFirstCard();
	}
	
	
	public static ArrayList<Region> finalRegionBuilder(ArrayList<ArrayList<City>> citiesbyregion)
	{
		
		ArrayList<Region> returnmap = new ArrayList<Region>();
		
		for(ArrayList<City> el : citiesbyregion)
		{
			returnmap.add(new Region(el));
			returnmap.get(returnmap.size()-1).setPosition(RegionType.valueOf(returnmap.size()-1));
		}
		
		return returnmap;
	}
	
	public ArrayList<City> getCities() 
	{
		return cities;
	}
	
	@Override
	public String toString() {
		String s = "\n";
		s += "REGION POSITION: " + position.toString() + "\n";
		for (City city : cities) 
		{
			s += city.toString() + "\n";
		}
		s += "-----CARDS-----\n   FIRST CARD: \n     " + firstcard.toString();
		s += "   SECOND CARD: \n     " + secondcard.toString();
		s += "\n   BALCONY:   " + balcony.toString();
		return s;
	}
	
}
