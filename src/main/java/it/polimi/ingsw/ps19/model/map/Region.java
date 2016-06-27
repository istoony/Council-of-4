package it.polimi.ingsw.ps19.model.map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.card.BusinessDeck;
import it.polimi.ingsw.ps19.model.card.DeckFactory;
import it.polimi.ingsw.ps19.model.parameter.FileNames;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

public class Region implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3588965044910132551L;
	
	private RegionType type; 
	private ArrayList<City> cities;
	private BusinessDeck businessdeck;
	private BusinessCard firstcard;
	private BusinessCard secondcard;
	private Balcony balcony;
	
	private Region(List<City> el)
	{
		cities = new ArrayList<>();
		cities.addAll(el);
		
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
	public static List<Region> finalRegionBuilder(List<List<City>> regioncitylist)
	{
		
		List<Region> returnmap = new ArrayList<>();
		
		for(List<City> el : regioncitylist)
		{
			returnmap.add(new Region(el));
			returnmap.get(returnmap.size()-1).setPosition(RegionType.valueOf(returnmap.size()-1));
		}
		
		return returnmap;
	}
	
	public List<City> getCities() 
	{
		return cities;
	}
	
	public City getCityById(int id)
	{
		for (City c : cities) 
			if(c.getId() == id)
				return c;
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
	public void drawFirstCard()
	{
		firstcard = businessdeck.getFirstCard();
	}
	
	public void drawSecondCard()
	{
		secondcard = businessdeck.getFirstCard();
	}
	public BusinessDeck getBusinessdeck() 
	{
		return businessdeck;
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
		s += "MAZZO\n";
		for (BusinessCard p : businessdeck.getCard()) 
		{
			s += p.toString() + "\n";
		}
		s += "\n   BALCONY:   " + balcony.toString();
		return s;
	}
	
}
