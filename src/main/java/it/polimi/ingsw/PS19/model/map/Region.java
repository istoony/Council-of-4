package it.polimi.ingsw.PS19.model.map;

import java.util.ArrayList;

import it.polimi.ingsw.PS19.model.FileNames;
import it.polimi.ingsw.PS19.model.card.BusinessCard;
import it.polimi.ingsw.PS19.model.card.BusinessDeck;
import it.polimi.ingsw.PS19.model.card.DeckFactory;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class Region {
	
	private RegionType type; 
	private ArrayList<City> cities;
	private BusinessDeck businessdeck;
	private BusinessCard firstcard;
	private BusinessCard secondcard;
	private Balcony balcony;
	
	private Region(ArrayList<City> c)
	{
		cities = new ArrayList<City>();
		cities.addAll(c);
		
	}
	
	private void setPosition(RegionType pos)
	{
		this.type=pos;
		
		initBusinessDeck();
	}
	
	public void setBalcony(Balcony balcony) 
	{
		this.balcony = balcony;
	}
	public Balcony getBalcony() 
	{
		return balcony;
	}
	
	private void initBusinessDeck()
	{
		this.businessdeck = DeckFactory.businessDeckFactory(FileNames.CARD_FILE,type, cities);
		firstcard = businessdeck.getFirstCard();
		secondcard = businessdeck.getFirstCard();
	}
	
	public RegionType getType() 
	{
		return type;
	}
	
	//inizializzazione 
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
	
	public City getCityById(int id)
	{
		for (City c : cities) 
		{
			if(c.getId() == id)
				return c;
		}
		return null;
	}
	public BusinessCard getFirstcard() 
	{
		return firstcard;
	}
	public BusinessCard getSecondcard() 
	{
		return secondcard;
	}
	
	@Override
	public String toString() {
		String s = "\n";
		s += "REGION POSITION: " + type.toString() + "\n";
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
